package work.controller;

import work.model.BackEndCoverage;
import work.model.CodeDebt;
import work.model.JenkinsStatus;
import work.model.TechIssue;
import work.outputModel.APITestResponse;
import work.outputModel.CodeDebtResponse;
import work.outputModel.FunctionalQualityResponse;
import work.outputModel.TechIssueResponse;
import work.utils.ComponentName;
import work.utils.TransferData;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by I320997 on 1/9/2017.
 */
@RequestMapping("/api")
@RestController
public class RestfulController {
    private static final String template = "work %s";
    private final AtomicLong counter = new AtomicLong();

//    @RequestMapping("/greeting")
//    public Greeting greeting(@RequestParam(value="name",defaultValue = "woooood") String name){
//            return new Greeting(counter.incrementAndGet(),
//                     String.format(template,name));
//    }

    @RequestMapping("/api2/{helloID}")
    public String api2(@PathVariable String helloID) {

        String result = "work  " + helloID;

        return result;
    }

    @RequestMapping("/codeDebt")
    public CodeDebt[] getCodeDebt() {
        CodeDebt[] codeDebt = (CodeDebt[]) TransferData.getTrans(ComponentName.codeDebts);
        return codeDebt;
    }

    @RequestMapping("/codeQuality/detail/codeDebt")
    public CodeDebtResponse[] getCodeDebtResponse() {
        CodeDebtResponse[] codeDebtResponses = new CodeDebtResponse[3];
        CodeDebt[] codeDebt = (CodeDebt[]) TransferData.getTrans(ComponentName.codeDebts);
        float sum = 0;
        for (int i = 0; i < codeDebt.length; i++) {
            sum += codeDebt[i].msr[0].val;
        }
        System.out.println(sum);

        codeDebtResponses[0] = new CodeDebtResponse("Design", codeDebt[0], codeDebt[1], sum);
        codeDebtResponses[1] = new CodeDebtResponse("Quality", codeDebt[2], codeDebt[3], sum);
        codeDebtResponses[2] = new CodeDebtResponse("Collaboration", codeDebt[4], codeDebt[5], sum);

        return codeDebtResponses;
    }

    @RequestMapping("/codeQuality/detail/techIssue")
    public TechIssueResponse[] getTechIssueResponse() {
        TechIssueResponse[] techIssueResponses = new TechIssueResponse[6];
        TechIssue[] techIssues = (TechIssue[]) TransferData.getTrans(ComponentName.techIssues);
        TechIssue[] techIssues1 = new TechIssue[2];
        techIssues1[0] = techIssues[4];
        techIssues1[1] = techIssues[5];
        techIssueResponses[0] = new TechIssueResponse("Collaboration", "high", techIssues1);
        techIssueResponses[1] = new TechIssueResponse("Collaboration", "medium", techIssues1);
        techIssueResponses[2] = new TechIssueResponse("Collaboration", "low", techIssues1);
        TechIssue[] techIssues2 = new TechIssue[4];
        for (int i = 0; i < 4; i++) {
            techIssues2[i] = techIssues[i];
        }

        techIssueResponses[3] = new TechIssueResponse("Design&Quality", "high", techIssues2);
        techIssueResponses[4] = new TechIssueResponse("Design&Quality", "medium", techIssues2);
        techIssueResponses[5] = new TechIssueResponse("Design&Quality", "low", techIssues2);
        return techIssueResponses;
    }

    @RequestMapping("/functionalQuality/detail/backEnd")
    public FunctionalQualityResponse[] getBackEndFuntionQualityDetail() {

        BackEndCoverage[] backEndCoverages = (BackEndCoverage[]) TransferData.getTrans(ComponentName.backEndCoverages);
        String design_quality_coverage = calculateCoverage(backEndCoverages[0], backEndCoverages[2]) + "%";
        String collabration_coverage = calculateCoverage(backEndCoverages[4], backEndCoverages[5]) + "%";

        int[] backEndIssue = (int[]) TransferData.getTrans(ComponentName.backEndIsue);
        FunctionalQualityResponse[] functionalQualityResponses = new FunctionalQualityResponse[2];
        functionalQualityResponses[0] = new FunctionalQualityResponse("Collaboration", backEndIssue[0]+"", collabration_coverage+"%");
        functionalQualityResponses[1] = new FunctionalQualityResponse("Design&Quality", backEndIssue[1]+"", design_quality_coverage+"%");
        return functionalQualityResponses;

    }

    public float calculateCoverage(BackEndCoverage backEndCoverage1, BackEndCoverage backEndCoverage2) {
        float x = backEndCoverage1.msr[0].val * backEndCoverage1.msr[1].val;
        float y = backEndCoverage2.msr[0].val * backEndCoverage2.msr[1].val;
        float z = backEndCoverage1.msr[0].val + backEndCoverage2.msr[0].val;
        return (x + y) / z;
    }

    @RequestMapping("/jenkins/status/collaboration")
    public JenkinsStatus getCollabationJenkinsStatus() {
        JenkinsStatus jenkinsStatus = (JenkinsStatus) TransferData.getTrans(ComponentName.collaborationbenkinsStatus);
        return jenkinsStatus;
    }

    @RequestMapping("/jenkins/status/design_quality")
    public JenkinsStatus getDesign_QualityJenkinsStatus() {
        JenkinsStatus jenkinsStatus = (JenkinsStatus) TransferData.getTrans(ComponentName.design_quality_jenkinsStatus);
        return jenkinsStatus;
    }


    @RequestMapping("/functionalQuality/detail/apiTest")
    public APITestResponse[] getAPITestResponse(){
        int[][] apiTestNum = (int[][]) TransferData.getTrans(ComponentName.apiTestNum);
        APITestResponse[] apiTestResponses =new APITestResponse[2];
        apiTestResponses[0] = new APITestResponse("Collaboration",apiTestNum[0][0],apiTestNum[0][1]);
        apiTestResponses[1] = new APITestResponse("Design&Quality", apiTestNum[1][0], apiTestNum[1][1]);
        return apiTestResponses;
    }

    @RequestMapping("/functionalQuality/detail/frontEnd")
    public FunctionalQualityResponse[] getFrontEndFuntionQualityDetail() {

        float[][] frontTestNum = (float[][]) TransferData.getTrans(ComponentName.frontTestNum);
        FunctionalQualityResponse[] functionalQualityResponses = new FunctionalQualityResponse[2];
        functionalQualityResponses[0] = new FunctionalQualityResponse("Collaboration", frontTestNum[0][0]+"", frontTestNum[0][1]+"%");
        functionalQualityResponses[1] = new FunctionalQualityResponse("Design&Quality", frontTestNum[1][0]+"", frontTestNum[1][1]+"%");
        return functionalQualityResponses;
    }
}
