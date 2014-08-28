package com.netflix.artemis;

/**
 * Created by dchoudhury on 8/25/14.
 */
public interface PluginService {

    public void start() throws Exception;

    public void stop() throws Exception;

    public String name();
}
