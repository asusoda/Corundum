dir /s /B *.java > sources.txt
javac -cp zip4j_1.3.2.jar @sources.txt