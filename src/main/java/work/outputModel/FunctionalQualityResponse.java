package work.outputModel;

/**
 * Created by I320997 on 1/16/2017.
 */
public class FunctionalQualityResponse {

    public String moduleName;
    public String issues;
    public String coverage;

    public FunctionalQualityResponse(String moduleName, String issues, String coverage) {
        this.moduleName = moduleName;
        this.issues = issues;
        this.coverage = coverage;
    }
}
