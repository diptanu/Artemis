package com.netflix.artemis;

import com.google.inject.Injector;
import com.netflix.governator.commons_cli.Cli;
import com.netflix.governator.guice.LifecycleInjector;
import com.netflix.governator.guice.serviceloader.ServiceLoaderModule;
import com.netflix.governator.lifecycle.LifecycleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dchoudhury on 8/18/14.
 */
public class Main {

    public static void main(String args[]) {
        Logger logger = LoggerFactory.getLogger(Main.class);
        Cli.start(Bootstrap.class, args);
        Injector injector = LifecycleInjector.builder()
                .withModules(
                        new ServiceLoaderModule() {
                            @Override
                            protected void configureServices() {
                                bindServices(PluginService.class).usingMultibinding(true);
                            }
                        })
                .build()
                .createInjector();
        LifecycleManager lifecycleManager = injector.getInstance(LifecycleManager.class);
        try {
            lifecycleManager.start();
        } catch (Exception e) {
            logger.error("Lifecycle Manager could not be started and hence not starting up");
            System.exit(1);
        }


        PluginManager pluginManager = injector.getInstance(PluginManager.class);
        pluginManager.startPlugins();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                logger.info("Stopping services");
                lifecycleManager.close();
                pluginManager.stopPlugins();
            }
        });
    }

}
