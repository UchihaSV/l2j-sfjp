/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.gameserver.model.actor.instance;

import static com.l2jserver.gameserver.model.itemcontainer.PcInventory.MAX_ADENA;
import static com.l2jserver.gameserver.util.Util.formatAdena;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javolution.util.FastMap;

import com.l2jserver.Config;
import com.l2jserver.gameserver.instancemanager.AuctionManager;
import com.l2jserver.gameserver.instancemanager.ClanHallManager;
import com.l2jserver.gameserver.instancemanager.MapRegionManager;
import com.l2jserver.gameserver.model.L2Clan;
import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.model.actor.templates.L2NpcTemplate;
import com.l2jserver.gameserver.model.entity.Auction;
import com.l2jserver.gameserver.model.entity.Auction.Bidder;
import com.l2jserver.gameserver.model.entity.clanhall.AuctionableHall;
import com.l2jserver.gameserver.network.SystemMessageId;
import com.l2jserver.gameserver.network.serverpackets.NpcHtmlMessage;
import com.l2jserver.gameserver.util.Util;

public final class L2AuctioneerInstance extends L2Npc
{
	private static final int COND_ALL_FALSE = 0;
	private static final int COND_BUSY_BECAUSE_OF_SIEGE = 1;
	private static final int COND_REGULAR = 3;
	
	private Map<Integer, Auction> _pendingAuctions = new FastMap<>();
	
	public L2AuctioneerInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
		setInstanceType(InstanceType.L2AuctioneerInstance);
	}
	
	@Override
	public void onBypassFeedback(L2PcInstance player, String command)
	{
		int condition = validateCondition(player);
		if (condition <= COND_ALL_FALSE)
		{
			//TODO: html
			player.sendMessage("Wrong conditions.");
			return;
		}
		else if (condition == COND_BUSY_BECAUSE_OF_SIEGE)
		{
			String filename = "data/html/auction/auction-busy.htm";
			NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
			html.setFile(player.getHtmlPrefix(), filename);
			html.replace("%objectId%", getObjectId());
			player.sendPacket(html);
			return;
		}
		else if (condition == COND_REGULAR)
		{
			StringTokenizer st = new StringTokenizer(command, " ");
			String actualCommand = st.nextToken(); // Get actual command
			
			String val = "";
			if (st.countTokens() >= 1)
				val = st.nextToken();
			
			if (actualCommand.equalsIgnoreCase("auction"))
			{
				if (val.isEmpty())
					return;
				
				try
				{
					int days = Integer.parseInt(val);
					try
					{
						SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
						long bid = 0;
						if (st.countTokens() >= 1)
							bid = Math.min(Long.parseLong(st.nextToken()), MAX_ADENA);
						
						Auction a = new Auction(player.getClan().getHideoutId(), player.getClan(), days*86400000L, bid, ClanHallManager.getInstance().getClanHallByOwner(player.getClan()).getName());
						if (_pendingAuctions.get(a.getId()) != null)
							_pendingAuctions.remove(a.getId());
						
						_pendingAuctions.put(a.getId(), a);
						
						String filename = "data/html/auction/AgitSale3.htm";
						NpcHtmlMessage html = new NpcHtmlMessage(1);
						html.setFile(player.getHtmlPrefix(), filename);
						html.replace("%x%", val);
						html.replace("%AGIT_AUCTION_END%", format.format(a.getEndDate()));
						html.replace("%AGIT_AUCTION_MINBID%", formatAdena(a.getStartingBid()));
						html.replace("%AGIT_AUCTION_MIN%", formatAdena(a.getStartingBid()));
						html.replace("%AGIT_AUCTION_DESC%", ClanHallManager.getInstance().getClanHallByOwner(player.getClan()).getDesc());
						html.replace("%AGIT_LINK_BACK%", "bypass -h npc_"+getObjectId()+"_sale2");
						html.replace("%objectId%", getObjectId());
						player.sendPacket(html);
					}
					catch (Exception e)
					{
						player.sendMessage("Invalid bid!");
					}
				}
				catch (Exception e)
				{
					player.sendMessage("Invalid auction duration!");
				}
				return;
			}
			else if (actualCommand.equalsIgnoreCase("confirmAuction"))
			{
				try
				{
					Auction a = _pendingAuctions.get(player.getClan().getHideoutId());
					a.confirmAuction();
					_pendingAuctions.remove(player.getClan().getHideoutId());
				}
				catch (Exception e)
				{
					player.sendMessage("Invalid auction");
				}
				return;
			}
			else if (actualCommand.equalsIgnoreCase("bidding"))
			{
				if (val.isEmpty())
					return;
				
				if (Config.DEBUG)
					_log.warning("bidding show successful");
				
				try
				{
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					int auctionId = Integer.parseInt(val);
					
					if (Config.DEBUG)
						_log.warning("auction test started");
					
					String filename = "data/html/auction/AgitAuctionInfo.htm";
					Auction a = AuctionManager.getInstance().getAuction(auctionId);
					
					NpcHtmlMessage html = new NpcHtmlMessage(1);
					html.setFile(player.getHtmlPrefix(), filename);
					if (a != null)
					{
						html.replace("%AGIT_NAME%", a.getItemName());
						html.replace("%OWNER_PLEDGE_NAME%", a.getSellerClanName());
						html.replace("%OWNER_PLEDGE_MASTER%", a.getSellerName());
						html.replace("%AGIT_SIZE%", ClanHallManager.getInstance().getAuctionableHallById(a.getItemId()).getGrade()*10);
						html.replace("%AGIT_LEASE%", formatAdena(ClanHallManager.getInstance().getAuctionableHallById(a.getItemId()).getLease()));
						html.replace("%AGIT_LOCATION%", ClanHallManager.getInstance().getAuctionableHallById(a.getItemId()).getLocNameHtm());
						html.replace("%AGIT_AUCTION_END%", format.format(a.getEndDate()));
						html.replace("%AGIT_AUCTION_REMAIN%", ((a.getEndDate()- System.currentTimeMillis())/3600000)+" hours "+(((a.getEndDate() - System.currentTimeMillis()) / 60000) % 60)+" minutes");
						html.replace("%AGIT_AUCTION_MINBID%", formatAdena(a.getStartingBid()));
						html.replace("%AGIT_AUCTION_COUNT%", a.getBidders().size());
						html.replace("%AGIT_AUCTION_DESC%", ClanHallManager.getInstance().getAuctionableHallById(a.getItemId()).getDesc());
						html.replace("%AGIT_LINK_BACK%", "bypass -h npc_"+getObjectId()+"_list");
						html.replace("%AGIT_LINK_BIDLIST%", "bypass -h npc_"+getObjectId()+"_bidlist "+a.getId());
						html.replace("%AGIT_LINK_RE%", "bypass -h npc_"+getObjectId()+"_bid1 "+a.getId());
						player.sendPacket(html);
					}
					else
						_log.warning("Auctioneer Auction null for AuctionId : "+auctionId);
				}
				catch (Exception e)
				{
					player.sendMessage("Invalid auction!");
				}
				return;
			}
			else if (actualCommand.equalsIgnoreCase("bid"))
			{
				if (val.isEmpty())
					return;
				
				try
				{
					int auctionId = Integer.parseInt(val);
					try
					{
						long bid = 0;
						if (st.countTokens() >= 1)
							bid = Math.min(Long.parseLong(st.nextToken()), MAX_ADENA);
						
						AuctionManager.getInstance().getAuction(auctionId).setBid(player, bid);
					}
					catch (Exception e)
					{
						player.sendMessage("Invalid bid!");
					}
				}
				catch (Exception e)
				{
					player.sendMessage("Invalid auction!");
				}
				return;
			}
			else if (actualCommand.equalsIgnoreCase("bid1"))
			{
				if (player.getClan() == null || player.getClan().getLevel() < 2)
				{
					player.sendPacket(SystemMessageId.AUCTION_ONLY_CLAN_LEVEL_2_HIGHER);
					return;
				}
				
				if (val.isEmpty())
					return;
				
				if (player.getClan().getAuctionBiddedAt() > 0 && player.getClan().getAuctionBiddedAt() != Integer.parseInt(val))
				{
					player.sendPacket(SystemMessageId.ALREADY_SUBMITTED_BID);
					return;
				}
				if (player.getClan().getHideoutId() > 0 || player.getClan().getCastleId() > 0)	//[JOJO]
				{
					player.sendPacket(SystemMessageId.CANNOT_PARTICIPATE_IN_AN_AUCTION);
					return;
				}
				
				try
				{
					String filename = "data/html/auction/AgitBid1.htm";
					
					long minimumBid = AuctionManager.getInstance().getAuction(Integer.parseInt(val)).getHighestBidderMaxBid();
					if (minimumBid == 0)
						minimumBid = AuctionManager.getInstance().getAuction(Integer.parseInt(val)).getStartingBid();
					
					NpcHtmlMessage html = new NpcHtmlMessage(1);
					html.setFile(player.getHtmlPrefix(), filename);
					html.replace("%AGIT_LINK_BACK%", "bypass -h npc_"+getObjectId()+"_bidding "+val);
					html.replace("%PLEDGE_ADENA%", formatAdena(player.getClan().getWarehouse().getAdena()));
					html.replace("%AGIT_AUCTION_MINBID%", formatAdena(minimumBid));
					html.replace("npc_%objectId%_bid", "npc_"+getObjectId()+"_bid "+val);
					player.sendPacket(html);
					return;
				}
				catch (Exception e)
				{
					player.sendMessage("Invalid auction!");
				}
				return;
			}
			else if (actualCommand.equalsIgnoreCase("list"))
			{
				List<Auction> auctions =AuctionManager.getInstance().getAuctions();
				SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd");
				/** Limit for make new page, prevent client crash **/
				int limit = 15;
				int start;
				int i = 1;
				double npage = Math.ceil((float)auctions.size()/limit);
				
				if (val.isEmpty())
				{
					start = 1;
				}
				else
				{
					start = limit*(Integer.parseInt(val)-1)+1;
					limit *= Integer.parseInt(val);
				}
				
				if (Config.DEBUG)
					_log.warning("cmd list: auction test started");
				
				StringBuilder items = new StringBuilder();
				items.append("<table width=280 border=0><tr>");
				for (int j = 1; j <= npage; j++)
				{
					items.append("<td><center><a action=\"bypass -h npc_")
					     .append(getObjectId())
					     .append("_list ")
					     .append(j)
					     .append("\"> Page ")
					     .append(j)
					     .append(" </a></center></td>");
				}
				
				items.append("</tr></table>"
					+ "<table width=280 border=0>");
				
				for (Auction a : auctions)
				{
					if (a == null)
						continue;
					
					if (i > limit)
						break;
					else if (i < start)
					{
						i++;
						continue;
					}
					else
						i++;
					
					items.append("<tr>"
					     + "<td>")
					     .append(ClanHallManager.getInstance().getAuctionableHallById(a.getItemId()).getLocNameHtm())
					     .append("</td>"
					     + "<td><a action=\"bypass -h npc_")
					     .append(getObjectId())
					     .append("_bidding ")
					     .append(a.getId())
					     .append("\">")
					     .append(a.getItemName())
					     .append("</a></td>"
					     + "<td>")
					     .append(format.format(a.getEndDate()))
					     .append("</td>"
					     + "<td>")
					     .append(formatAdena(a.getStartingBid()))
					     .append("</td>"
					     + "</tr>");
				}
				
				items.append("</table>");
				String filename = "data/html/auction/AgitAuctionList.htm";
				
				NpcHtmlMessage html = new NpcHtmlMessage(1);
				html.setFile(player.getHtmlPrefix(), filename);
				html.replace("%AGIT_LINK_BACK%", "bypass -h npc_"+getObjectId()+"_start");
				html.replace("%itemsField%", items);
				player.sendPacket(html);
				return;
			}
			else if (actualCommand.equalsIgnoreCase("bidlist"))
			{
				int auctionId = 0;
				if (val.isEmpty())
				{
					if (player.getClan().getAuctionBiddedAt() <= 0)
						return;
					auctionId = player.getClan().getAuctionBiddedAt();
				}
				else
					auctionId = Integer.parseInt(val);
				
				if (Config.DEBUG)
					_log.warning("cmd bidlist: auction test started");
				
				StringBuilder biders = new StringBuilder();
				Map<Integer, Bidder> bidders = AuctionManager.getInstance().getAuction(auctionId).getBidders();
				for(Bidder b :bidders.values())
				{
					biders.append("<tr>" +
					"<td>").append(b.getClanName()).append("</td><td>").append(b.getName()).append("</td><td>").append(b.getTimeBid().get(Calendar.YEAR)).append('/').append((b.getTimeBid().get(Calendar.MONTH)+1)).append('/').append(b.getTimeBid().get(Calendar.DATE)).append("</td><td>").append(Util.formatAdena(b.getBid())).append("</td>" +
					"</tr>");
				}
				String filename = "data/html/auction/AgitBidderList.htm";
				
				NpcHtmlMessage html = new NpcHtmlMessage(1);
				html.setFile(player.getHtmlPrefix(), filename);
				html.replace("%AGIT_LIST%", biders);
				html.replace("%AGIT_LINK_BACK%", "bypass -h npc_"+getObjectId()+"_selectedItems");
				html.replace("%x%", val);
				html.replace("%objectId%", getObjectId());
				player.sendPacket(html);
				return;
			}
			else if (actualCommand.equalsIgnoreCase("selectedItems"))
			{
				if (player.getClan() == null)
				{
					player.sendPacket(SystemMessageId.CANNOT_PARTICIPATE_IN_AN_AUCTION);
					return;
				}
				if (player.getClan().getHideoutId() == 0 && player.getClan().getAuctionBiddedAt() > 0)
				{
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					String filename = "data/html/auction/AgitBidInfo.htm";
					NpcHtmlMessage html = new NpcHtmlMessage(1);
					html.setFile(player.getHtmlPrefix(), filename);
					Auction a = AuctionManager.getInstance().getAuction(player.getClan().getAuctionBiddedAt());
					if (a != null)
					{
						AuctionableHall item = ClanHallManager.getInstance().getAuctionableHallById(a.getItemId());
						
						html.replace("%AGIT_NAME%", a.getItemName());
						html.replace("%OWNER_PLEDGE_NAME%", a.getSellerClanName());
						html.replace("%OWNER_PLEDGE_MASTER%", a.getSellerName());
						html.replace("%AGIT_SIZE%", item.getGrade()*10);
						html.replace("%AGIT_LEASE%", formatAdena(item.getLease()));
						html.replace("%AGIT_LOCATION%", item.getLocNameHtm());
						html.replace("%AGIT_AUCTION_END%", format.format(a.getEndDate()));
						html.replace("%AGIT_AUCTION_REMAIN%", ((a.getEndDate()-System.currentTimeMillis()) / 3600000)+" hours "+(((a.getEndDate()-System.currentTimeMillis()) / 60000) % 60)+" minutes");
						html.replace("%AGIT_AUCTION_MINBID%", formatAdena(a.getStartingBid()));
						html.replace("%AGIT_AUCTION_MYBID%", formatAdena(a.getBidders().get(player.getClanId()).getBid()));
						html.replace("%AGIT_AUCTION_DESC%", item.getDesc());
						html.replace("%objectId%", getObjectId());
						html.replace("%AGIT_LINK_BACK%", "bypass -h npc_"+getObjectId()+"_start");
						player.sendPacket(html);
					}
					else
						_log.warning("Auctioneer Auction null for AuctionBiddedAt : "+player.getClan().getAuctionBiddedAt());
					return;
				}
				else if (AuctionManager.getInstance().getAuction(player.getClan().getHideoutId()) != null)
				{
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					String filename = "data/html/auction/AgitSaleInfo.htm";
					NpcHtmlMessage html = new NpcHtmlMessage(1);
					html.setFile(player.getHtmlPrefix(), filename);
					Auction a = AuctionManager.getInstance().getAuction(player.getClan().getHideoutId());
					if (a != null)
					{
						AuctionableHall item = ClanHallManager.getInstance().getAuctionableHallById(a.getItemId());
						
						html.replace("%AGIT_NAME%", a.getItemName());
						html.replace("%AGIT_OWNER_PLEDGE_NAME%", a.getSellerClanName());
						html.replace("%OWNER_PLEDGE_MASTER%", a.getSellerName());
						html.replace("%AGIT_SIZE%", item.getGrade()*10);
						html.replace("%AGIT_LEASE%", formatAdena(item.getLease()));
						html.replace("%AGIT_LOCATION%", item.getLocNameHtm());
						html.replace("%AGIT_AUCTION_END%", format.format(a.getEndDate()));
						html.replace("%AGIT_AUCTION_REMAIN%", ((a.getEndDate()-System.currentTimeMillis()) / 3600000)+" hours "+(((a.getEndDate()-System.currentTimeMillis()) / 60000) % 60)+" minutes");
						html.replace("%AGIT_AUCTION_MINBID%", formatAdena(a.getStartingBid()));
						html.replace("%AGIT_AUCTION_BIDCOUNT%", a.getBidders().size());
						html.replace("%AGIT_AUCTION_DESC%", item.getDesc());
						html.replace("%AGIT_LINK_BACK%", "bypass -h npc_"+getObjectId()+"_start");
						html.replace("%id%", a.getId());
						html.replace("%objectId%", getObjectId());
						player.sendPacket(html);
					}
					else
						_log.warning("Auctioneer Auction null for getHasHideout : "+player.getClan().getHideoutId());
					return;
				}
				else if (player.getClan().getHideoutId() != 0)
				{
					int ItemId = player.getClan().getHideoutId();
					String filename = "data/html/auction/AgitInfo.htm";
					NpcHtmlMessage html = new NpcHtmlMessage(1);
					html.setFile(player.getHtmlPrefix(), filename);
					AuctionableHall item = ClanHallManager.getInstance().getAuctionableHallById(ItemId);
					if (item != null)
					{
						html.replace("%AGIT_NAME%", item.getNameHtm());
						html.replace("%AGIT_OWNER_PLEDGE_NAME%", player.getClan().getName());
						html.replace("%OWNER_PLEDGE_MASTER%", player.getClan().getLeaderName());
						html.replace("%AGIT_SIZE%", item.getGrade()*10);
						html.replace("%AGIT_LEASE%", formatAdena(item.getLease()));
						html.replace("%AGIT_LOCATION%", item.getLocNameHtm());
						html.replace("%AGIT_LINK_BACK%", "bypass -h npc_"+getObjectId()+"_start");
						html.replace("%objectId%", getObjectId());
						player.sendPacket(html);
					}
					else
						_log.warning("Clan Hall ID NULL : "+ItemId+" Can be caused by concurent write in ClanHallManager");
					return;
				}
				else if (player.getClan().getHideoutId() == 0)
				{
					player.sendPacket(SystemMessageId.NO_OFFERINGS_OWN_OR_MADE_BID_FOR);
					return;
				}
				else
				{
					// L2AuctioneerInstance: Unknown NPC bypass: "selectedItems" NpcId: 30767
					player.sendPacket(SystemMessageId.CANNOT_PARTICIPATE_IN_AN_AUCTION);	//+[JOJO]
					return;	//+[JOJO]
				}
			}
			else if (actualCommand.equalsIgnoreCase("cancelBid"))
			{
				long bid = AuctionManager.getInstance().getAuction(player.getClan().getAuctionBiddedAt()).getBidders().get(player.getClanId()).getBid();
				String filename = "data/html/auction/AgitBidCancel.htm";
				NpcHtmlMessage html = new NpcHtmlMessage(1);
				html.setFile(player.getHtmlPrefix(), filename);
				html.replace("%AGIT_BID%", formatAdena(bid));
				html.replace("%AGIT_BID_REMAIN%", formatAdena((long)(bid*0.9)));
				html.replace("%AGIT_LINK_BACK%", "bypass -h npc_"+getObjectId()+"_selectedItems");
				html.replace("%objectId%", getObjectId());
				player.sendPacket(html);
				return;
			}
			else if (actualCommand.equalsIgnoreCase("doCancelBid"))
			{
				if (AuctionManager.getInstance().getAuction(player.getClan().getAuctionBiddedAt()) != null)
				{
					AuctionManager.getInstance().getAuction(player.getClan().getAuctionBiddedAt()).cancelBid(player.getClanId());
					player.sendPacket(SystemMessageId.CANCELED_BID);
				}
				return;
			}
			else if (actualCommand.equalsIgnoreCase("cancelAuction"))
			{
				if (!((player.getClanPrivileges() & L2Clan.CP_CH_AUCTION) == L2Clan.CP_CH_AUCTION))
				{
					String filename = "data/html/auction/not_authorized.htm";
					NpcHtmlMessage html = new NpcHtmlMessage(1);
					html.setFile(player.getHtmlPrefix(), filename);
					html.replace("%objectId%", getObjectId());
					player.sendPacket(html);
					return;
				}
				String filename = "data/html/auction/AgitSaleCancel.htm";
				NpcHtmlMessage html = new NpcHtmlMessage(1);
				html.setFile(player.getHtmlPrefix(), filename);
				html.replace("%AGIT_DEPOSIT%", formatAdena(ClanHallManager.getInstance().getClanHallByOwner(player.getClan()).getLease()));
				html.replace("%AGIT_LINK_BACK%", "bypass -h npc_"+getObjectId()+"_selectedItems");
				html.replace("%objectId%", getObjectId());
				player.sendPacket(html);
				return;
			}
			else if (actualCommand.equalsIgnoreCase("doCancelAuction"))
			{
				if (AuctionManager.getInstance().getAuction(player.getClan().getHideoutId()) != null)
				{
					AuctionManager.getInstance().getAuction(player.getClan().getHideoutId()).cancelAuction();
					player.sendMessage("Your auction has been canceled");
				}
				return;
			}
			else if (actualCommand.equalsIgnoreCase("sale2"))
			{
				String filename = "data/html/auction/AgitSale2.htm";
				NpcHtmlMessage html = new NpcHtmlMessage(1);
				html.setFile(player.getHtmlPrefix(), filename);
				html.replace("%AGIT_LAST_PRICE%", formatAdena(ClanHallManager.getInstance().getClanHallByOwner(player.getClan()).getLease())); //TODO: ~1‚©ŒŽ•ª‚Ì‰Æ’À ¨ ›ÅI“üŽD‰¿Ši
				html.replace("%AGIT_LINK_BACK%", "bypass -h npc_"+getObjectId()+"_sale");
				html.replace("%objectId%", getObjectId());
				player.sendPacket(html);
				return;
			}
			else if (actualCommand.equalsIgnoreCase("sale"))
			{
				if (!((player.getClanPrivileges() & L2Clan.CP_CH_AUCTION) == L2Clan.CP_CH_AUCTION))
				{
					String filename = "data/html/auction/not_authorized.htm";
					NpcHtmlMessage html = new NpcHtmlMessage(1);
					html.setFile(player.getHtmlPrefix(), filename);
					html.replace("%objectId%", getObjectId());
					player.sendPacket(html);
					return;
				}
				String filename = "data/html/auction/AgitSale1.htm";
				NpcHtmlMessage html = new NpcHtmlMessage(1);
				html.setFile(player.getHtmlPrefix(), filename);
				html.replace("%AGIT_DEPOSIT%", formatAdena(ClanHallManager.getInstance().getClanHallByOwner(player.getClan()).getLease()));
				html.replace("%AGIT_PLEDGE_ADENA%", formatAdena(player.getClan().getWarehouse().getAdena()));
				html.replace("%AGIT_LINK_BACK%", "bypass -h npc_"+getObjectId()+"_selectedItems");
				html.replace("%objectId%", getObjectId());
				player.sendPacket(html);
				return;
			}
			else if (actualCommand.equalsIgnoreCase("rebid"))
			{
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				if (!((player.getClanPrivileges() & L2Clan.CP_CH_AUCTION) == L2Clan.CP_CH_AUCTION))
				{
					String filename = "data/html/auction/not_authorized.htm";
					NpcHtmlMessage html = new NpcHtmlMessage(1);
					html.setFile(player.getHtmlPrefix(), filename);
					html.replace("%objectId%", getObjectId());
					player.sendPacket(html);
					return;
				}
				try
				{
					String filename = "data/html/auction/AgitBid2.htm";
					NpcHtmlMessage html = new NpcHtmlMessage(1);
					html.setFile(player.getHtmlPrefix(), filename);
					Auction a = AuctionManager.getInstance().getAuction(player.getClan().getAuctionBiddedAt());
					if (a != null)
					{
						html.replace("%AGIT_AUCTION_BID%", formatAdena(a.getBidders().get(player.getClanId()).getBid()));
						html.replace("%AGIT_AUCTION_MINBID%", formatAdena(a.getStartingBid()));
						html.replace("%AGIT_AUCTION_END%",format.format(a.getEndDate()));
						html.replace("%AGIT_LINK_BACK%", "bypass -h npc_"+getObjectId()+"_selectedItems");
						html.replace("npc_%objectId%_bid1", "npc_"+getObjectId()+"_bid1 "+a.getId());
						player.sendPacket(html);
					}
					else
						_log.warning("Auctioneer Auction null for AuctionBiddedAt : "+player.getClan().getAuctionBiddedAt());
				}
				catch (Exception e)
				{
					player.sendMessage("Invalid auction!");
				}
				return;
			}
			else if (actualCommand.equalsIgnoreCase("location"))
			{
				NpcHtmlMessage html = new NpcHtmlMessage(1);
				html.setFile(player.getHtmlPrefix(), "data/html/auction/location.htm");
				html.replace("%location%", MapRegionManager.getInstance().getClosestTownName(player));
				html.replace("%LOCATION%", getPictureName(player));
				html.replace("%AGIT_LINK_BACK%", "bypass -h npc_"+getObjectId()+"_start");
				player.sendPacket(html);
				return;
			}
			else if (actualCommand.equalsIgnoreCase("start"))
			{
				showChatWindow(player);
				return;
			}
		}
		
		super.onBypassFeedback(player, command);
	}
	
	@Override
	public void showChatWindow(L2PcInstance player)
	{
		String filename;// = "data/html/auction/auction-no.htm";
		
		int condition = validateCondition(player);
		if (condition == COND_BUSY_BECAUSE_OF_SIEGE)
			filename = "data/html/auction/auction-busy.htm"; // Busy because of siege
		else
			filename = "data/html/auction/auction.htm";
		
		NpcHtmlMessage html = new NpcHtmlMessage(1);
		html.setFile(player.getHtmlPrefix(), filename);
		html.replace("%objectId%", getObjectId());
		html.replace("%npcId%", getNpcId());
		html.replace("%npcname%", getName());
		player.sendPacket(html);
	}
	
	private int validateCondition(L2PcInstance player)
	{
		if (getCastle() != null && getCastle().getCastleId() > 0)
		{
			if (getCastle().getSiege().getIsInProgress())
				return COND_BUSY_BECAUSE_OF_SIEGE; // Busy because of siege
			return COND_REGULAR;
		}
		
		return COND_ALL_FALSE;
	}
	
	private String getPictureName(L2PcInstance plyr)
	{
		int nearestTownId = MapRegionManager.getInstance().getMapRegionLocId(plyr);
		String nearestTown;
		
		switch (nearestTownId)
		{
			case 911: nearestTown = "GLUDIN"; break;
			case 912: nearestTown = "GLUDIO"; break;
			case 916: nearestTown = "DION"; break;
			case 918: nearestTown = "GIRAN"; break;
			case 1537: nearestTown = "RUNE"; break;
			case 1538: nearestTown = "GODARD"; break;
			case 1714: nearestTown = "SCHUTTGART"; break;
			default: nearestTown = "ADEN"; break;
		}
		
		return nearestTown;
	}
}