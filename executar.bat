@echo off
chcp 65001 > nul
if exist out rmdir /s /q out
javac --release 17 -encoding UTF-8 -d out -sourcepath src src\rpg\Main.java
if errorlevel 1 exit /b 1
java -cp out rpg.Main %*
