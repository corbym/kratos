package uk.co.binarysoup.kratos.acceptance;

import io.dropwizard.client.JerseyClientBuilder;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static uk.co.binarysoup.kratos.acceptance.AcceptanceTestSuite.RULE;

public class KratosApplicationAcceptanceTest {

    private static final Client CLIENT = new JerseyClientBuilder(RULE.getEnvironment()).build("test client");

    @Test
    public void serverIsHealthy() {

        final Invocation.Builder invocationBuilder = CLIENT.target(
                String.format("http://localhost:%d/healthcheck", RULE.getAdminPort()))
                .request();

        final Response response = invocationBuilder.get();
        final String body = response.readEntity(String.class);

        assertThat(response.getStatus(), equalTo(200));
        assertThat(body, containsString("\"kratos\":{\"healthy\":true}"));
    }


}