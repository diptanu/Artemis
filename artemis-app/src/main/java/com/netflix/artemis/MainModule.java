package com.netflix.artemis;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

/**
 * Created by dchoudhury on 9/2/14.
 */
public class MainModule extends AbstractModule {


    @Override
    protected void configure() {
        Multibinder.newSetBinder(binder(), PluginService.class);
    }
}
