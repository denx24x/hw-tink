package com.academy.fintech.scoring.containers;

import com.academy.fintech.scoring.Containers;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MockServerContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.utility.DockerImageName;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.concurrent.Future;

public class AppContainer extends GenericContainer<AppContainer> {

    public static final DockerImageName MOCKSERVER_IMAGE = DockerImageName
            .parse("mockserver/mockserver:5.15.0");
    private static final int HTTP_PORT = 8086;
    private static final int GRPC_PORT = 9096;
    public static MockServerContainer mockServer = new MockServerContainer(MOCKSERVER_IMAGE)
            .withNetwork(Containers.network)
            .withNetworkAliases("test-scoring")
            .withLogConsumer(new Slf4jLogConsumer(LoggerFactory.getLogger(MockServerContainer.class)));

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
        withEnv("scoring.client.product-engine.rest.url:", "http://host.docker.internal:" + mockServer.getServerPort());
        withExposedPorts(HTTP_PORT, GRPC_PORT);
        waitingFor(Wait.forHttp("/actuator/health/readiness").forPort(HTTP_PORT));
        withStartupTimeout(Duration.ofMinutes(1));
    }

    public int getHttpPort() {
        return this.getMappedPort(HTTP_PORT);
    }

    public int getGrpcPort() {
        return this.getMappedPort(GRPC_PORT);
    }

    @Override
    public void start() {
        mockServer.start();
        super.start();
    }
}
