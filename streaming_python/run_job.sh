hadoop jar /usr/lib/hadoop-0.20-mapreduce/\
contrib/streaming/hadoop-streaming*.jar \
-input shakespeare  -output streaming/python/word \
-file ./word_mapper.py -file ./word_reducer.py \
-mapper ./word_mapper.py -reducer ./word_reducer.py
