package work.service;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import work.model.JenkinsStatus;
import work.utils.HttpRequestGlassfish;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by I320997 on 1/17/2017.
 */

@Component
public class JenkinsStatusService {
    @Autowired
    private HttpRequestGlassfish httpRequestGlassfish;

    public JenkinsStatus getJenkinsStatus(String url) throws ParseException {
        String responseAsString = httpRequestGlassfish.getContent(url);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(responseAsString);
        JSONObject jsonObject = (JSONObject) obj;
        String jenkinsurl = jsonObject.get("url").toString();
        String jenkinsColor = jsonObject.get("color").toString();
        System.out.println(jenkinsurl+">>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(jenkinsColor+">>>>>>>>>>>>>>>>>>>>>>");

        JenkinsStatus jenkinsStatus = new JenkinsStatus(jenkinsurl,jenkinsColor);
        return jenkinsStatus;
    }


}
