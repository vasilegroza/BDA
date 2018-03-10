package stubs;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class AverageReducer extends Reducer<Text, IntWritable, Text, FloatWritable> {

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int wordCount = 0;
        int totalLength = 0;
        for(IntWritable value: values){
            totalLength+= value.get();
        }

        context.write(key, new FloatWritable(totalLength/wordCount));
    }
}
