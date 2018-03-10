echo "Clean last build...\n"
#remove output directory from hadoop fs
hadoop fs -rm -R wordcounts

#remove last build
rm -rf /stubs/*.class
rm -rf ./wc.jar
echo "Compile java files and build jar\n"
#compile java class
javac -classpath `hadoop classpath` stubs/*.java
#package class files into a jar

jar cvf wc.jar stubs/*.class

# run Word Count job
hadoop jar wc.jar stubs.WordCount -D use_combinr=false shakespeare wordcounts

