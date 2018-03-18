package stubs;
import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class IndexMapper extends Mapper<Text, Text, Text, Text> {
  Text word;
  Text file_in;
  @Override
  protected void setup(Context context) throws IOException, InterruptedException {
    this.word = new Text();
    this.file_in = new Text();

  }

  @Override
  public void map(Text key, Text value, Context context) throws IOException,
      InterruptedException {

    /*
     * TODO implement
     */

    for(String word: value.toString().split("\\W")){
      if (word.length()>0){
        FileSplit fileSplit = (FileSplit) context.getInputSplit();
        Path path = fileSplit.getPath();
        String fileName = path.getName();

        this.word.set(word);
        this.file_in.set(fileName+"@"+key);
        context.write(this.word, this.file_in);
      }
    }

  }
}