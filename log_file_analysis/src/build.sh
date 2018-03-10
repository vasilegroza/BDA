#remove last build
rm -rf /stubs/*.class
#compile java class
javac -classpath `hadoop classpath` stubs/*.java
#package class files into a jar

jar cvf wc.jar stubs/*.class

# run Word Count job
hadoop jar wc.jar stubs.WordCount shakespeare wordcounts

