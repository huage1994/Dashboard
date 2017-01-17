package work.task;

import com.sun.org.apache.bcel.internal.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import work.model.BackEndCoverage;
import work.model.CodeDebt;
import work.model.TechIssue;
import work.service.BackEndUtService;
import work.service.CodeDebtService;
import work.service.TechIssueService;
import work.utils.ComponentName;
import work.utils.TransferData;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by I320997 on 1/12/2017.
 */

@Component
public class ScheduledTasks {


    @Autowired
    CodeDebtService codeDebtService;

    @Autowired
    TechIssueService techIssueService;

    @Autowired
    BackEndUtService backEndUtService;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");

    @Scheduled(fixedRate = 10000)
    public void reportCurrentTime(){
        System.out.println("the time is now " + dateFormat.format(new Date()));
    }

    @Scheduled(cron = "0 0 4 * * ?")
    public void planDebt(){
        CodeDebt[] codeDebts = codeDebtService.getCodeDebt();
        TransferData.setTrans(ComponentName.codeDebts,codeDebts);
        System.out.println(codeDebts[0].toString());
    }

    @Scheduled(cron = "0 0 4 * * ?")
    public void planTechIssue(){
        TechIssue[] techIssues = techIssueService.getAllTechIssues();
        TransferData.setTrans(ComponentName.techIssues,techIssues);

    }
    @Scheduled(cron = "0 0 4 * * ?")
    public void planTBackEndUtService(){
        BackEndCoverage[] backEndCoverages = backEndUtService.getBackEndCoverage();
        TransferData.setTrans(ComponentName.backEndCoverages,backEndCoverages);

    }

}
