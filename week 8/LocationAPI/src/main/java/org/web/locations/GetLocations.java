package org.web.locations;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.sqlclient.Pool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;

public class GetLocations {
    public static void main(String[] args) {

        Vertx vertx = Vertx.vertx();

        MySQLConnectOptions connectOptions = new MySQLConnectOptions()
                .setPort(3306)
                .setHost("localhost")
                .setDatabase("students_data")
                .setUser("root")
                .setPassword("madawa@2000");

        PoolOptions poolOptions = new PoolOptions().setMaxSize(5);

        Pool client = Pool.pool(vertx, connectOptions, poolOptions);

        vertx.createHttpServer()
                .requestHandler(setupRouter(vertx, client))
                .listen(8080, http -> {
                    if (http.succeeded()) {
                        System.out.println("HTTP server started on port 8080");
                    } else {
                        System.out.println("Failed to start HTTP server: " + http.cause().getMessage());
                    }
                });
    }

    private static Router setupRouter(Vertx vertx, Pool client) {
        Router router = Router.router(vertx);
        router.get("/api/get/location/:lat/:lon/:distance").handler(context -> handleGetRequest(context, client));
        return router;
    }

    private static void handleGetRequest(RoutingContext context, Pool client) {

        Double getLat = Double.parseDouble(context.pathParam("lat"));
        Double getLon = Double.parseDouble(context.pathParam("lon"));
        Double getDistance = Double.parseDouble(context.pathParam("distance"));

        client.getConnection(ar -> {
            if (ar.succeeded()) {
                var connection = ar.result();

                String query = "SELECT name, latitude, longitude FROM location_details";
                connection.query(query).execute(queryResult -> {
                    if (queryResult.succeeded()) {
//                        RowSet<Row> rows = queryResult.result();
//                        JsonObject jsonResponse = new JsonObject();

                        var rows = queryResult.result();
                        var locations = new JsonArray();

                        for (Row row : rows) {

//                            var location = new JsonObject()
//                                    .put("name", row.getString("name"))
//                                    .put("latitude", row.getDouble("latitude"))
//                                    .put("longitude", row.getDouble("longitude"));

                            String name = row.getString("name");
                            Double latitude = row.getDouble("latitude");
                            Double longitude = row.getDouble("longitude");

                            //locations.add(location);

                            if(withinDistance(getLat, getLon, latitude,longitude, getDistance )){

                                var location = new JsonObject()
                                        .put("name", name)
                                        .put("latitude", latitude)
                                        .put("longitude", longitude);

                                locations.add(location);
                            }




                        }

                        context.response()
                                .putHeader("content-type", "application/json")
                                .setStatusCode(200)
                                .end( new JsonObject()
                                        .put("status", "success")
                                        .put("locations", locations)
                                        .encodePrettily());
                    } else {
                        context.response()
                                .setStatusCode(500)
                                .end(new JsonObject()
                                        .put("status", "error")
                                        .put("message", "Query execution failed")
                                        .encodePrettily());
                    }
                    connection.close();
                });
            } else {
                context.response()
                        .setStatusCode(500)
                        .end(new JsonObject()
                                .put("status", "error")
                                .put("message", "Database connection failed")
                                .encodePrettily());
            }
        });
    }

    private static boolean withinDistance(double lat1, double lon1, double lat2, double lon2, double maxDistance) {
        double earthRadius = 6371;

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = earthRadius * c;

        System.out.println("distance: "+ distance);
        return distance <= maxDistance;
    }


}
