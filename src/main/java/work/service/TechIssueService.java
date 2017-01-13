package work.service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import work.model.TechIssue;

/**
 * Created by I320997 on 1/13/2017.
 */

@Component
public class TechIssueService {

    public TechIssue getTechIssue(String url){
        RestTemplate restTemplate = new RestTemplate();
        TechIssue techIssue = restTemplate.getForObject(url, TechIssue.class);
        return techIssue;
    }

    public TechIssue[] getAllTechIssues(){
        String[] urls = {
            "http://10.97.153.73:9000/api/issues/search?componentRoots=com.sap.mfg.qm.core",
            ""
        };
        return  null;
    }
}
