package work.model;

/**
 * Created by I320997 on 1/13/2017.
 */
public class TechIssue {
    public String total;
    public Issue[] issues;

    @Override
    public String toString() {
        return total +"success  "+issues.length;
    }
}
