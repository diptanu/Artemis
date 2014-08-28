package com.netflix.artemis.plugins.jetty;

import com.google.common.util.concurrent.AbstractIdleService;
import org.eclipse.jetty.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.net.BindException;

/**
 * Created by dchoudhury on 5/31/14.
 */
public class HttpService extends AbstractIdleService {

    private Server server;

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Inject
    public HttpService(Server server) {
        this.server = server;
    }

    @Override
    protected void startUp() throws Exception {
        try {
            logger.info("Starting Server");
            server.start();
        } catch (BindException bx) {
            logger.error("Port in use, can't start server");
            server.stop();
        }
    }

    @Override
    protected void shutDown() throws Exception {
        logger.info("Shutting down server");
        server.stop();
    }
}
