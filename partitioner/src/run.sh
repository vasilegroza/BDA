#!/usr/bin/env bash
#remove last build
hadoop fs -rm -R partitioner
rm -rf /stubs/*.class
#compile java class
javac -classpath `hadoop classpath` stubs/*.java
#package class files into a jar

jar cvf part.jar stubs/*.class

# run Word Count job
hadoop jar part.jar stubs.ProcessLogs  weblog/access_log partitioner