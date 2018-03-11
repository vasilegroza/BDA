#!/usr/bin/env bash
#remove last build
hadoop fs -rm -R uncompressedf
hadoop fs -rm -R compressedf
hadoop fs -rm -R compressedf2text
rm -rf /stubs/*.class
#compile java class
javac -classpath `hadoop classpath` stubs/*.java
#package class files into a jar

jar cvf create_seq_file.jar stubs/*.class


# run create_seq_file uncompressed mode job
hadoop jar create_seq_file.jar stubs.CreateSequenceFile weblog/access_log uncompressedf

# run create_seq_file uncompressed mode job
hadoop jar create_seq_file.jar stubs.CreateSequenceFile -Dcompress_output=true weblog/access_log compressedf

# run create_seq_file uncompressed mode job
hadoop jar create_seq_file.jar stubs.ReadCompressedSequenceFile compressedf compressedf2text