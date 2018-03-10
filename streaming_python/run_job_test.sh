hadoop jar /usr/lib/hadoop-0.20-mapreduce/\
contrib/streaming/hadoop-streaming*.jar \
-input mapper_test_data  -output streaming/python/test \
-file ./word_mapper.py -file ./word_reducer.py \
-mapper ./word_mapper.py -reducer ./word_reducer.py
