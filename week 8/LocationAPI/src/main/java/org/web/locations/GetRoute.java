package org.web.locations;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;

public class GetRoute {

    private static final String apiKey = "AIzaSyDcAFz8e4dIQRl1uNZ1fxALxNtqg3rNRyQ";
    private static final String url = "https://maps.googleapis.com/maps/api/directions/json";

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        WebClientOptions options = new WebClientOptions().setSsl(true).setTrustAll(true);
        WebClient client = WebClient.create(vertx, options);


        String origin = "Galle";
        String destination = "Kandy";


        String url = GetRoute.url + "?origin=" + origin + "&destination=" + destination + "&key=" + apiKey;


        client.getAbs(url).send(ar -> {
            if (ar.succeeded()) {

                JsonObject response = ar.result().bodyAsJsonObject();
                System.out.println("Route Details:");
                System.out.println(response.encodePrettily());


                if (response.getString("status").equals("OK")) {
                    response.getJsonArray("routes").forEach(route -> {
                        JsonObject routeObject = (JsonObject) route;
                        System.out.println("Summary: " + routeObject.getString("summary"));
                        System.out.println("Route information: " + routeObject.encodePrettily());
                    });
                }
            } else {

                System.out.println("Failed to get response: " + ar.cause().getMessage());
            }
        });
    }
}

