package work.service;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import work.model.JenkinsStatus;

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
    public WebTarget getTarget(String url) {
        ClientConfig clientConfig = new ClientConfig();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("i320997", "Liguanhua448");
        clientConfig.register(feature);
        Client client = ClientBuilder.newClient(clientConfig);
        WebTarget webTarget = client.target(url);

        return webTarget;
    }

    public String getContent(String url) throws ParseException {

        WebTarget target = getTarget(url);

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        if (response.getStatus() == 200) {
            String responseAsString = response.readEntity(String.class);

            return responseAsString;
        }

        return "nullllll";
    }

    public JenkinsStatus getJenkinsStatus(String url) throws ParseException {
        String responseAsString = getContent(url);
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
