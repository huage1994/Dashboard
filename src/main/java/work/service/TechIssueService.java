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
            "http://10.97.153.73:9000/api/issues/search?componentRoots=com.sap.mfg.dc.core&statuses=OPEN,REOPENED",
            "http://10.97.153.73:9000/api/issues/search?componentRoots=com.sap.mfg.dc.web&statuses=OPEN,REOPENED",
            "http://10.97.153.73:9000/api/issues/search?componentRoots=com.sap.mfg.qm.core&statuses=OPEN,REOPENED",
            "http://10.97.153.73:9000/api/issues/search?componentRoots=com.sap.mfg.qm.web&statuses=OPEN,REOPENED",
            "http://10.97.153.73:9000/api/issues/search?componentRoots=com.sap.mfg.collaboration&statuses=OPEN,REOPENED",
            "http://10.97.153.73:9000/api/issues/search?componentRoots=com.sap.mfg.common.service&statuses=OPEN,REOPENED",
        };
        TechIssue[] techIssues = new TechIssue[6];
        for (int i=0;i<6;i++)
        {
            techIssues[i]  = getTechIssue(urls[i]);
        }
        return  techIssues;
    }

}
