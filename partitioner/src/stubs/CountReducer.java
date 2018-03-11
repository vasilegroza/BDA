package stubs;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CountReducer extends Reducer<Text, Text, Text, IntWritable> {
    IntWritable value_hits;
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        this.value_hits = new IntWritable();
    }

    @Override
  public void reduce(Text key, Iterable<Text> values, Context context)
      throws IOException, InterruptedException {
    
	  /*
	   * TODO: implement
	   */

      int hits = 0;
      for(Text value : values){
          hits++;
      }
      this.value_hits.set(hits);

      context.write(key, this.value_hits);
  }
}
