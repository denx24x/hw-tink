package com.academy.fintech.pe.containers;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.concurrent.Future;

public class AppContainer extends GenericContainer<AppContainer> {

    private static final int HTTP_PORT = 8083;

    public AppContainer() {
        super(image());
    }

    @Override
    protected void configure(){
        super.configure();
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
}
