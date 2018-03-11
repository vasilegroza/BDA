package stubs;

import java.util.HashMap;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Partitioner;


public class MonthPartitioner<K2, V2> extends Partitioner<Text, Text> implements
        Configurable {

  private Configuration configuration;
  HashMap<String, Integer> months = new HashMap<String, Integer>();

  /**
   * Set up the months hash map in the setConf method.
   */
  @Override
  public void setConf(Configuration configuration) {
    /*
     * Add the months to a HashMap.
     */
//1. January – Jan. 2 . February – Feb. 3. March – Mar. 4. April – Apr. 5. May – May 6. June – Jun. 7. July – Jul.
// 8. August – Aug. 9. September – Sep. or Sept. 10. October – Oct. 11. November – Nov. 12. December – Dec

    /*
     * TODO implement
     */
    this.months.put("Jan",0);
    this.months.put("Feb",1);
    this.months.put("Mar",2);
    this.months.put("Apr",3);
    this.months.put("May",4);
    this.months.put("Jun",5);
    this.months.put("Jul",6);
    this.months.put("Aug",7);
    this.months.put("Sep",8);
    this.months.put("Oct",9);
    this.months.put("Nov",10);
    this.months.put("Dec",11);

  }

  /**
   * Implement the getConf method for the Configurable interface.
   */
  @Override
  public Configuration getConf() {
    return configuration;
  }

  /**
   * You must implement the getPartition method for a partitioner class.
   * This method receives the three-letter abbreviation for the month
   * as its value. (It is the output value from the mapper.)
   * It should return an integer representation of the month.
   * Note that January is represented as 0 rather than 1.
   *
   * For this partitioner to work, the job configuration must have been
   * set so that there are exactly 12 reducers.
   */
  public int getPartition(Text key, Text value, int numReduceTasks) {
    /*
     * TODO implement
     * Change the return 0 statement below to return the number of the month
     * represented by the value parameter.  Use to months hashtable to map
     * the string to the month number: months.get(value.toString()) and cast it to int.
     */
    return (int) months.get(value.toString());
  }
}


