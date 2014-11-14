#!/bin/sh
mkdir out
find -name "*.java" > sources.txt
javac -d out @sources.txt
jar cfm CorundumInstaller.jar MANIFEST.mf out/org/