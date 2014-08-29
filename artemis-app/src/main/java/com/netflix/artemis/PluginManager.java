package com.netflix.artemis;

import com.google.inject.Inject;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dchoudhury on 8/28/14.
 */
public class PluginManager {

    Set<PluginService> services = new HashSet<PluginService>();

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
