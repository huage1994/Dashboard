package work.outputModel;

/**
 * Created by I320997 on 1/18/2017.
 */
public class APITestResponse {

    public String moduleName;
    public int issues;
    public int cases;

    public APITestResponse(String moduleName, int issues, int cases) {
        this.moduleName = moduleName;
        this.issues = issues;
        this.cases = cases;
    }


}
