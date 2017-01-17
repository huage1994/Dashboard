package work.service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import work.model.BackEndCoverage;

/**
 * Created by I320997 on 1/16/2017.
 */

@Component
public class BackEndUtService {

    public BackEndCoverage[] getBackEndCoverage(){
        RestTemplate restTemplate = new RestTemplate();
        BackEndCoverage[]  backEndCoverages = restTemplate.getForObject("http://10.97.153.73:9000/api/resources?metrics=ncloc,coverage&format=json", BackEndCoverage[].class);
        return backEndCoverages;
    }

}
