@echo off
color 17
cls
java -Djava.util.logging.config.file=console.cfg -cp ./../libs/*;l2jlogin.jar com.l2jserver.tools.gsregistering.BaseGameServerRegister -c
if ERRORLEVEL 1 pause
color