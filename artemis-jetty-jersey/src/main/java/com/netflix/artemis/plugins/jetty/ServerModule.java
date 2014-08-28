package com.netflix.artemis.plugins.jetty;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.RequestLog;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.RequestLogHandler;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

/**
 * Created by dchoudhury on 5/31/14.
 */
public class ServerModule extends AbstractModule {



    @Override
    protected void configure() {
        bind(HttpService.class);
        bind(RequestLog.class).to(HttpServerRequestLog.class);
    }

    @Provides
    @Singleton
    @Inject
    protected Server provideHttpServer(HandlerCollection handlerCollection) {
        Server server = new Server(8080);
        server.setHandler(handlerCollection);
        return server;
    }

    @Provides
    @Singleton
    @Inject
    protected HandlerCollection providesHandlerCollection(ServletContextHandler servletContextHandler, RequestLogHandler requestLogHandler) {
        HandlerCollection handlerCollection = new HandlerCollection();
        handlerCollection.setHandlers(new Handler[]{servletContextHandler, requestLogHandler});
        return handlerCollection;
    }

    @Provides
    @Singleton
    @Inject
    protected RequestLogHandler providesRequestLogHandler(RequestLog requestLog) {
        RequestLogHandler requestLogHandler = new RequestLogHandler();
        requestLogHandler.setRequestLog(requestLog);
        return requestLogHandler;
    }

    @Provides
    @Singleton
    protected ServletContextHandler providesServletContextHandler() {
        ServletContextHandler servletContextHandler = new ServletContextHandler();
        servletContextHandler.addServlet(DefaultServlet.class, "/*");
        servletContextHandler.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));
        return servletContextHandler;
    }


    @Provides
    @Singleton
    protected ObjectMapper provideObjectMapper() {
        return new ObjectMapper();
    }

    @Provides
    @Singleton
    @Inject
    protected JacksonJsonProvider providesJacksonJsonProvider(ObjectMapper objectMapper) {
        return new JacksonJsonProvider(objectMapper);
    }


}
