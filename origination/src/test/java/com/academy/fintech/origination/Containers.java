package com.academy.fintech.origination;

import com.academy.fintech.origination.containers.AppContainer;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.output.Slf4jLogConsumer;

public class Containers {
    private static final Network network = Network.newNetwork();
    public static final AppContainer appContainer = new AppContainer()
            .withNetwork(network)
            .withNetworkAliases("test-origination")
            .withLogConsumer(new Slf4jLogConsumer(LoggerFactory.getLogger(AppContainer.class)));

    public static void start() {
        appContainer.start();
    }

}
