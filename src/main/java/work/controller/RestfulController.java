package work.controller;

import work.model.CodeDebt;
import work.outputModel.CodeDebtResponse;
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
    public String api2(@PathVariable String helloID){

        String result = "work  " + helloID;

        return result;
    }

    @RequestMapping("/codeDebt")
    public CodeDebt[] getCodeDebt(){
        CodeDebt[] codeDebt = (CodeDebt[]) TransferData.getTrans(ComponentName.codeDebt);
        return codeDebt;
    }

    @RequestMapping("/codeQuality/detail/codeDebt")
    public CodeDebtResponse[] getCodeDebtNew(){
        CodeDebtResponse[] codeDebtResponses = new CodeDebtResponse[3];
        CodeDebt[] codeDebt = (CodeDebt[]) TransferData.getTrans(ComponentName.codeDebt);
        float sum = 0;
        for (int i=0; i<codeDebt.length;i++)
        {
            sum += codeDebt[i].msr[0].val;
        }
        System.out.println(sum);

        codeDebtResponses[0] = new CodeDebtResponse("Design",codeDebt[0],codeDebt[1],sum);
        codeDebtResponses[1] = new CodeDebtResponse("Quality",codeDebt[2],codeDebt[3],sum);
        codeDebtResponses[2] = new CodeDebtResponse("Collaboration",codeDebt[4],codeDebt[5],sum);

        return codeDebtResponses;
    }
}
