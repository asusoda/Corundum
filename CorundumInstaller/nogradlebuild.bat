mkdir out
dir /s /B *.java > sources.txt
javac -cp zip4j_1.3.2.jar -d out @sources.txt
jar cmf MANIFEST.mf CorundumInstaller.jar out/