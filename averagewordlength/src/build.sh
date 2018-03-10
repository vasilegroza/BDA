#clean last build
rm avgwordlength.jar
rm -rf /stubs/*.class
#compile java class
javac -classpath `hadoop classpath` stubs/*.java
#package class files into a jar
jar cvf avgwordlength.jar stubs/*.class

# run Word Count job
hadoop jar avgwordlength.jar stubs.AvgWordLength shakespeare wordlengths
