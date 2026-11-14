#!/usr/bin/env sh
set -e
rm -rf out
javac --release 17 -encoding UTF-8 -d out -sourcepath src src/rpg/Main.java < /dev/null
java -Dstdout.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -cp out rpg.Main "$@"
