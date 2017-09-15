package uk.co.binarysoup.kratos;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.slf4j.LoggerFactory;
import uk.co.binarysoup.kratos.healthcheck.KratosHealthCheck;

public class KratosApplication extends Application<KratosAppConfiguration> {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(KratosApplication.class);

    public static void main(final String[] args) throws Exception {
        new KratosApplication().run(args);
    }


    @Override
    public void run(final KratosAppConfiguration configuration, final Environment environment) throws Exception {
        environment.healthChecks().register("kratos", new KratosHealthCheck());
    }

}

