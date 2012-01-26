@TITLE %~n0
java -Djava.util.logging.config.file=console.cfg -cp ./../libs/*;l2jlogin.jar com.l2jserver.tools.gsregistering.GameServerRegister
@IF ERRORLEVEL 1 PAUSE
