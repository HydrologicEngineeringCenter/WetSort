:: compile
mkdir build
javac -d build src\wetsort\*.java src\wetsort\controls\*.java src\wetsort\util\*.java -cp  "jar/*"

:: make jar
cd build
jar cvf wetsort.jar *
copy wetsort.jar ..\jar
cd ..
