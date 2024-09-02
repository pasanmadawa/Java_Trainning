/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.connectiontoserver;

/**
 *
 * @author ASUS
 */
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

public class Server {
    public static void main(String[] args){
        
        Vertx vertx = Vertx.vertx();
        
        HttpServer httpServer = vertx.createHttpServer();
        
        Router router = Router.router(vertx);
        
        router
                .route()
                .handler(routingContext ->{
                    HttpServerResponse response = routingContext.response();
                    response.putHeader("content-type", "text/plain");
                    response.end("Hi");
                });
        
        httpServer
                .requestHandler(router)
                .listen(8090);
    }
    
}
