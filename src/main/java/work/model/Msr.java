package work.model;

/**
 * Created by I320997 on 1/11/2017.
 */
/*
 "key": "sqale_index",
        "val": 0.0,
        "frmt_val": "0"
 */
public class Msr {
    public String key;
    public float val;
    public String frmt_val;

    @Override
    public String toString() {
        return "key:"+key +"\n"
                + "val:" + val + "\n"
                + "frmt_val:" + frmt_val + "\n";
    }
}

