package mrunit;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import stubs.LetterMapper;

public class TestMapper {

    MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;

    @Before
    public void setUp() {
        LetterMapper mapper = new LetterMapper();
        mapDriver = new MapDriver<LongWritable, Text, Text, IntWritable>();
        mapDriver.setMapper(mapper);

    }
    @org.junit.Test
    public void testMapper() {
        mapDriver.withInput(new LongWritable(1), new Text("No now is definitely not the time"));

        mapDriver.withOutput(new Text("N"), new IntWritable(2));
        mapDriver.withOutput(new Text("n"), new IntWritable(3));
        mapDriver.withOutput(new Text("i"), new IntWritable(2));
        mapDriver.withOutput(new Text("d"), new IntWritable(10));
        mapDriver.withOutput(new Text("n"), new IntWritable(3));
        mapDriver.withOutput(new Text("t"), new IntWritable(3));
        mapDriver.withOutput(new Text("t"), new IntWritable(4));

        mapDriver.runTest();
    }
}
