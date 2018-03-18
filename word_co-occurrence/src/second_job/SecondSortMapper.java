package second_job;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SecondSortMapper extends Mapper<LongWritable, Text, ComposeWritable, IntWritable> {
    ComposeWritable composePair;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        this.composePair = new ComposeWritable();
    }

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        /*
         * TODO implement
         */
        String line = value.toString();
        IntWritable val =  new IntWritable(Integer.parseInt(line.split("\t")[1]));
        this.composePair.setLeft((line.split("\t")[0].split(",")[0]));
        this.composePair.setRight((line.split("\t")[0].split(",")[1]));
        this.composePair.setOccurrences(val.get());
        context.write(composePair, val);
    }

}
