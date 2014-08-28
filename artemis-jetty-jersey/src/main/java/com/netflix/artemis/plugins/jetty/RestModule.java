package com.netflix.artemis.plugins.jetty;

import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

/**
 * Created by dchoudhury on 8/19/14.
 */
public class RestModule extends JerseyServletModule {

    @Override
    protected void configureServlets() {
        bind(GuiceContainer.class);
        serve("/*").with(GuiceContainer.class);
        PackagesResourceConfig packagesResourceConfig = new PackagesResourceConfig(getClass().getPackage().getName());
        for (Class<?> resource : packagesResourceConfig.getClasses()) {
            bind(resource);
        }
    }
}
