#!/usr/bin/env bash
#remove last build
hadoop fs -rm -R name_count
rm -rf /stubs/*.class
#compile java class
javac -classpath `hadoop classpath` stubs/*.java
#package class files into a jar

jar cvf writable.jar stubs/*.class

rm -rf /stubs/*.class

# run custom writable key count job
hadoop jar writable.jar stubs.StringPairTestDriver nameyeartestdata name_count