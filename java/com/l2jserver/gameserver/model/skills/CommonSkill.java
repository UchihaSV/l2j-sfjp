/*
 * Copyright (C) 2004-2014 L2J Server
 * 
 * This file is part of L2J Server.
 * 
 * L2J Server is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J Server is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.gameserver.model.skills;

import com.l2jserver.gameserver.datatables.SkillData;

/**
 * An Enum to hold some important references to commonly used skills
 * @author DrHouse
 */
public enum CommonSkill
{
	RAID_CURSE(4215, 1),	// ���C�h�̎� - ���C�h �K�[�f�B�A���̎􂢂ɂ�����A��莞�ԁA�����A���@�X�L���̂��ׂĂ�������ꂽ��ԁB������背�x����9�ȏ�Ⴂ���C�h �����X�^�[��|�����Ƃ��鎞�ɂ�����A�􂢂��������Ƃ͂ł��܂���B
	RAID_CURSE2(4515, 1),	// ���C�h�̎� - ���C�h �K�[�f�B�A���̎􂢁B�Ή��ɂ������āA�ꎞ�I�ɑ̂��΂̂悤�Ɍł܂��Ă����ԁB������背�x����9�ȏ�Ⴂ���C�h �����X�^�[��|�����Ƃ��鎞�ɂ�����A�������Ƃ͂ł��܂���B
	SEAL_OF_RULER(246, 1),	// �V�[�� �I�u ���[���[ - �����삷��Ñ�̐����ɑ΂���x�z�҂Ƃ��č��󂳂��܂��B
	BUILD_HEADQUARTERS(247, 1),	// �w�n�\�z - �U���̍ہA�������̉񕜂Ɛ��������������w�n���\�z���܂��B�W�F���X�g�[���FC�O���[�h300�����Ղ��܂��B
	WYVERN_BREATH(4289, 1),	// ���C�o�[�� �u���X - ���𕬂��A�����̓G���U�����܂��B�^�[�Q�b�g���牓���Ȃ���_���[�W���������܂��B
	STRIDER_SIEGE_ASSAULT(325, 1),	// �X�g���C�_�[ �V�[�W �A�T���g - 15005�̈З͂ŏ����U�����܂��B�X�g���C�_�[���掞�Ɏg�p�ł��܂��B���[���X�g�[����5���Ղ��܂��B
	FIREWORK(5965, 1),	// �ԉ�
	LARGE_FIREWORK(2025, 1),	// ��ԉ�
	BLESSING_OF_PROTECTION(5182, 1),	// �ی�̏j�� - 10���x���ȏ㍷�̂���J�I�e�B�b�N�����̃L�����N�^�[�̍U��������S�ɐg�����܂��B
	VOID_BURST(3630, 1),	// �{�C�h �o�[�X�g - ���ꂽ�C���������̓G���U�����܂��B
	VOID_FLOW(3631, 1),	// �{�C�h �t���[ - ���ꂽ�C���Ïk���������̓G�ɑŌ���^���܂��B
	THE_VICTOR_OF_WAR(5074, 1),	// �헐�̏��� - �ő�CP������������ԁB
	THE_VANQUISHED_OF_WAR(5075, 1),	// �헐�̔s�� - �ő�CP������������ԁB
	SPECIAL_TREE_RECOVERY_BONUS(2139, 1),	// �����c���[�̉񕜃{�[�i�X - �N���X�}�X �C�x���g�̍����c���[���ʁBHP�񕜃{�[�i�X�AMP�񕜃{�[�i�X�����サ����ԁB
	WEAPON_GRADE_PENALTY(6209, 1),	// Lv.1�`4 ����O���[�h �y�i���e�B - �����̃��x���ɍ���Ȃ��O���[�h�̕���𑕒��������߁A��������16�A�N���e�B�J���m������%�A�N���e�B�J���З͂���%�A�U�����x��10%�A�U���͂�10%�ቺ����y�i���e�B���󂯂Ă��܂��B
	ARMOR_GRADE_PENALTY(6213, 1),	// Lv.1�`4 �h��O���[�h �y�i���e�B - �����̃��x���ɍ���Ȃ��O���[�h�̖h��A�A�N�Z�T���[�𑕒��������߁A��𗦂����A�U�����x�A���@�r�����x�A�ړ����x����%�ቺ����y�i���e�B���󂯂Ă��܂��B
	CREATE_DWARVEN(172, 1),	// Lv.1�`10 �N���G�C�g �A�C�e�� - ���X�e�b�v�̃A�C�e���������ł��܂��B
	LUCKY(194, 1),	// ���b�L�[ - LV9�ȉ��ł̎��S���ɁA�o���l�̌����Ǝ��������炷���̉e�����󂯂�̂�h���A�o�C�^���e�B �|�C���g�������܂���B
	EXPERTISE(239, 1),	// Lv.1�`7 �G�L�X�p�[�e�B�[�Y �� - ���O���[�h�ȉ��̑����̈����Ɋ���܂��B
	CRYSTALLIZE(248, 1),	// Lv.1�`5 �N���X�^���C�Y - ���O���[�h�̃N���X�^���C�Y���\�ɂ��܂��B
	CLAN_LUCK(390, 1),	// Lv.1�`3 �N���� �f�X �t�H�[�`���� - ��������PK�A��ʃ����X�^�[�ɓ|���ꂽ�ꍇ�̌o���l�̌������Ǝ��������炷���̉e�����󂯂�m�����Ⴍ�Ȃ�܂��B�]�m�ȏォ����͂��K�p����܂��B
	ONYX_BEAST_TRANSFORMATION(617, 1),	// �g�����X�t�H�[�� �I�j�L�X �r�[�X�g - �I�j�L�X �r�[�X�g�ɕϐg���܂��B
	CREATE_COMMON(1320, 1),	// Lv.1�`9 �N���G�C�g �R���� �A�C�e�� - ���X�e�b�v�̈�ʃA�C�e���������ł��܂��B
	DIVINE_INSPIRATION(1405, 1),	// Lv.1�`4 �C���N���[�X �I�u �f�B�r�j�e�B - �l�̎󂯂��鋭�����@�̐������������܂��B
	SERVITOR_SHARE(1557, 1),	// �V�F�A�����O �A�r���e�B - �����t�̔\�͂������b�Ɉڂ�܂��B�ڂ�̂͏����t�̍U���́A�h��͂�50%�A���́A���@��R��25%�A�ő�HP��MP��10%�A�N���e�B�J���m����20%�A�U�����x��10%�A���@�r�����x��3%�ł��B
	CARAVANS_SECRET_MEDICINE(2341, 1),	// �L�����o���̔�� - �����̔M�C�ɖƉu�����Ă����ԁB
	;
	
	private final int _id, _skillHashCode;
	
	private CommonSkill(int id, int level)
	{
		_id = id;
		_skillHashCode = SkillData.getSkillHashCode(id, level);
	}
	
	public int getId()
	{
		return _id;
	}
	
	public int getLevel()
	{
		return SkillData.getSkillLevel(_skillHashCode);
	}
	
	public Skill getSkill()
	{
		return SkillData.getSkill(_skillHashCode);
	}
}