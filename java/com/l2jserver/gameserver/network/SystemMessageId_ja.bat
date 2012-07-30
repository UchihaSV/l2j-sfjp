CD /D %~dp0
svn revert SystemMessageId.java ||@(PAUSE&GOTO :EOF)

PUSHD ..\..\..\..\..\
svn patch java\com\l2jserver\gameserver\network\SystemMessageId_ja.diff ||@(PAUSE&GOTO :EOF)
POPD

perl SystemMessageId_ja.pl ||@(PAUSE&GOTO :EOF)
