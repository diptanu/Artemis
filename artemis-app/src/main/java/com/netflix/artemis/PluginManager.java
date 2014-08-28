package com.netflix.artemis;

import javax.inject.Inject;
import java.util.Set;

/**
 * Created by dchoudhury on 8/28/14.
 */
public class PluginManager {

    private Set<PluginService> services;

    @Inject
    public PluginManager(Set<PluginService> services) {
        this.services = services;
    }

    public void startPlugins() {
        for(PluginService pluginService: services) {
            try {
                pluginService.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stopPlugins() {
        for(PluginService pluginService: services) {
            try {
                pluginService.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
