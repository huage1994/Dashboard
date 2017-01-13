package work.outputModel;

import work.model.CodeDebt;

/**
 * Created by I320997 on 1/11/2017.
 */
public class CodeDebtResponse {
    public String moduleName;
    public String maintainability;
    public String debtRatio;

    public CodeDebtResponse(String module, CodeDebt codeDebt1,CodeDebt codeDebt2,float sum) {
        float tempDebtRatio;
        this.moduleName = module;
        maintainability = codeDebt1.msr[1].val > codeDebt2.msr[1].val ? codeDebt1.msr[1].frmt_val:codeDebt2.msr[1].frmt_val;
        tempDebtRatio = codeDebt1.msr[0].val + codeDebt2.msr[0].val;
        tempDebtRatio = tempDebtRatio/sum * 100;
        debtRatio = String.format("%.2f", tempDebtRatio)+"%";
    }
}
