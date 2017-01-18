package work.outputModel;

/**
 * Created by I320997 on 1/16/2017.
 */
public class FunctionalQualityResponse {

    public String moduleName;
    public int issues =0;
    public String coverage;

    public FunctionalQualityResponse(String moduleName, int issues, String coverage) {
        this.moduleName = moduleName;
        this.issues = issues;
        this.coverage = coverage;
    }
}
