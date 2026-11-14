@echo off
chcp 65001 > nul < nul
if exist out rmdir /s /q out
javac --release 17 -encoding UTF-8 -d out -sourcepath src src\rpg\Main.java
if errorlevel 1 exit /b 1
java -Dstdout.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -cp out rpg.Main %*
