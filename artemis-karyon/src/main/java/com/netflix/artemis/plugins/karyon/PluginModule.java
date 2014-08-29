package com.netflix.artemis.plugins.karyon;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.multibindings.Multibinder;
import com.netflix.artemis.PluginService;

/**
 * Created by dchoudhury on 8/29/14.
 */
public class PluginModule extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder<PluginService> pluginBinder = Multibinder.newSetBinder(binder(), PluginService.class);
        pluginBinder.addBinding().to(KaryonService.class).in(Scopes.SINGLETON);

    }
}
