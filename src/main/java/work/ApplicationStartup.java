package work;

import org.json.simple.parser.ParseException;
import org.omg.CORBA.TRANSACTION_MODE;
import work.model.BackEndCoverage;
import work.model.CodeDebt;
import work.model.JenkinsStatus;
import work.model.TechIssue;
import work.service.*;
import work.utils.ComponentName;
import work.utils.TransferData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


/**
 * Created by I320997 on 1/9/2017.
 */
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent>{

    @Autowired
    CodeDebtService codeDebtService;

    @Autowired
    TechIssueService techIssueService;

    @Autowired
    BackEndUtService backEndUtService;

    @Autowired
    FunctionalQualityIssueSerivice functionalQualityIssueSerivice;

    @Autowired
    JenkinsStatusService jenkinsStatusService;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {


        System.out.println("-------------------------------------------");

        CodeDebt[] codeDebts = codeDebtService.getCodeDebt();
        TransferData.setTrans(ComponentName.codeDebts, codeDebts);


        TechIssue[] techIssues = techIssueService.getAllTechIssues();
        TransferData.setTrans(ComponentName.techIssues, techIssues);


        BackEndCoverage[] backEndCoverages = backEndUtService.getBackEndCoverage();
        TransferData.setTrans(ComponentName.backEndCoverages, backEndCoverages);

        int[] backEndIsue = new int[2];
        backEndIsue[0] = functionalQualityIssueSerivice.getFailuretestNum("http://10.58.67.159:8080/job/Team1-Dev-Pipeline/lastCompletedBuild/execution/node/26/wfapi/log");
        backEndIsue[1] = functionalQualityIssueSerivice.getFailuretestNum("http://10.58.67.159:8080/job/Team2-Dev-Pipeline/lastCompletedBuild/execution/node/26/wfapi/log");
        TransferData.setTrans(ComponentName.backEndIsue,backEndIsue);
        //jenkins 访问非常慢， 要等接近半分钟这两个 http请求，才能返回，等出现下面的console输出再测试
        System.out.println(backEndIsue[0] + "ffffffffffffffffff");


        try {
            JenkinsStatus collaborationbenkinsStatus = jenkinsStatusService.getJenkinsStatus("http://10.58.67.159:8080/job/Team1-Dev-Pipeline/api/json");
            TransferData.setTrans(ComponentName.collaborationbenkinsStatus,collaborationbenkinsStatus);
            JenkinsStatus design_quality_jenkinsStatus = jenkinsStatusService.getJenkinsStatus("http://10.58.67.159:8080/job/Team2-Dev-Pipeline/api/json");
            TransferData.setTrans(ComponentName.design_quality_jenkinsStatus,design_quality_jenkinsStatus);

        } catch (ParseException e) {
            e.printStackTrace();
        }
//--------------------------------2017.1.18     1
        int[][] apiTestNum = new int[2][2];
        apiTestNum[0] = new int[]{-1, -1};
        apiTestNum[1] = new int[]{-2, -2};
//        apiTestNum[0] = functionalQualityIssueSerivice.getFailureAndAlltestNum("");
//        apiTestNum[1] = functionalQualityIssueSerivice.getFailureAndAlltestNum("");
        TransferData.setTrans(ComponentName.apiTestNum,apiTestNum);

//--------------------------------2017.1.18     2
        int[][] frontTestNum = new int[2][2];
        frontTestNum[0] = new int[]{-1, 80};
        frontTestNum[1] = new int[]{-2, 82};
//        apiTestNum[0] = functionalQualityIssueSerivice.getFailureAndAlltestNum("");
//        apiTestNum[1] = functionalQualityIssueSerivice.getFailureAndAlltestNum("");
        TransferData.setTrans(ComponentName.frontTestNum,frontTestNum);
        System.out.println("start up finished");
    }
}
