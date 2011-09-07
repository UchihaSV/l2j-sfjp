package com.l2jserver.gameserver.model.actor.instance;

/**
 * @author JOJO
 */
public interface IhaveOwner
{
	public L2PcInstance getOwner();
	
	public void setOwner(L2PcInstance newOwner);
}
