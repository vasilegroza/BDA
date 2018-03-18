#!/usr/bin/env bash
# remove old outputs
#localout

#clean last build
rm avgwordlength.jar
rm -rf /stubs/*.class
#compile java class
javac -classpath `hadoop classpath` stubs/*.java
#package class files into a jar
jar cvf avgwordlength.jar stubs/*.class

# run local Word Count job case sensitive
#hadoop jar avgwordlength.jar stubs.AvgWordLength  -D mapred.reduce.task=10 -D case_sensitive=true shakespeare wordlengths 

hadoop jar avgwordlength.jar stubs.AvgWordLength -fs=file:/// -jt=local ~/training_materials/developer/data/shakespeare localout


