#!/usr/bin/env bash
#remove last build
hadoop fs -rm -R imagecount
rm -rf /stubs/*.class
#compile java class
javac -classpath `hadoop classpath` stubs/*.java
#package class files into a jar

jar cvf ic.jar stubs/*.class

# run Word Count job
hadoop jar ic.jar stubs.ImageCounter -D mapred.map.child.log.level=INFO weblog/access_log imagecount