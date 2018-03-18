package stubs;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class SumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    IntWritable occurrences;
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        this.occurrences = new IntWritable();
    }

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        for(IntWritable value: values){
            sum += value.get();
        }
        this.occurrences.set(sum);
        context.write(key, this.occurrences );

    }
}
