package stubs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCoMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    IntWritable ONE = new IntWritable(1);
    Text new_key = new Text();
    int neighbors;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        this.neighbors = context.getConfiguration().getInt("neighbors", 2);
    }

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        /*
         * TODO implement
         */
        List<String> words= new ArrayList<String>();
        String[] tokens = value.toString().split("\\W+");
        for(String token:tokens){
            if(token.length()>0){
                words.add(token);
            }
        }
        int start, end;
        for(int i=0; i<words.size(); i++){
            //compute the start and the end of neighbors
            // for neighbors = 2
            // - x n n *****
            // *** n x n ***
            // ******n n x -
            StringBuilder wordList = new StringBuilder();
            start = (i - neighbors) < 0 ? 0: i-neighbors;
            end = (i + neighbors) >= words.size() ? words.size()-1: i + (neighbors);

            wordList.append(words.get(i));
            for(int j = start; j<=end; j++){
                if(i==j)
                    continue;
                wordList.append(','+ words.get(j));
            }
            new_key.set(wordList.toString());
            context.write(new_key, ONE);
        }

    }

}
