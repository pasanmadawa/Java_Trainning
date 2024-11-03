package org.web.locations;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.ext.web.codec.BodyCodec;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FindLocation extends AbstractVerticle {

    private static final String GOOGLE_API_KEY = "AIzaSyDcAFz8e4dIQRl1uNZ1fxALxNtqg3rNRyQ";
    private static final String PLACE_ID = "ChIJD7fiBh9u5kcRYJSMaMOCCwQ";
    private static final String GOOGLE_PLACE_DETAILS_URL = "maps.googleapis.com";

    @Override
    public void start() {
        WebClientOptions options = new WebClientOptions()
                .setSsl(true)
                .setDefaultHost(GOOGLE_PLACE_DETAILS_URL);

        WebClient client = WebClient.create(vertx, options);


        client.get("/maps/api/place/details/json")
                .addQueryParam("place_id", PLACE_ID)
                .addQueryParam("key", GOOGLE_API_KEY)
                .as(BodyCodec.string())
                .send(ar -> {
                    if (ar.succeeded()) {

                        String responseBody = ar.result().body();
                        parseAndPrintCoordinates(responseBody);
                    } else {
                        System.out.println("Request failed: " + ar.cause().getMessage());
                    }
                });
    }

    private void parseAndPrintCoordinates(String responseBody) {
        try {

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(responseBody);
            JsonNode locationNode = rootNode.path("result").path("geometry").path("location");

            if (!locationNode.isMissingNode()) {
                double latitude = locationNode.path("lat").asDouble();
                double longitude = locationNode.path("lng").asDouble();

                System.out.println("Latitude: " + latitude);
                System.out.println("Longitude: " + longitude);
            } else {
                System.out.println("Location not found in the response.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new FindLocation());
    }
}
