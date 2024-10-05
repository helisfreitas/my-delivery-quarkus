package dev.helis.registration;

import java.util.HashMap;
import java.util.Map;

import org.testcontainers.containers.PostgreSQLContainer;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class RegistrationTestLifecycleManager implements QuarkusTestResourceLifecycleManager {

    public static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:12.0");

    @Override
    public Map<String, String> start() {
        POSTGRES.start();

        Map<String, String> proprieties = new HashMap<String, String>();

        proprieties.put("quarkus.datasource.jdbc.url", POSTGRES.getJdbcUrl());
        proprieties.put("quarkus.datasource.username", POSTGRES.getUsername());
        proprieties.put("quarkus.datasource.password", POSTGRES.getPassword());

        return proprieties;
    }

    @Override
    public void stop() {
        if (POSTGRES != null && POSTGRES.isRunning()) {
            POSTGRES.stop();
        }
    }

}