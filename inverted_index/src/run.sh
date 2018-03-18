#!/usr/bin/env bash
# remove old outputs
hadoop fs -rm -R invertedIndexOutput

#clean last build
rm invert_index.jar
rm -rf /stubs/*.class
#compile java class
javac -classpath `hadoop classpath` stubs/*.java
#package class files into a jar
jar cvf invert_index.jar stubs/*.class

# run Word Count job case sensitive
hadoop jar invert_index.jar stubs.InvertedIndex  invertedIndexInput invertedIndexOutput


