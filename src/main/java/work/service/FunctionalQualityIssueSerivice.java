package work.service;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import work.utils.HttpRequestGlassfish;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Component
public class FunctionalQualityIssueSerivice {

    @Autowired
    private HttpRequestGlassfish httpRequestGlassfish;


    public int getFailuretestNum(String url){
        String handStr = httpRequestGlassfish.getContent(url);

        System.out.println(handStr);

        int first = handStr.indexOf("Results");
        if (first != -1) {
            int last = handStr.indexOf("Errors", first);

            handStr = handStr.substring(first, last);

            int failureResult = getFailureResult(handStr);

            System.out.println(failureResult);
            return failureResult;
        }
        return -1;
    }

    public int[] getFailureAndAlltestNum(String url) {
        String handStr = httpRequestGlassfish.getContent(url);

        System.out.println(handStr);

        int first = handStr.indexOf("Results");
        if (first != -1) {
            int last = handStr.indexOf("Errors", first);

            handStr = handStr.substring(first, last);

            int[] result = new int[2];
            result[0] = getFailureResult(handStr);
            result[1] = getRunResult(handStr);

            return result;
        }
        int[] result = {-1,-1};
        return result;
    }

    public int[] getFailureAndCoverage(String url) {
        String handStr = httpRequestGlassfish.getContent(url);
        System.out.println(handStr);

        int first = handStr.indexOf("Results");
        if (first != -1) {
            int last = handStr.indexOf("Errors", first);

            handStr = handStr.substring(first, last);

            int[] result = new int[2];
            result[0] = getFailureResult(handStr);
            result[1] = getCoverageResult(handStr);

            return result;
        }
        int[] result = {-1,-1};
        return result;
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


    private int getCoverageResult(String handStr) {
        int first = handStr.indexOf("Coverage");
        first= handStr.indexOf(":",first)+1;
        /* TO DO */
        int last = handStr.indexOf(",", first);
        return Integer.parseInt(handStr.substring(first, last).trim());


    }


}