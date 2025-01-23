package org.vertex;


import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.sqlclient.*;

import java.util.UUID;

public class StudentDetails {
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


        router.post("/api/post/student").handler(context -> handlePostRequest(context, client));
        router.get("/api/get/student/:id").handler(context -> handleGetStudent(context, client));
        router.put("/api/put/student/:id").handler(context -> handlePutRequest(context, client));
        router.delete("/api/delete/student/:id").handler(context -> handleDeleteRequestById(context, client));
        //router.get("/api/student/list").handler(context -> handleGetListRequest(context, client));
        router.delete("/api/get/student/all").handler(context -> handleDeleteAllRequest(context, client));
        router.get("/api/get/student/location/:id1/:id2").handler(context -> handleGetDistance(context, client));
        router.get("/api/get/student/location/:id/:lat/:lon").handler(context -> handleGetTheDistanceToSpecialPlace(context,client));
        return router;
    }


    private static void handlePostRequest(RoutingContext context, Pool client) {

        context.request().bodyHandler(buffer -> {
            JsonObject jsonBody = buffer.toJsonObject();


            String student_name = jsonBody.getString("student_name");
            Integer student_grade = jsonBody.getInteger("student_grade");
            //String location = jsonBody.getString("location");
            Double lat = jsonBody.getDouble("student_location_latitude");
            Double lon = jsonBody.getDouble("student_location_longitude");


            String student_id = UUID.randomUUID().toString();


            String query = "INSERT INTO student_details (student_id, student_name, student_grade, student_location_latitude, student_location_longitude) VALUES (?, ?, ?, ?, ?)";
            client.getConnection(ar -> {
                if (ar.succeeded()) {
                    SqlConnection connection = ar.result();
                    connection.preparedQuery(query).execute(Tuple.of(student_id, student_name, student_grade,lat,lon), res -> {
                        if (res.succeeded()) {
                            System.out.println("Data inserted into MySQL: student_id = " + student_id + ", student_name = " + student_name + ", student_grade = " + student_grade + ", latitude = " + lat + ", longitude = "+ lon);
                            context.response()
                                    .putHeader("content-type", "application/json")
                                    .setStatusCode(200)
                                    .end(new JsonObject()
                                            .put("status", "success")
                                            .put("student_id", student_id)
                                            .put("student_name", student_name)
                                            .put("student_grade", student_grade)
                                            .put("student_location_latitude", lat)
                                            .put("student_location_longitude", lon)
                                            .encodePrettily());
                        } else {
                            System.out.println("Failed to insert data into MySQL: " + res.cause().getMessage());
                            context.response()
                                    .putHeader("content-type", "application/json")
                                    .setStatusCode(500)
                                    .end(new JsonObject()
                                            .put("status", "error")
                                            .put("message", res.cause().getMessage())
                                            .encodePrettily());
                        }
                        connection.close();
                    });
                } else {
                    System.out.println("Failed to connect to MySQL: " + ar.cause().getMessage());
                    context.response()
                            .putHeader("content-type", "application/json")
                            .setStatusCode(500)
                            .end(new JsonObject()
                                    .put("status", "error")
                                    .put("message", ar.cause().getMessage())
                                    .encodePrettily());
                }
            });
        });
    }

    private static void handleGetStudent(RoutingContext context, Pool client) {

        String student_id = context.pathParam("id");
        System.out.println(student_id);

        String query = "SELECT student_id, student_name, student_grade FROM student_details WHERE student_id = ?";

        client.getConnection(ar -> {
            if (ar.succeeded()) {

                SqlConnection connection = ar.result();

                connection.preparedQuery(query).execute(Tuple.of(student_id), res -> {

                    if (res.succeeded()) {

                        if (res.result().size() > 0) {

                            Row row = res.result().iterator().next();
                            String student_name = row.getString("student_name");
                            Integer student_grade = row.getInteger("student_grade");
                            for (int i = 0; i < row.size(); i++) {
                                System.out.println("Column: " + row.getColumnName(i) + ", Value: " + row.getValue(i));
                            }

//                            RowSet<Row> rows = ar.result();
//                            for (Row row : rows) {
//                                System.out.println("User " + row.getString(0) + " " + row.getString(1));
//                            }


                            context.response()
                                    .putHeader("content-type", "application/json")
                                    .setStatusCode(200)
                                    .end(new JsonObject()
                                            .put("status", "success")
                                            .put("student_id", student_id)
                                            .put("student_name", student_name)
                                            .put("student_grade", student_grade)
                                            .encodePrettily());
                        } else {

                            context.response()
                                    .putHeader("content-type", "application/json")
                                    .setStatusCode(404)
                                    .end(new JsonObject()
                                            .put("status", "error")
                                            .put("message", "Student not found")
                                            .encodePrettily());
                        }


                    }

                    else {

                        System.out.println("Failed to retrieve data from MySQL: " + res.cause().getMessage());
                        context.response()
                                .putHeader("content-type", "application/json")
                                .setStatusCode(500)
                                .end(new JsonObject()
                                        .put("status", "error")
                                        .put("message", res.cause().getMessage())
                                        .encodePrettily());
                    }
//                    for(Row row: ar){
//
//                    }
                    connection.close();
                });
            } else {

                System.out.println("Failed to connect to MySQL: " + ar.cause().getMessage());
                context.response()
                        .putHeader("content-type", "application/json")
                        .setStatusCode(500)
                        .end(new JsonObject()
                                .put("status", "error")
                                .put("message", ar.cause().getMessage())
                                .encodePrettily());
            }
//            ar.succeeded(rows -> {
//                for(Row row :rows ){
//                    System.out.println(row.getString("student_name"));
//                }
//                    }
//
//            );
        });
    }


    private static void handlePutRequest(RoutingContext context, Pool client) {
        String student_id = context.pathParam("id");

        context.request().bodyHandler(buffer -> {
            JsonObject jsonBody = buffer.toJsonObject();

            String student_name = jsonBody.getString("student_name");
            Integer student_grade = jsonBody.getInteger("student_grade");
            String location = jsonBody.getString("location");

            String query = "UPDATE student_details SET student_name = ?, student_grade = ?, location = ? WHERE student_id = ?";
            client.getConnection(ar -> {
                if (ar.succeeded()) {
                    SqlConnection connection = ar.result();
                    connection.preparedQuery(query).execute(Tuple.of(student_name, student_grade, location, student_id), res -> {
                        if (res.succeeded()) {
                            if (res.result().rowCount() > 0) {
                                System.out.println("Data updated in MySQL: student_id = " + student_id + ", student_name = " + student_name + ", student_grade = " + student_grade +", location = " + location);
                                context.response()
                                        .putHeader("content-type", "application/json")
                                        .setStatusCode(200)
                                        .end(new JsonObject()
                                                .put("status", "success")
                                                .put("student_id", student_id)
                                                .put("student_name", student_name)
                                                .put("student_grade", student_grade)
                                                .put("location", location)
                                                .encodePrettily());
                            } else {
                                context.response()
                                        .putHeader("content-type", "application/json")
                                        .setStatusCode(404)
                                        .end(new JsonObject()
                                                .put("status", "error")
                                                .put("message", "Student not found")
                                                .encodePrettily());
                            }
                        } else {
                            System.out.println("Failed to update data in MySQL: " + res.cause().getMessage());
                            context.response()
                                    .putHeader("content-type", "application/json")
                                    .setStatusCode(500)
                                    .end(new JsonObject()
                                            .put("status", "error")
                                            .put("message", res.cause().getMessage())
                                            .encodePrettily());
                        }
                        connection.close();
                    });
                } else {
                    System.out.println("Failed to connect to MySQL: " + ar.cause().getMessage());
                    context.response()
                            .putHeader("content-type", "application/json")
                            .setStatusCode(500)
                            .end(new JsonObject()
                                    .put("status", "error")
                                    .put("message", ar.cause().getMessage())
                                    .encodePrettily());
                }
            });
        });
    }

    private static void handleDeleteRequestById(RoutingContext context, Pool client) {
        String student_id = context.pathParam("id");

        String query = "DELETE FROM student_details WHERE student_id = ?";
        client.getConnection(ar -> {
            if (ar.succeeded()) {
                SqlConnection connection = ar.result();
                connection.preparedQuery(query).execute(Tuple.of(student_id), res -> {
                    if (res.succeeded()) {
                        if (res.result().rowCount() > 0) {
                            System.out.println("Data deleted from MySQL: student_id = " + student_id);
                            context.response()
                                    .putHeader("content-type", "application/json")
                                    .setStatusCode(200)
                                    .end(new JsonObject()
                                            .put("status", "success")
                                            .put("message", "Student deleted")
                                            .put("student_id", student_id)
                                            .encodePrettily());
                        } else {
                            context.response()
                                    .putHeader("content-type", "application/json")
                                    .setStatusCode(404)
                                    .end(new JsonObject()
                                            .put("status", "error")
                                            .put("message", "Student not found")
                                            .encodePrettily());
                        }
                    } else {
                        System.out.println("Failed to delete data from MySQL: " + res.cause().getMessage());
                        context.response()
                                .putHeader("content-type", "application/json")
                                .setStatusCode(500)
                                .end(new JsonObject()
                                        .put("status", "error")
                                        .put("message", res.cause().getMessage())
                                        .encodePrettily());
                    }
                    connection.close();
                });
            } else {
                System.out.println("Failed to connect to MySQL: " + ar.cause().getMessage());
                context.response()
                        .putHeader("content-type", "application/json")
                        .setStatusCode(500)
                        .end(new JsonObject()
                                .put("status", "error")
                                .put("message", ar.cause().getMessage())
                                .encodePrettily());
            }
        });
    }

    private static void handleGetListRequest(RoutingContext context, Pool client) {
        String query = "SELECT student_id, student_name, student_grade, location FROM student_details";

        client.getConnection(ar -> {
            if (ar.succeeded()) {
                SqlConnection connection = ar.result();
                connection.preparedQuery(query).execute(res -> {
                    if (res.succeeded()) {

                        var rows = res.result();
                        var students = new JsonArray();
                        for (Row row : rows) {
                            var student = new JsonObject()
                                    .put("student_id", row.getString("student_id"))
                                    .put("student_name", row.getString("student_name"))
                                    .put("student_grade", row.getInteger("student_grade"));


                            students.add(student);
                        }

                        context.response()
                                .putHeader("content-type", "application/json")
                                .setStatusCode(200)
                                .end(new JsonObject()
                                        .put("status", "success")
                                        .put("students", students)
                                        .encodePrettily());
                    } else {
                        System.out.println("Failed to retrieve data from MySQL: " + res.cause().getMessage());
                        context.response()
                                .putHeader("content-type", "application/json")
                                .setStatusCode(500)
                                .end(new JsonObject()
                                        .put("status", "error")
                                        .put("message", res.cause().getMessage())
                                        .encodePrettily());
                    }
                    connection.close();
                });
            } else {
                System.out.println("Failed to connect to MySQL: " + ar.cause().getMessage());
                context.response()
                        .putHeader("content-type", "application/json")
                        .setStatusCode(500)
                        .end(new JsonObject()
                                .put("status", "error")
                                .put("message", ar.cause().getMessage())
                                .encodePrettily());
            }
        });
    }

    private static void handleDeleteAllRequest(RoutingContext context, Pool client) {
        String query = "DELETE FROM student_details";
        client.getConnection(ar -> {
            if (ar.succeeded()) {
                SqlConnection connection = ar.result();
                connection.query(query).execute(res -> {
                    if (res.succeeded()) {
                        System.out.println("All data deleted from MySQL: student_details table");
                        context.response()
                                .putHeader("content-type", "application/json")
                                .setStatusCode(200)
                                .end(new JsonObject()
                                        .put("status", "success")
                                        .put("message", "All students deleted")
                                        .encodePrettily());
                    } else {
                        System.out.println("Failed to delete data from MySQL: " + res.cause().getMessage());
                        context.response()
                                .putHeader("content-type", "application/json")
                                .setStatusCode(500)
                                .end(new JsonObject()
                                        .put("status", "error")
                                        .put("message", res.cause().getMessage())
                                        .encodePrettily());
                    }
                    connection.close();
                });
            } else {
                System.out.println("Failed to connect to MySQL: " + ar.cause().getMessage());
                context.response()
                        .putHeader("content-type", "application/json")
                        .setStatusCode(500)
                        .end(new JsonObject()
                                .put("status", "error")
                                .put("message", ar.cause().getMessage())
                                .encodePrettily());
            }
        });
    }

    private static void handleGetDistance(RoutingContext context, Pool client){

        String studentId1 = context.pathParam("id1");
        String studentId2 = context.pathParam("id2");

        String query = "SELECT student_id, student_location_latitude, student_location_longitude FROM student_details WHERE student_id IN (?, ?)";
        //System.out.println("id1: "+ studentId1);
        client.getConnection(ar -> {
            if (ar.succeeded()) {
                SqlConnection connection = ar.result();

                connection.preparedQuery(query).execute(Tuple.of(studentId1, studentId2), res -> {
                    if (res.succeeded()) {
                        if (res.result().size() > 0) {

                            var rows = res.result();
                            var students = new JsonArray();
                            for(Row row: rows){
                                var student = new JsonObject()
                                        .put("student_id", row.getString("student_id"))
                                        .put("student_location_latitude", row.getDouble("student_location_latitude"))
                                        .put("student_location_longitude", row.getDouble("student_location_longitude"));

                                students.add(student);


                            }
                            Double student1_lat = null;
                            Double student1_lon = null;
                            Double student2_lat = null;
                            Double student2_lon = null;

                            for(int i = 0; i < students.size(); i++){

                                JsonObject student = students.getJsonObject(i);
                                String studentId = student.getString("student_id");

                                if(studentId.equals(studentId1)){
                                    student1_lat = student.getDouble("student_location_latitude");
                                    student1_lon = student.getDouble("student_location_longitude");
                                }
                                if(studentId.equals(studentId2)){
                                    student2_lat = student.getDouble("student_location_latitude");
                                    student2_lon = student.getDouble("student_location_longitude");
                                }

                            }

                            student1_lat = Math.toRadians(student1_lat);
                            student1_lon = Math.toRadians(student1_lon);
                            student2_lat = Math.toRadians(student2_lat);
                            student2_lon = Math.toRadians(student2_lon);

                            double lon = student1_lon  - student2_lon;
                            double lat = student1_lat - student2_lat;

                            double a = Math.pow(Math.sin(lat / 2), 2)
                                    + Math.cos(student1_lat) * Math.cos(student2_lat)
                                    * Math.pow(Math.sin(lon / 2),2);

                            double c = 2 * Math.asin(Math.sqrt(a));

                            double earthRadius = 6371;

                            double distance = c* earthRadius;

                            System.out.println("Distance is: "+ distance + " km");

                            context.response()
                                    .putHeader("content-type", "application/json")
                                    .setStatusCode(200)
                                    .end(new JsonObject()
                                            .put("status", "success")
                                            .put("students", students)
                                            .encodePrettily());

                        } else {

                            context.response()
                                    .putHeader("content-type", "application/json")
                                    .setStatusCode(404)
                                    .end(new JsonObject()
                                            .put("status", "error")
                                            .put("message", "Student not found")
                                            .encodePrettily());
                        }
                    } else {

                        System.out.println("Failed to retrieve data from MySQL: " + res.cause().getMessage());
                        context.response()
                                .putHeader("content-type", "application/json")
                                .setStatusCode(500)
                                .end(new JsonObject()
                                        .put("status", "error")
                                        .put("message", res.cause().getMessage())
                                        .encodePrettily());
                    }
                    connection.close();
                });
            } else {

                System.out.println("Failed to connect to MySQL: " + ar.cause().getMessage());
                context.response()
                        .putHeader("content-type", "application/json")
                        .setStatusCode(500)
                        .end(new JsonObject()
                                .put("status", "error")
                                .put("message", ar.cause().getMessage())
                                .encodePrettily());
            }
        });

//        client
//                .preparedQuery(query)
//                .execute(Tuple.of(studentId1))
//                .onComplete(ar -> {
//                    if(ar.succeeded()){
//                        RowSet<Row> rows = ar.result();
//                        for(Row row : rows){
//                            System.out.println(row.getString(3));
//                        }
//                    }
//                    else {
//                        System.out.println("Fail: " + ar.cause().getMessage());
//                    }
//                });
    }

    private static void handleGetTheDistanceToSpecialPlace(RoutingContext context, Pool client){

        String studentId = context.pathParam("id");
        String getLat = context.pathParam("lat");
        String getLon = context.pathParam("lon");
        Double placeLat = Double.parseDouble(getLat);
        Double placeLon = Double.parseDouble(getLon);

        System.out.println(getLat);
        System.out.println(getLon);
        System.out.println(placeLat);
        System.out.println(placeLon);



        String query = "SELECT student_id, student_location_latitude, student_location_longitude FROM student_details WHERE student_id IN (?)";

        client.getConnection(ar -> {
            if (ar.succeeded()) {
                SqlConnection connection = ar.result();

                connection.preparedQuery(query).execute(Tuple.of(studentId), res -> {
                    if (res.succeeded()) {
                        if (res.result().size() > 0) {

                            var rows = res.result();
                            var students = new JsonArray();
                            for(Row row: rows){
                                var student = new JsonObject()
                                        .put("student_id", row.getString("student_id"))
                                        .put("student_location_latitude", row.getDouble("student_location_latitude"))
                                        .put("student_location_longitude", row.getDouble("student_location_longitude"));

                                students.add(student);


                            }
                            Double student1_lat = null;
                            Double student1_lon = null;



                            for(int i = 0; i < students.size(); i++){

                                JsonObject student = students.getJsonObject(i);
                                String studentIdN = student.getString("student_id");

                                if(studentIdN.equals(studentId)){
                                    student1_lat = student.getDouble("student_location_latitude");
                                    student1_lon = student.getDouble("student_location_longitude");
                                }


                            }
                            double placeLat1;
                            double placeLon1;

                            student1_lat = Math.toRadians(student1_lat);
                            student1_lon = Math.toRadians(student1_lon);
                            placeLat1 = Math.toRadians(placeLat);
                            placeLon1 = Math.toRadians(placeLon);



                            double lon = student1_lon  - placeLon1;
                            double lat = student1_lat - placeLat1;

                            double a = Math.pow(Math.sin(lat / 2), 2)
                                    + Math.cos(student1_lat) * Math.cos(placeLat)
                                    * Math.pow(Math.sin(lon / 2),2);

                            double c = 2 * Math.asin(Math.sqrt(a));

                            double earthRadius = 6371;

                            double distance = c* earthRadius;

                            System.out.println("Distance is: "+ distance + " km");

                            context.response()
                                    .putHeader("content-type", "application/json")
                                    .setStatusCode(200)
                                    .end(new JsonObject()
                                            .put("status", "success")
                                            .put("students", students)
                                            .encodePrettily());

                        } else {

                            context.response()
                                    .putHeader("content-type", "application/json")
                                    .setStatusCode(404)
                                    .end(new JsonObject()
                                            .put("status", "error")
                                            .put("message", "Student not found")
                                            .encodePrettily());
                        }
                    } else {

                        System.out.println("Failed to retrieve data from MySQL: " + res.cause().getMessage());
                        context.response()
                                .putHeader("content-type", "application/json")
                                .setStatusCode(500)
                                .end(new JsonObject()
                                        .put("status", "error")
                                        .put("message", res.cause().getMessage())
                                        .encodePrettily());
                    }
                    connection.close();
                });
            } else {

                System.out.println("Failed to connect to MySQL: " + ar.cause().getMessage());
                context.response()
                        .putHeader("content-type", "application/json")
                        .setStatusCode(500)
                        .end(new JsonObject()
                                .put("status", "error")
                                .put("message", ar.cause().getMessage())
                                .encodePrettily());
            }
        });
    }



}




