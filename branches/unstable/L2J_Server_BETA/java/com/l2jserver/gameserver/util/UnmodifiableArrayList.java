//@formatter:off
package com.l2jserver.gameserver.util;

/*
 * @auther JOJO
 */
public final class UnmodifiableArrayList<E> extends java.util.AbstractList<E>
		implements java.util.RandomAccess
{
	private final E[] elementData;
	
	@SuppressWarnings("unchecked")
	public UnmodifiableArrayList(java.util.Collection<E> collection)
	{
		this((E[]) collection.toArray());
	}
	
	public UnmodifiableArrayList(E[] array)
	{
		this.elementData = array;
	}
	
	@Override
	public final E get(int index)
	{
		return elementData[index];
	}
	
	@Override
	public final int size()
	{
		return elementData.length;
	}
}
