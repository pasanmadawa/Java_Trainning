package org.vertex;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

public class Main {
    public static void main(String[] args) {

        Vertx vertx = Vertx.vertx();
        HttpServer httpServer = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router
                .route()
                        .handler(routingContext ->{
                            HttpServerResponse response = routingContext.response();
                            response.putHeader("content-type","application/json" );

                            JsonObject jsonObject = new JsonObject();
                            jsonObject.put("message","Hello world");

                            response.end(jsonObject.encodePrettily());

                        });

        httpServer
                .requestHandler(router)
                .listen(8090);
    }
}