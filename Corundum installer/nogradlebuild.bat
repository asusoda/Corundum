mkdir out
dir /s /B *.java > sources.txt
javac -d out @sources.txt
jar cmf MANIFEST.mf CorundumInstaller.jar out/org/