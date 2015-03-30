CD /D %~dp0
svn revert SystemMessageId.java ||@(PAUSE&GOTO :EOF)
@SET REV=
@FOR /F usebackq %%I IN (`svnversion -n SystemMessageId.java`) DO SET REV=%%I
@ECHO [%REV%]
@ECHO ### Eclipse Workspace Patch 1.0>###.patch
@ECHO #P L2J_Server_BETA>>###.patch

PUSHD ..\..\..\..\..\
svn patch java\com\l2jserver\gameserver\network\SystemMessageId_ja.patch ||@(PAUSE&GOTO :EOF)
svn diff  java\com\l2jserver\gameserver\network\SystemMessageId.java>>java\com\l2jserver\gameserver\network\###.patch
POPD

perl SystemMessageId_ja.pl ||@(PAUSE&GOTO :EOF)

fc /L  ###.patch SystemMessageId_ja.patch || (
RENAME SystemMessageId_ja.patch SystemMessageId_ja.patch.%REV%.BAK
COPY   ###.patch SystemMessageId_ja.patch
RENAME ###.patch SystemMessageId_ja.patch.#r%REV%
)
