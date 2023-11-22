package com.academy.fintech.pe;

import com.academy.fintech.pe.containers.AppContainer;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.output.Slf4jLogConsumer;

public class Containers {
    private static final Network network = Network.newNetwork();
    public static final AppContainer appContainer = new AppContainer()
            .withNetwork(network)
            .withNetworkAliases("test-app")
            .withLogConsumer(new Slf4jLogConsumer(LoggerFactory.getLogger(AppContainer.class)));

    public static void start() {
        appContainer.start();
    }

}
