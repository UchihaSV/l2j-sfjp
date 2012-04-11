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
package com.l2jserver.gameserver.model.base;


/**
 * This class will hold the information of the player classes.
 * @author Zoey76
 */
public final class ClassInfo
{
	private final ClassId _classId;
	private final String _className;
	private final String _classServName;
	private final ClassId _parentClassId;
	
	/**
	 * Constructor for ClassInfo.
	 * @param classId the class Id.
	 * @param className the in game class name.
	 * @param classServName the server side class name.
	 * @param parentClassId the parent class for the given {@code classId}.
	 */
	public ClassInfo(ClassId classId, String className, String classServName, ClassId parentClassId)
	{
		_classId = classId;
		_className = className;
		_classServName = classServName;
		_parentClassId = parentClassId;
	}
	
	/**
	 * @return the class Id.
	 */
	public ClassId getClassId()
	{
		return _classId;
	}
	
	/**
	 * @return the hardcoded in-game class name.
	 */
	public String getClassName()
	{
		return _className;
	}
	
	/**
	 * @return the class client Id formatted to be displayed on a HTML.
	 */
	public static String toHtm(int id)	//[JOJO]
	{
		return toHtmW(id);
	}
	public static String toHtmW(int id)	//[JOJO]
	{
		//英語   55 "<FSTRING>1811055</FSTRING>" (Bounty Hunter)
		//台湾語 55 "<FSTRING>1811055</FSTRING>" (賞金獵人)
		//日本語 55 "<FSTRING>1811055</FSTRING>" (バウンティ ハンター) -- 全角文字
		return "<FSTRING>" + (1811000 + id) + "</FSTRING>";
	}
	public static String toHtmN(int id)	//[JOJO]
	{
		//英語   55 "&$302;" (Bounty Hunter)
		//台湾語 55 "&$302;" (賞金獵人)
		//日本語 55 "&$302;" (ﾊﾞｳﾝﾃｨｰ ﾊﾝﾀｰ) -- ﾊﾝｶｸ文字
		int classClientId = id;
		if ((classClientId >= 0) && (classClientId <= 57))
		{
			classClientId += 247;
		}
		else if ((classClientId >= 88) && (classClientId <= 118))
		{
			classClientId += 1071;
		}
		else if ((classClientId >= 123) && (classClientId <= 136))
		{
			classClientId += 1438;
		}
		else
			return null;
		return "&$" + classClientId + ";";
	}
	
	/**
	 * @return the class client Id formatted to be displayed on a HTML.
	 */
	public String toHtm()	//[JOJO]
	{
		return toHtm(getClassId().getId());
	}
	public String toHtmW()	//[JOJO]
	{
		return toHtmW(getClassId().getId());
	}
	public String toHtmN()	//[JOJO]
	{
		return toHtmN(getClassId().getId());
	}
	
	/**
	 * @return the class client Id formatted to be displayed on a HTML.
	 */
	public String getClientCode()
	{
		return toHtm();	//[JOJO]
	}
	
	/**
	 * @return the escaped class client Id formatted to be displayed on a HTML.
	 */
	public String getEscapedClientCode()
	{
		return toHtm();
	//	return Matcher.quoteReplacement(toHtm());
	}
	
	/**
	 * @return the server side class name.
	 */
	public String getClassServName()
	{
		return _classServName;
	}
	
	/**
	 * @return the parent class Id.
	 */
	public ClassId getParentClassId()
	{
		return _parentClassId;
	}
}