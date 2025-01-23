package org.web.locations;


import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;

public class GetIntersections {

    private static final String apiKey = "AIzaSyDcAFz8e4dIQRl1uNZ1fxALxNtqg3rNRyQ";
    private static final String directionsUrl = "https://maps.googleapis.com/maps/api/directions/json";
    private static final String roadsUrl = "https://roads.googleapis.com/v1/nearestRoads";

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        WebClientOptions options = new WebClientOptions().setSsl(true).setTrustAll(true);
        WebClient client = WebClient.create(vertx, options);

        String origin = "Galle";
        String destination = "Kandy";

        String url = directionsUrl + "?origin=" + origin + "&destination=" + destination + "&key=" + apiKey;

        client.getAbs(url).send(ar -> {
            if (ar.succeeded()) {
                JsonObject response = ar.result().bodyAsJsonObject();
                if ("OK".equals(response.getString("status"))) {
                    JsonArray routes = response.getJsonArray("routes");

                    JsonArray junctionsArray = new JsonArray(); // Array to store junctions

                    routes.forEach(route -> {
                        JsonObject routeObject = (JsonObject) route;
                        JsonArray legs = routeObject.getJsonArray("legs");


                        legs.forEach(leg -> {
                            JsonObject legObject = (JsonObject) leg;
                            JsonArray steps = legObject.getJsonArray("steps");

                            steps.forEach(step -> {
                                JsonObject stepObject = (JsonObject) step;
                                JsonObject startLocation = stepObject.getJsonObject("start_location");

                                double lat = startLocation.getDouble("lat");
                                double lng = startLocation.getDouble("lng");


                                getNearbyRoads(client, lat, lng, junctionsArray);
                            });
                        });
                    });
                } else {
                    System.out.println("Route request failed with status: " + response.getString("status"));
                }
            } else {
                System.out.println("Failed to get response: " + ar.cause().getMessage());
            }
        });
    }


    private static void getNearbyRoads(WebClient client, double lat, double lng, JsonArray junctionsArray) {
        String url = roadsUrl + "?points=" + lat + "," + lng + "&key=" + apiKey;

        client.getAbs(url).send(roadResponse -> {
            if (roadResponse.succeeded()) {
                JsonObject roadResponseBody = roadResponse.result().bodyAsJsonObject();
                JsonArray snappedPoints = roadResponseBody.getJsonArray("snappedPoints");

                if (snappedPoints != null) {
                    snappedPoints.forEach(point -> {
                        JsonObject pointObject = (JsonObject) point;
                        JsonObject location = pointObject.getJsonObject("location");


                        junctionsArray.add(location);
                    });

                    System.out.println("Junctions collected so far: " + junctionsArray.encodePrettily());
                }
            } else {
                System.out.println("Failed to retrieve nearby roads: " + roadResponse.cause().getMessage());
            }
        });
    }
}

