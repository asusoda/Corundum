#!/bin/sh
mkdir out
find -name "*.java" > sources.txt
javac -cp zip4j_1.3.2.jar -d out @sources.txt
jar cfm CorundumInstaller.jar MANIFEST.mf out/