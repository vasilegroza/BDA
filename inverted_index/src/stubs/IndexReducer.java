package stubs;
import java.io.IOException;

import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Reducer;

/**
 * On input, the reducer receives a word as the key and a set
 * of locations in the form "play name@line number" for the values. 
 * The reducer builds a readable string in the valueList variable that
 * contains an index of all the locations of the word. 
 */
public class IndexReducer extends Reducer<Text, Text, Text, Text> {
  Text output;

  @Override
  protected void setup(Context context) throws IOException, InterruptedException {
    this.output = new Text();
  }

  @Override
  public void reduce(Text key, Iterable<Text> values, Context context)
      throws IOException, InterruptedException {

    /*
     * TODO implement
     */

    this.output.set(concatWithCommas(values));
    context.write(key,this.output);


    
  }
public static String concatWithCommas(Iterable<Text> words) {
    StringBuilder wordList = new StringBuilder();
    for (Text word : words) {
        wordList.append(word.toString() + ",");
    }
    return new String(wordList.deleteCharAt(wordList.length() - 1));
}
}
