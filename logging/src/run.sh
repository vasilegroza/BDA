# remove old outputs
hadoop fs -rm -R wordlengths
hadoop fs -rm -R wordlengths_1

#clean last build
rm avgwordlength.jar
rm -rf /stubs/*.class
#compile java class
javac -classpath `hadoop classpath` stubs/*.java
#package class files into a jar
jar cvf avgwordlength.jar stubs/*.class

# run Word Count job case sensitive
hadoop jar avgwordlength.jar stubs.AvgWordLength -D mapred.reduce.task=10 -D case_sensitive=true -D mapred.map.child.log.level=INFO shakespeare wordlengths 

# run Word Count job case insensitive
#hadoop jar avgwordlength.jar stubs.AvgWordLength  -D mapred.reduce.task=10 -D case_sensitive=false shakespeare wordlengths_1

