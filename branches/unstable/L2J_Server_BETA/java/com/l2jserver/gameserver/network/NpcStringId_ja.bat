CD /D %~dp0
svn revert NpcStringId.java ||@(PAUSE&GOTO :EOF)

PUSHD ..\..\..\..\..\
svn patch java\com\l2jserver\gameserver\network\NpcStringId_ja.diff ||@(PAUSE&GOTO :EOF)
POPD

perl NpcStringId_ja.pl ||@(PAUSE&GOTO :EOF)
