package com.netflix.artemis.plugins.jetty;

import org.eclipse.jetty.server.NCSARequestLog;
import org.eclipse.jetty.util.StringUtil;

import java.util.logging.Logger;

/**
 * Created by dchoudhury on 5/31/14.
 */
public class HttpServerRequestLog extends NCSARequestLog {

    private int lineSeparationLength = StringUtil.__LINE_SEPARATOR.length();

    private final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void write(String request) {
        logger.info(request.substring(0, request.length() - lineSeparationLength));
    }
}
