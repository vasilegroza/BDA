#!/usr/bin/env bash
# remove old outputs
hadoop fs -rm -R shakespeare_co_extra
hadoop fs -rm -R co_sorted_asc
hadoop fs -rm -R co_sorted_desc

#clean last build
rm word_co_extra.jar
rm sort_co.jar
rm -rf ./*.class
#compile java class
javac -classpath `hadoop classpath` extracredit/*.java
javac -classpath `hadoop classpath` second_job/*.java

#package class files into a jar
jar cvf word_co_extra.jar extracredit/*.class
jar cvf sort_co.jar second_job/*.class

rm -rf ./*.class
# run Word Count job case sensitive
hadoop jar word_co_extra.jar extracredit.WordCo -Dneighbors=1 shakespeare shakespeare_co_extra

hadoop jar sort_co.jar second_job.SecondSortDriver -Dord=ASC shakespeare_co_extra co_sorted_asc
hadoop jar sort_co.jar second_job.SecondSortDriver -Dord=DESC shakespeare_co_extra co_sorted_desc

