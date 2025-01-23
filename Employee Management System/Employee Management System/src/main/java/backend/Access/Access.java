package backend.Access;

import backend.Components.JWTClass;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.sqlclient.Pool;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.SqlConnection;
import io.vertx.sqlclient.Tuple;
import org.mindrot.jbcrypt.BCrypt;

public class Access {

    public static String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String password, String hashPassword){
        return BCrypt.checkpw(password, hashPassword);
    }


    public static void handleSignIn(RoutingContext context, Pool client){

        context.request().bodyHandler(buffer -> {

            JsonObject jsonObject = buffer.toJsonObject();

            String empId = jsonObject.getString("empId");
            String depId = jsonObject.getString("depId");
            String empName  = jsonObject.getString("empName");
            String empGmail = jsonObject.getString("empGmail");
            String description = jsonObject.getString("description");
            String password = jsonObject.getString("password");

            if(empId == null || depId == null || empName == null || empGmail == null || password == null){
                context.response()
                        .setStatusCode(400)
                        .end("Please, Fill the details");

                return;
            }

            String hashPassword = hashPassword(password);

            String sql = "INSERT INTO employee_details (empId, depId, empName,empGmail, password) VALUES (?, ?, ?, ?, ?)";

//            client.preparedQuery(sql)
//                    .execute(Tuple.of(empId, depId, empName, empGmail, department, hashPassword)), ar ->{
//                        if(ar.s)
//            }

            client.getConnection(ar -> {
                if (ar.succeeded()){
                    SqlConnection connection = ar.result();
                    connection.preparedQuery(sql).execute(Tuple.of(empId, depId, empName, empGmail,hashPassword), res -> {
                        System.out.println("Data inserted into MySQL: empId: " + empId+ ", depId: "+depId+", empGmail: "+empGmail);
                        context.response()
                                .putHeader("content-type", "application/json")
                                .setStatusCode(200)
                                .end(new JsonObject()
                                        .put("status", "success")
                                        .put("empId", empId)
                                        .put("depId", depId)
                                        .put("empName", empName)
                                        .put("empGmail", empGmail)
                                        .put("password", hashPassword)
                                        .encodePrettily());


                    });
                }else {
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

    public static void handleLogIn(RoutingContext context, Pool client){

        context.request().bodyHandler(buffer -> {
            JsonObject jsonObject = buffer.toJsonObject();

            String userId = jsonObject.getString("empId");
            String userPassword = jsonObject.getString("password");

            String sql = "SELECT empId,depId,empName,empGmail,password,description,adminReview FROM employee_details WHERE empId = ?";

            client.getConnection(ar -> {
                SqlConnection connection = ar.result();
                connection.preparedQuery(sql).execute(Tuple.of(userId), res -> {
                    if(res.succeeded()){
                        if(res.result().size() > 0){

                            Row row = res.result().iterator().next();
                            JsonObject responseJson = new JsonObject();
                            responseJson.put("empId", row.getString("empId"));
                            responseJson.put("depId", row.getString("depId"));
                            responseJson.put("empName", row.getString("empName"));
                            responseJson.put("empGmail",row.getString("empGmail"));
                            responseJson.put("description",row.getString("description"));
                            responseJson.put("adminReview",row.getString("adminReview"));


                            //String hashPassword = res.result().iterator().next().getString("password");

                            String hashPassword = row.getString("password");

                            if(checkPassword(userPassword, hashPassword)){
                                String jwtToken = JWTClass.generateToken(userId);
                                JsonObject jwtResponse = new JsonObject();

                                jwtResponse.put("status", "success");
                                jwtResponse.put("token", jwtToken);
                                jwtResponse.put("userDetails", responseJson);

                                context.response()
                                        .setStatusCode(200)
                                        .putHeader("Content-Type", "application/json")
                                        .end(jwtResponse.encodePrettily());
                            }
                            else {
                                context.response()
                                        .setStatusCode(401)
                                        .end("Invalid Password!");
                            }
                        }
                        else {
                            context.response()
                                    .setStatusCode(500)
                                    .end("Use Correct Inputs");
                        }
                    }
                    else {
                        context.response()
                                .setStatusCode(500)
                                .end("Database Connection Failed");
                    }
                });
            });
        });
    }

    public static void handleAdminSignIn(RoutingContext context, Pool client){

        context.request().bodyHandler(buffer -> {

                JsonObject jsonObject = buffer.toJsonObject();

                String adminId = jsonObject.getString("adminId");
                String depId = jsonObject.getString("depId");
                String adminName  = jsonObject.getString("adminName");
                String adminGmail = jsonObject.getString("adminGmail");
                String adminPassword = jsonObject.getString("adminPassword");

                if(adminId == null || depId == null || adminName == null || adminGmail == null || adminPassword == null){
                    context.response()
                            .setStatusCode(400)
                            .end("Please, Fill the details");

                    return;
                }

                String hashPassword = hashPassword(adminPassword);

                String sql = "INSERT INTO admin_details (adminId, depId,adminName,adminGmail,password) VALUES (?, ?, ?, ?, ?)";


                client.getConnection(ar -> {
                    if (ar.succeeded()){
                        SqlConnection connection = ar.result();
                        connection.preparedQuery(sql).execute(Tuple.of(adminId, depId, adminName, adminGmail,hashPassword), res -> {
                            System.out.println("Data inserted into MySQL: adminId: " + adminId+ ", depId: "+depId+", adminGmail: "+adminGmail);
                            context.response()
                                    .putHeader("content-type", "application/json")
                                    .setStatusCode(200)
                                    .end(new JsonObject()
                                            .put("status", "success")
                                            .put("adminId", adminId)
                                            .put("depId", depId)
                                            .put("adminName", adminName)
                                            .put("adminGmail", adminGmail)
                                            .put("adminPassword", hashPassword)
                                            .encodePrettily());


                        });
                    }else {
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

    public static void handleAdminLogIn(RoutingContext context, Pool client){

        context.request().bodyHandler(buffer -> {
            JsonObject jsonObject = buffer.toJsonObject();

            String adminId = jsonObject.getString("adminId");
            String adminPassword = jsonObject.getString("password");

            String sql = "SELECT adminId, password FROM admin_details WHERE adminId = ?";

            client.getConnection(ar -> {
                SqlConnection connection = ar.result();
                connection.preparedQuery(sql).execute(Tuple.of(adminId), res -> {
                    if(res.succeeded()){
                        if(res.result().size() > 0){
                            String hashPassword = res.result().iterator().next().getString("password");

                            if(checkPassword(adminPassword, hashPassword)){
                                context.response()
                                        .setStatusCode(200)
                                        .end("Login Successful");
                            }
                            else {
                                context.response()
                                        .setStatusCode(401)
                                        .end("Invalid Password!");
                            }
                        }
                        else {
                            context.response()
                                    .setStatusCode(500)
                                    .end("Use correct Inputs");
                        }
                    }
                    else {
                        context.response()
                                .setStatusCode(500)
                                .end("Database Connection Failed");
                    }
                });
            });
        });
    }


}
