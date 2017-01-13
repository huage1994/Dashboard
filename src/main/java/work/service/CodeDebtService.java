package work.service;

import work.model.CodeDebt;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by I320997 on 1/11/2017.
 */
@Component
public class CodeDebtService {


    public CodeDebt[] getCodeDebt(){
        RestTemplate restTemplate =new RestTemplate();
        CodeDebt[] codeDebts= restTemplate.getForObject(
                "http://10.97.153.73:9000/api/resources?metrics=sqale_index,sqale_rating&format=json", CodeDebt[].class);

        return codeDebts;
    }
}
