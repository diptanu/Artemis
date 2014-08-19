package com.netflix.artemis;

import com.google.inject.Injector;
import com.netflix.artemis.server.HttpService;
import com.netflix.artemis.server.ServerModule;
import com.netflix.governator.guice.LifecycleInjector;
import com.netflix.governator.lifecycle.LifecycleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dchoudhury on 8/18/14.
 */
public class Main {

    public static void main(String args[]) {
        Logger logger = LoggerFactory.getLogger(Main.class);
        Injector injector = LifecycleInjector.builder()
                .withModules(new ServerModule())
                .build()
                .createInjector();
        LifecycleManager lifecycleManager = injector.getInstance(LifecycleManager.class);
        try {
            lifecycleManager.start();
        } catch (Exception e) {
            logger.error("Lifecycle Manager could not be started and hence not starting up");
            System.exit(1);
        }

        HttpService httpService = injector.getInstance(HttpService.class);
        httpService.startAsync();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run(){
                logger.info("Stopping services");
                lifecycleManager.close();
                httpService.stopAsync();
            }
        });
    }

}
