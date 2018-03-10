package stubs;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;


public class LetterMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    Text character;
    IntWritable word_length;
    boolean case_sensitive;
    private static final Logger LOGGER = Logger.getLogger(LetterMapper.class.getName());

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        Configuration conf = context.getConfiguration();
        this.case_sensitive = conf.getBoolean("case_sensitive", false);

        LOGGER.info("CASE_SENSITIVE = "+ String.valueOf(this.case_sensitive));

        this.character = new Text();
        this.word_length = new IntWritable();
    }

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        String line = value.toString();

        for (String word : line.split("\\W+")) {
            if (word.length() > 0) {
                if (this.case_sensitive) {
                    this.character.set(word.substring(0, 1));
                } else {
                    this.character.set(word.substring(0, 1).toLowerCase());
                }
                this.word_length.set(word.length());
                context.write(this.character, this.word_length);
            }
        }
    }
}
