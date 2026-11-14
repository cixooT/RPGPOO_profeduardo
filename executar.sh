#!/usr/bin/env sh
set -e
rm -rf out
javac --release 17 -encoding UTF-8 -d out -sourcepath src src/rpg/Main.java
java -cp out rpg.Main "$@"
