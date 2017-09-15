package uk.co.binarysoup;

import io.dropwizard.testing.ConfigOverride;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;
import uk.co.binarysoup.kratos.KratosAppConfiguration;
import uk.co.binarysoup.kratos.KratosApplication;

import java.io.IOException;
import java.net.ServerSocket;


public class KratosApplicationTest {
    @ClassRule
    public static final DropwizardAppRule<KratosAppConfiguration> RULE =
            new DropwizardAppRule<>(KratosApplication.class,
                    "config/app_config.yml",
                    ConfigOverride.config("server.applicationConnectors[0].port",
                            randomLocalServerPort()));


    @Test
    public void serverIsHealthy() {

    }

    private static String randomLocalServerPort() {
        try (ServerSocket server = new ServerSocket(0)) {
            return String.valueOf(server.getLocalPort());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}