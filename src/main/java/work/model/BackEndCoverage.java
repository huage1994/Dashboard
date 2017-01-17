package work.model;

import java.util.Arrays;

/**
 * Created by I320997 on 1/16/2017.
 */
public class BackEndCoverage {

    public String name;
    public Msr[] msr;

    @Override
    public String toString() {
        return "BackEndCoverage{" +
                "name='" + name + '\'' +
                ", msr=" + Arrays.toString(msr) +
                '}';
    }
}
