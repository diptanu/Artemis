package com.netflix.artemis.plugins.karyon;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.netflix.karyon.transport.KaryonTransport;
import com.netflix.karyon.transport.http.SimpleUriRouter;
import io.reactivex.netty.protocol.http.server.HttpServer;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import io.reactivex.netty.protocol.http.server.RequestHandler;
import rx.Observable;

/**
 * Created by dchoudhury on 8/29/14.
 */
public class KaryonPluginModule extends AbstractModule {

    @Override
    protected void configure() {

    }

    @Provides
    public HttpServer providesKaryonTransport() {
        SimpleUriRouter simpleUriRouter = new SimpleUriRouter();
        simpleUriRouter.addUri("/foo", new RequestHandler() {
            @Override
            public Observable<Void> handle(HttpServerRequest request, HttpServerResponse response) {
                response.writeAndFlush("Hello World`");
                return response.close();
            }

            @Override
            public Observable<Void> handle(Object input, Object output) {
                return Observable.empty();
            }
        });
        return KaryonTransport.newHttpServer(8888, simpleUriRouter);
    }
}
