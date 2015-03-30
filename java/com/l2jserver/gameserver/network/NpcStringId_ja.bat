CD /D %~dp0
svn revert NpcStringId.java ||@(PAUSE&GOTO :EOF)
@SET REV=
@FOR /F usebackq %%I IN (`svnversion -n NpcStringId.java`) DO SET REV=%%I
@ECHO [%REV%]
@ECHO ### Eclipse Workspace Patch 1.0>###.patch
@ECHO #P L2J_Server_BETA>>###.patch

PUSHD ..\..\..\..\..\
svn patch java\com\l2jserver\gameserver\network\NpcStringId_ja.patch ||@(PAUSE&GOTO :EOF)
svn diff  java\com\l2jserver\gameserver\network\NpcStringId.java>>java\com\l2jserver\gameserver\network\###.patch
POPD

perl NpcStringId_ja.pl ||@(PAUSE&GOTO :EOF)

fc /L  ###.patch NpcStringId_ja.patch || (
RENAME NpcStringId_ja.patch NpcStringId_ja.patch.%REV%.BAK
COPY   ###.patch NpcStringId_ja.patch
RENAME ###.patch NpcStringId_ja.patch.#r%REV%
)
