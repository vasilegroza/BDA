package second_job;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
import org.apache.log4j.Logger;
import second_job.ComposeWritable;

public class OccurrenceComparable extends WritableComparator {
    Logger LOGGER = Logger.getLogger(OccurrenceComparable.class);
    protected OccurrenceComparable() {
        super(ComposeWritable.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        ComposeWritable k1 = (ComposeWritable) a;
        ComposeWritable k2 = (ComposeWritable) b;
        int res = k1.compareTo(k2);
//        LOGGER.info("COMPARATOR" + -1*res);
        return -1*res;

    }
}
