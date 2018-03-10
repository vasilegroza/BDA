package stubs;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * Example input line:
 * 96.7.4.14 - - [24/Apr/2011:04:20:11 -0400] "GET /cat.jpg HTTP/1.1" 200 12433
 */
public class LogFileMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        /*
         * TODO implement
         */



        String line = value.toString();
        String ip_regex = "(?:[0-9]{1,3}\\.){3}[0-9]{1,3}";
        Pattern pattern = Pattern.compile(ip_regex);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find())
        {
            context.write(new Text(matcher.group()), new IntWritable(1));
        }

    }
}