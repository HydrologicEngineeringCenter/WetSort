echo on
:: java -cp MyJar.jar com.mycomp.myproj.dir2.MainClass2 /home/myhome/datasource.properties /home/myhome/input.txt

java -Djava.library.path=%cd%/jar -cp jar\*  wetsort.Main 

