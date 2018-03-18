package extracredit;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class SumReducerExtra extends Reducer<StringPairWritable, IntWritable, Text, IntWritable> {
    Text key_out ;
    IntWritable occurrences;
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        this.occurrences = new IntWritable();
        this.key_out = new Text();
    }

    @Override
    protected void reduce(StringPairWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        for(IntWritable value: values){
            sum += value.get();
        }
        this.occurrences.set(sum);
        this.key_out.set(key.toString());
        context.write(this.key_out, this.occurrences );

    }
}
