package com.netflix.artemis;

import com.google.inject.AbstractModule;
import com.netflix.governator.guice.annotations.GovernatorConfiguration;

/**
 * Created by dchoudhury on 8/26/14.
 */
@GovernatorConfiguration
public class Bootstrap extends AbstractModule {
    @Override
    protected void configure() {
        bind(Main.class).asEagerSingleton();
    }
}
