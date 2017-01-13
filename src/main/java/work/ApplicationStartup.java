package work;

import work.model.CodeDebt;
import work.model.TechIssue;
import work.service.CodeDebtService;
import work.service.TechIssueService;
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

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {



        System.out.println("-------------------------------------------");

        CodeDebt[] codeDebts = codeDebtService.getCodeDebt();

        TransferData.setTrans(ComponentName.codeDebts,codeDebts);
        CodeDebt[] fortest = (CodeDebt[]) TransferData.getTrans(ComponentName.codeDebts);
//        for (int i=0;i<fortest.length;i++) {
//            System.out.println(fortest[i].toString());
//        }

        TechIssue[] techIssues = techIssueService.getAllTechIssues();

        TransferData.setTrans(ComponentName.techIssues,techIssues);
        for (int i =0;i<6;i++) {
            System.out.println(techIssues[i]);
        }

    }
}
