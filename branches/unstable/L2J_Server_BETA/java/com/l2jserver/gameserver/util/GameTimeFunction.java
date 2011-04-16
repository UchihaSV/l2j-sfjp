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
package com.l2jserver.gameserver.util;

import com.l2jserver.gameserver.GameTimeController;

/**
 * @author  JOJO
 */
public class GameTimeFunction
{
	public static final long DAYMILI = 14400*1000;	// �Q�[�������Ԃ̂P���̒����������Ԃł���킵��[�~���b]
	public static final long TIMEMILI = 600*1000;	// �Q�[�������Ԃ̂P���Ԃ̒����������Ԃł���킵��[�~���b]
	public static final long MINUTEMILI = 10*1000;	// �Q�[�������Ԃ̂P���̒����������Ԃł���킵��[�~���b]

	/**
	 * @param hh,mm,ss �Q�[�������Ԃ̎����b
	 * @return ���̎��̎����b�܂ŁA���Ɖ��~���b���B�����Ԃł̃~���b�ŕԂ��B
	 */
	public static long timeLeftMili(int hh, int mm, int ss)
	{
		int now = GameTimeController.getGameTicks() * 60 / 100;	//�Q�[��������[�b]
		int dd = (hh * 3600 + mm * 60 + ss) - (now % 86400);
		if (dd < 0) dd += 86400;

		return (long)dd * 1000 / 6;	//���A�����Ԃɕϊ�
	}
}
