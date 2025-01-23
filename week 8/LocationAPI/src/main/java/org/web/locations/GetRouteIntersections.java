package org.web.locations;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;

import java.util.ArrayList;
import java.util.List;

public class GetRouteIntersections {

    private static final String apiKey = "AIzaSyDcAFz8e4dIQRl1uNZ1fxALxNtqg3rNRyQ";
    private static final String url = "https://maps.googleapis.com/maps/api/directions/json";

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        WebClientOptions options = new WebClientOptions().setSsl(true).setTrustAll(true);
        WebClient client = WebClient.create(vertx, options);

        String origin = "Colombo";
        String destination = "Kandy";

        String url = GetRouteIntersections.url + "?origin=" + origin + "&destination=" + destination + "&key=" + apiKey;

        client.getAbs(url).send(ar -> {
            if (ar.succeeded()) {
                JsonObject response = ar.result().bodyAsJsonObject();
                System.out.println("Route Details:");
                System.out.println(response.encodePrettily());

                if ("OK".equals(response.getString("status"))) {
                    JsonArray routesArray = response.getJsonArray("routes");


                    List<JsonObject> endLocations = new ArrayList<>();


                    for (int i = 0; i < routesArray.size(); i++) {
                        JsonObject routeObject = routesArray.getJsonObject(i);
                        JsonArray legsArray = routeObject.getJsonArray("legs");


                        if (legsArray != null && !legsArray.isEmpty()) {
                            JsonObject firstLeg = legsArray.getJsonObject(0);
                            JsonObject endLocation = firstLeg.getJsonObject("end_location");


                            endLocations.add(endLocation);


//                            System.out.println("Route " + (i + 1) + " End Location:");
//                            System.out.println("Latitude: " + endLocation.getDouble("lat"));
//                            System.out.println("Longitude: " + endLocation.getDouble("lng"));
                        }
                    }

                    // Print all end locations
                    System.out.println("All End Locations: " + endLocations);
                }
            } else {
                System.out.println("Failed to get response: " + ar.cause().getMessage());
            }
        });
    }
}
