package work.utils;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by I320997 on 1/18/2017.
 */

@Component
public class HttpRequestGlassfish {

    public WebTarget getTarget(String url) {
        ClientConfig clientConfig = new ClientConfig();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("i320997", "Liguanhua448");
        clientConfig.register(feature);
        Client client = ClientBuilder.newClient(clientConfig);
        WebTarget webTarget = client.target(url);

        return webTarget;
    }

    public String getContent(String url) {

        WebTarget target = getTarget(url);

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        if (response.getStatus() == 200) {
            String responseAsString = response.readEntity(String.class);
            return responseAsString;
        }

        return "nullllll";
    }
}
