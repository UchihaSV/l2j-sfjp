package com.l2jserver.gameserver;

import com.l2jserver.gameserver.model.actor.L2Npc;

public class debug
{
	public static void TRACE(String message) {TRACE("", message);}
	public static void TRACE(String title, String message)
	{
//		/**
//		 * example 1
//		 */
//		com.l2jserver.gameserver.network.serverpackets.CreatureSay cs = new com.l2jserver.gameserver.network.serverpackets.CreatureSay(0, com.l2jserver.gameserver.network.clientpackets.Say2.TELL, title, message);
//		for (com.l2jserver.gameserver.model.actor.instance.L2PcInstance pc : com.l2jserver.gameserver.datatables.AdminTable.getInstance().getAllGms(true))
//			pc.sendPacket(cs);

//		/**
//		 * example 2
//		 */
//		System.out.println("[TRACE] "+title + ": " + message);	/*example*/
	}
	public static String STR(com.l2jserver.gameserver.model.actor.L2Npc npc) { return npc == null ? "NULL" : npc.getNpcId() + npc.getName(); }
	public static String STR(com.l2jserver.gameserver.model.items.instance.L2ItemInstance item) { return item == null ? "NULL" : item.getItemId() + item.getName(); }
	public static String STR(com.l2jserver.gameserver.model.L2Object obj) { return obj == null ? "NULL" : obj instanceof L2Npc ? ((L2Npc)obj).getNpcId() + obj.getName() : obj.getName(); }
	public static String STR(com.l2jserver.gameserver.model.skills.L2Skill skill) { return skill == null ? "NULL" : (skill.getId()+"-"+skill.getLevel()+" "+skill.getName()); }
	public static String STR(com.l2jserver.gameserver.model.L2Object[] targets) {
		if (targets == null) return "NULL";
		if (targets.length == 0) return "EMPTY";
		StringBuilder s = new StringBuilder(256).append('{');
		for (com.l2jserver.gameserver.model.L2Object o : targets)
			s.append(o == null ? "NULL" : o.getName()).append(',');
		s.setCharAt(s.length()-1, '}');
		return s.toString();
	}
	public static String STR(java.util.concurrent.ScheduledFuture<?> task)
	{
		if (task == null) return "NULL";
		StringBuilder s = new StringBuilder(99).append(com.l2jserver.util.Util.strMillTime(task.getDelay(java.util.concurrent.TimeUnit.MILLISECONDS)));
		if (task.isCancelled()) s.append("[CANCELLED]");
		if (task.isDone()) s.append("[DONE]");
		return s.toString();
	}
}