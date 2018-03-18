package second_job;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.log4j.Logger;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ComposeWritable implements  Writable, WritableComparable<ComposeWritable>{


    int occurrences;
    Logger LOGGER = Logger.getLogger(OccurrenceComparable.class);

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    String left;
    String right;
    public int getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(int occurrences) {
        this.occurrences = occurrences;
    }

    @Override
    public int compareTo(ComposeWritable o) {
        int res = occurrences<o.occurrences? -1 : 1;
        if(occurrences == o.occurrences)
                res = 0;
//        LOGGER.info("COMPOSE"+ res);
        return res;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(left);
        out.writeUTF(right);
        out.writeInt(occurrences);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.setLeft(in.readUTF());
        this.setRight(in.readUTF());
        this.setOccurrences(in.readInt());
    }

    @Override
    public String toString() {
        return this.left+","+this.right+ "#"+occurrences;
    }
}
