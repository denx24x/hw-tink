package com.academy.fintech.scoring.containers;

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

    private static final int HTTP_PORT = 8086;

    public AppContainer() {
        super(image());
    }

    private static Future<String> image() {
        Path dockerFile = Paths.get(System.getProperty("user.dir"), "Dockerfile");
        return new ImageFromDockerfile("test-scoring", true).withDockerfile(dockerFile);
    }

    @Override
    protected void configure() {
        super.configure();
        withExposedPorts(HTTP_PORT);
        waitingFor(Wait.forHttp("/actuator/health/readiness").forPort(HTTP_PORT));
        withStartupTimeout(Duration.ofMinutes(1));
    }

    public int getHttpPort() {
        return this.getMappedPort(HTTP_PORT);
    }

    @Override
    public void start() {
        super.start();
    }
}
