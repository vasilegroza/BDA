package stubs;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.compress.SnappyCodec;
import org.apache.hadoop.mapreduce.Job;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.Logger;

public class CreateSequenceFile extends Configured implements Tool {
    Logger LOGGER = Logger.getLogger(CreateSequenceFile.class);
    @Override
    public int run(String[] args) throws Exception {

        if (args.length != 2) {
            System.out.printf("Usage: CreateSequenceFile <input dir> <output dir>\n");
            return -1;
        }

        Job job = new Job(getConf());
        job.setJarByClass(CreateSequenceFile.class);
        job.setJobName("Create Sequence File");

        /*
         * TODO implement
         */
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setOutputFormatClass(SequenceFileOutputFormat.class);

        boolean compress = getConf().getBoolean("compress_output", false);
        LOGGER.info("COMPRESS_OUTPUT="+ compress);
        if (compress){
            FileOutputFormat.setCompressOutput(job,compress);
            FileOutputFormat.setOutputCompressorClass(job, SnappyCodec.class);
            SequenceFileOutputFormat.setOutputCompressionType(job, SequenceFile.CompressionType.BLOCK);
        }

        job.setNumReduceTasks(0);

        boolean success = job.waitForCompletion(true);
        return success ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new Configuration(), new CreateSequenceFile(), args);
        System.exit(exitCode);
    }
}
