package stubs;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class ImageCounter extends Configured implements Tool {

  @Override
  public int run(String[] args) throws Exception {

    if (args.length != 2) {
      System.out.printf("Usage: ImageCounter <input dir> <output dir>\n");
      return -1;
    }

    Job job = new Job(getConf());
    job.setJarByClass(ImageCounter.class);
    job.setJobName("Image Counter");

    /*
     * TODO implement
     */

    FileInputFormat.setInputPaths(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    job.setMapperClass(ImageCounterMapper.class);
    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(IntWritable.class);

    job.setNumReduceTasks(0);

    System.out.println("Waiting gor jobs to end");
    boolean success = job.waitForCompletion(true);
    if (success) {
      /*
       * Print out the counters that the mappers have been incrementing.
       * TODO implement
       */
        long gif_number = job.getCounters().findCounter("image", "gif").getValue();
        long jpeg_number = job.getCounters().findCounter("image", "jpeg").getValue();
        long other_number = job.getCounters().findCounter("image", "other").getValue();

        System.out.println("GIF = " + gif_number);
        System.out.println("JPEG = " + jpeg_number);
        System.out.println("OTHER = " + other_number);

      return 0;
    } else
      return 1;

  }

  public static void main(String[] args) throws Exception {
    int exitCode = ToolRunner.run(new Configuration(), new ImageCounter(), args);
    System.exit(exitCode);
  }
}