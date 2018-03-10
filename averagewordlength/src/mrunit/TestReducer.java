package mrunit;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import stubs.AverageReducer;

import java.util.ArrayList;
import java.util.List;

public class TestReducer {

    ReduceDriver<Text, IntWritable, Text, DoubleWritable> reduceDriver;

    @Before
    public void setUp() {
        AverageReducer reducer = new AverageReducer();
        reduceDriver = new ReduceDriver<Text, IntWritable, Text, DoubleWritable>();
        reduceDriver.setReducer(reducer);

    }

    @org.junit.Test
    public void testMapper() {
//            N (2)
//            d (10)
//            i (2)
//            n (3,3)
//            t (3,4)

        List<IntWritable> values5 = new ArrayList<IntWritable>();

        values5.add(new IntWritable(3));
        values5.add(new IntWritable(4));


        reduceDriver.withInput(new Text("t"), values5);



//        N 2.0
//        d 10.0
//        i 2.0
//        n 3.0
//        t 3.5
        reduceDriver.withOutput(new Text("t"), new DoubleWritable(3.5));

        reduceDriver.runTest();
    }

}
