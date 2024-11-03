package org.web.locations;

import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public class Location {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        WebClient client = WebClient.create(vertx);
        HttpServer server = vertx.createHttpServer();

        String googleApiKey = "AIzaSyDcAFz8e4dIQRl1uNZ1fxALxNtqg3rNRyQ";

//        server.requestHandler(req -> {
//            req.response()
//                    .putHeader("content-type", "text/plain")
//                    .end("Hello, Vert.x!");
//        });
//
//
//                server.listen(8080, http ->{
//                    if(http.succeeded()){
//                        System.out.println("HTTP server run on port 8080");
//                    }
//                    else {
//                        System.out.println("Failed to start the server");
//                    }
//                });

        getDistance(client, googleApiKey,"Galle","Colombo");
    }

    public static void getDistance(WebClient client, String apiKey, String origin, String destination){

        String URL = "/maps/api/distancematrix/json?origins=" + origin + "&destinations"+ destination+"&key=" + apiKey;

        client.get(443, "maps.googleapis.com",URL)
                .ssl(true)
                .send(ar -> {
                    if (ar.succeeded()) {
                        HttpResponse<io.vertx.core.buffer.Buffer> response = ar.result();

                        //System.out.println("Response body: " + response.bodyAsString());


                        try {
                            JsonObject jsonResponse = response.bodyAsJsonObject();
                            if (jsonResponse.getString("status").equals("OK")) {
                                JsonArray rows = jsonResponse.getJsonArray("rows");
                                JsonObject element = rows.getJsonObject(0).getJsonArray("elements").getJsonObject(0);

                                if (element.getString("status").equals("OK")) {
                                    JsonObject distance = element.getJsonObject("distance");
                                    JsonObject duration = element.getJsonObject("duration");
                                    System.out.println("Distance: " + distance.getString("text") + ", Duration: " + duration.getString("text"));
                                } else {
                                    System.out.println("No results for the given locations.");
                                }
                            } else {
                                System.out.println("Failed to get a valid response. Status: " + jsonResponse.getString("status"));
                            }
                        } catch (Exception e) {
                            System.out.println("Error parsing JSON response: " + e.getMessage());
                            e.printStackTrace();
                        }
                    } else {
                        ar.cause().printStackTrace();
                    }
                });
    }


}
