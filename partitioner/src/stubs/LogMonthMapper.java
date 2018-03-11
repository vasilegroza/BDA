package stubs;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class LogMonthMapper extends Mapper<LongWritable, Text, Text, Text> {


  Text key_ip;
  Text value_month;
  @Override
  protected void setup(Context context) throws IOException, InterruptedException {
    this.key_ip = new Text();
    this.value_month = new Text();
  }

  /**
   * Example input line:
   * 96.7.4.14 - - [24/Apr/2011:04:20:11 -0400] "GET /cat.jpg HTTP/1.1" 200 12433
   *
   */
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    
	  /* TODO: implement */
    String line = value.toString();
    String access_log_pattern = getAccessLogRegex();
    Pattern pattern = Pattern.compile(access_log_pattern);
    Matcher matcher = pattern.matcher(line);
//      LOGGER.debug("MATCHER"+matcher.find() + " " + line+ "\n\t"+ access_log_pattern );
    if(matcher.find()){
      String month = matcher.group(4).split("/")[1];
      this.key_ip.set(matcher.group(1));
      this.value_month.set(month);

      context.write(key_ip, value_month);
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
