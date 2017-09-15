package uk.co.binarysoup.kratos.healthcheck;

import com.codahale.metrics.health.HealthCheck;

public class KratosHealthCheck extends HealthCheck {
    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
