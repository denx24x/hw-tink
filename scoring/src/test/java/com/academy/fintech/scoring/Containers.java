package com.academy.fintech.scoring;

import com.academy.fintech.scoring.containers.AppContainer;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.output.Slf4jLogConsumer;

public class Containers {
    public static final Network network = Network.newNetwork();
    public static final AppContainer appContainer = new AppContainer()
            .withNetwork(network)
            .withNetworkAliases("test-scoring")
            .withLogConsumer(new Slf4jLogConsumer(LoggerFactory.getLogger(AppContainer.class)));

    public static void start() {
        appContainer.start();
    }
}
