package work.task;

import com.sun.org.apache.bcel.internal.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import work.model.CodeDebt;
import work.service.CodeDebtService;
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

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");

    @Scheduled(fixedRate = 10000)
    public void reportCurrentTime(){
        System.out.println("the time is now " + dateFormat.format(new Date()));
    }

    @Scheduled(cron = "0 0 4 * * ?")
    public void getDebt(){
        CodeDebt[] codeDebts = codeDebtService.getCodeDebt();
        TransferData.setTrans(ComponentName.codeDebt,codeDebts);
        System.out.println(codeDebts[0].toString());
    }
}
