package com.netflix.artemis.plugins.jetty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by dchoudhury on 8/21/14.
 */
@Path("/ping")
public class PingResource {

    @GET
    public Response ping() {
        return Response.ok("pong").build();
    }
}
