package com.netflix.artemis.plugins.jetty;

import com.google.inject.Inject;
import com.netflix.artemis.PluginService;
import org.eclipse.jetty.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.BindException;

/**
 * Created by dchoudhury on 8/28/14.
 */
public class JettyPlugin implements PluginService {

    @Inject
    private Server server;

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Override
    public void start() throws Exception {
        try {
            logger.info("Starting Jetty Server");
            server.start();
        } catch (BindException bx) {
            logger.error("Jetty startup failed");
            server.stop();
        }
    }

    @Override
    public void stop() throws Exception {
        server.stop();
    }

    @Override
    public String name() {
        return "Jetty-Jersey";
    }
}
