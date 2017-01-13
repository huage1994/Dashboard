package work.model;

/**
 * Created by I320997 on 1/13/2017.
 */
public class TechIssue {
    public String total;
    public Issue[] issues;

    @Override
    public String toString() {
        String xx = issues.length==0?"sss":issues[0].severity;
        return total +"success  "+ xx;
    }
}
