package work.model;

/**
 * Created by I320997 on 1/11/2017.
 */
public class CodeDebt {
    public String name;
    public Msr[] msr;

    @Override
    public String toString() {
        String result = "\n name:" + name +"\n";
        for(int i=0;i<msr.length;i++)
        {
            result += msr[i].toString();
        }
        return result;
    }
}
