@TITLE %~n0
java -Djava.util.logging.config.file=console.cfg -cp ./../libs/*;l2jserver.jar;l2jlogin.jar com.l2jserver.tools.configurator.ConfigUserInterface
@IF ERRORLEVEL 1 PAUSE
