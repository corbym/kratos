package uk.co.binarysoup;

import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.ConfigOverride;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;
import uk.co.binarysoup.kratos.KratosAppConfiguration;
import uk.co.binarysoup.kratos.KratosApplication;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.ServerSocket;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class KratosApplicationTest {
    @ClassRule
    public static final DropwizardAppRule<KratosAppConfiguration> RULE =
            new DropwizardAppRule<>(KratosApplication.class,
                    "config/app_config.yml",
                    ConfigOverride.config("server.applicationConnectors[0].port",
                            randomLocalServerPort()));


    @Test
    public void serverIsHealthy() {
        Client client = new JerseyClientBuilder(RULE.getEnvironment()).build("test client");

        final Invocation.Builder invocationBuilder = client.target(
                String.format("http://localhost:%d/healthcheck", RULE.getAdminPort()))
                .request();

        final Response request = invocationBuilder.get();
        final String body = request.readEntity(String.class);

        assertThat(request.getStatus(), equalTo(200));
        assertThat(body, containsString("\"kratos\":{\"healthy\":true}"));
    }

    private static String randomLocalServerPort() {
        try (ServerSocket server = new ServerSocket(0)) {
            return String.valueOf(server.getLocalPort());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}