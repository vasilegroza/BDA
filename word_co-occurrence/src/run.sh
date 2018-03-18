#!/usr/bin/env bash
# remove old outputs
hadoop fs -rm -R shakespeare_co_occurrence

#clean last build
rm word_co.jar
rm -rf stubs/*.class
#compile java class
javac -classpath `hadoop classpath` stubs/*.java
#package class files into a jar
jar cvf word_co.jar stubs/*.class

# run Word Count job case sensitive
hadoop jar word_co.jar stubs.WordCo -Dneighbors=1 shakespeare shakespeare_co_occurrence


