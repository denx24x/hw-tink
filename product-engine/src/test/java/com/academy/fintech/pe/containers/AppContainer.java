package com.academy.fintech.pe.containers;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.concurrent.Future;

public class AppContainer extends GenericContainer<AppContainer> {

    private static final int HTTP_PORT = 8083;

    @Container
    static PostgreSQLContainer postgres = new PostgreSQLContainer<>(
            "postgres:15-alpine"
    ).withDatabaseName("fintech")
            .withUsername("postgres")
            .withPassword("postgres")
            .withExposedPorts(5432);

    public AppContainer() {
        super(image());
    }

    @Override
    protected void configure(){
        super.configure();
        withEnv("spring.datasource.url", "jdbc:postgresql://host.docker.internal:" + postgres.getMappedPort(5432) + "/fintech");
        withEnv("spring.datasource.username", postgres.getUsername());
        withEnv("spring.datasource.password", postgres.getPassword());
        withExposedPorts(HTTP_PORT);
        waitingFor(Wait.forHttp("/actuator/health/readiness").forPort(HTTP_PORT));
        withStartupTimeout(Duration.ofMinutes(1));
    }

    private static Future<String> image(){
        Path dockerFile = Paths.get(System.getProperty("user.dir"), "Dockerfile");
        return new ImageFromDockerfile("test-app", true).withDockerfile(dockerFile);
    }

    public int getHttpPort(){
        return this.getMappedPort(HTTP_PORT);
    }

    @Override
    public void start(){
        postgres.start();
        super.start();
    }
}
