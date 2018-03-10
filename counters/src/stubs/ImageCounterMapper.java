package stubs;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;

/**
 * Example input line:
 * 96.7.4.14 - - [24/Apr/2011:04:20:11 -0400] "GET /cat.jpg HTTP/1.1" 200 12433
 *
 */


public class ImageCounterMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private static final Logger LOGGER = Logger.getLogger(ImageCounter.class.getName());
    @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {

    /*
     * TODO implement
     */
      //10.211.47.159 - - [29/Aug/2009:14:17:54 -0700] "GET /assets/img/search-button.gif HTTP/1.1" 200 168

      String line = value.toString();
      String access_log_pattern = getAccessLogRegex();
      Pattern pattern = Pattern.compile(access_log_pattern);
      Matcher matcher = pattern.matcher(line);
//      LOGGER.debug("MATCHER"+matcher.find() + " " + line+ "\n\t"+ access_log_pattern );
      if(matcher.find()){
//            LOGGER.debug("REQ"+matcher.group(5));
            String request = matcher.group(5);
            String resource = request.split(" ")[1];
            if(resource.endsWith("gif")){
                context.getCounter("image", "gif").increment(1);
            }
            else if (resource.endsWith("jpeg")){

                context.getCounter("image", "jpeg").increment(1);

            }else
            {

                context.getCounter("image", "other").increment(1);
            }
      }

  }

    static String getAccessLogRegex()
    {
//        ^([\d.]+) (\S+) (\S+) \[(.*?)\] "(.+?)" (\d{3}) (\d+|(.+?))

        String regex1 = "([\\d.]+)"; // Client IP
        String regex2 = " (\\S+)"; // -
        String regex3 = " (\\S+)"; // -
        String regex4 = " \\[(.*?)\\]"; // Date
        String regex5 = " \"(.+?)\""; // request method and url
        String regex6 = " (\\d{3})"; // HTTP code
        String regex7 = " (\\d+|(.+?))"; // Number of bytes

        return regex1+regex2+regex3+regex4+regex5+regex6+regex7;
    }
}
