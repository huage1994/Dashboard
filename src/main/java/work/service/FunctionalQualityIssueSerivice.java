package work.service;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Component
public class FunctionalQualityIssueSerivice {

    public WebTarget getTarget(String url) {
        ClientConfig clientConfig = new ClientConfig();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("i320997", "Liguanhua448");
        clientConfig.register(feature);
        Client client = ClientBuilder.newClient(clientConfig);
        WebTarget webTarget = client.target(url);

        return webTarget;
    }

    public synchronized String getContent(String url) {

        WebTarget target = getTarget(url);

        Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        if (response.getStatus() == 200) {
            String responseAsString = response.readEntity(String.class);
            return responseAsString;
        }

        return "nullllll";
    }


    public int getFailuretestNum(String url) {
        String handStr = getContent(url);

        System.out.println(handStr);

        int first = handStr.indexOf("Results");
        if (first != -1) {
            int last = handStr.indexOf("Errors", first);

            handStr = handStr.substring(first, last);


            int runResult = getRunResult(handStr);
            int failureResult = getFailureResult(handStr);
            System.out.println(runResult);
            System.out.println(failureResult);
            return failureResult;
        }
        return -1;
    }





    public int getRunResult(String handStr){
        int first = handStr.indexOf("run");
        first = handStr.indexOf(":", first)+1;
        int last = handStr.indexOf(",", first);
        return Integer.parseInt(handStr.substring(first, last).trim());
    }

    public int getFailureResult(String handStr){
        int first = handStr.indexOf("Failures");
        first = handStr.indexOf(":", first)+1;
        int last = handStr.indexOf(",", first);
        return Integer.parseInt(handStr.substring(first, last).trim());
    }

}