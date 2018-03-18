package extracredit;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class StringPairWritable implements WritableComparable<StringPairWritable>{
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

    @Override
    public String toString() {
        return left + ","+right;
    }

    String left;
    String right;
    public StringPairWritable(){

    }

    public StringPairWritable(String left, String right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(StringPairWritable o) {
        int res = left.compareTo(o.left);
        if(res == 0)
            res = right.compareTo(right);
        return res;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(this.left);
        out.writeUTF(this.right);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.left = in.readUTF();
        this.right = in.readUTF();

    }
}
