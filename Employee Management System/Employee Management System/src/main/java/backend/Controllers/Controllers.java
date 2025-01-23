package backend.Controllers;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.sqlclient.Pool;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.SqlConnection;
import io.vertx.sqlclient.Tuple;
public class Controllers {

    public static void handleUpdateEmpDetails(RoutingContext context, Pool client){
        String empId = context.pathParam("empId");
        context.request().bodyHandler(buffer -> {
            JsonObject jsonObject = buffer.toJsonObject();

            String empID = jsonObject.getString("empId");
            String depId = jsonObject.getString("depId");
            String empName = jsonObject.getString("empName");
            String empGmail = jsonObject.getString("empGmail");
            String description = jsonObject.getString("description");

            String sql = "UPDATE employee_details SET empId =?,depId = ?,  empName = ?,empGmail = ?, description = ? WHERE empId = ?";

            client.getConnection(ar -> {
                if(ar.succeeded()){
                    SqlConnection connection = ar.result();
                    connection.preparedQuery(sql).execute(Tuple.of(empID,depId,empName,empGmail,description,empId), res ->{
                        if(res.succeeded()){
                            if(res.result().rowCount() > 0){
                                System.out.println("update description: empID: " + empId + " description: "+description);
                                context.response()
                                        .putHeader("status","application/json")
                                        .setStatusCode(200)
                                        .end(new JsonObject()
                                                .put("status", "success")
                                                .put("empId",empID)
                                                .put("depId",depId)
                                                .put("empName", empName)
                                                .put("empGmail",empGmail)
                                                .put("description", description)
                                                .encodePrettily());
                            }
                            else{
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
                            System.out.println("Failed to update data in MySQL: " + res.cause().getMessage());
                            context.response()
                                    .putHeader("content-type", "application/json")
                                    .setStatusCode(500)
                                    .end(new JsonObject()
                                            .put("status", "error")
                                            .put("message", res.cause().getMessage())
                                            .encodePrettily());
                        }
                    });
                }
                else {
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

    public static void handleGetEmployeeDetails(RoutingContext context, Pool client){

        String query = "SELECT empId, depId, empName, empGmail, description,adminReview FROM employee_details";

        client.getConnection(ar ->{
            if(ar.succeeded()){
                SqlConnection connection = ar.result();
                connection.preparedQuery(query).execute(res ->{
                    if(res.succeeded()){
                        var rows = res.result();
                        var employees = new JsonArray();
                        for(Row row: rows){
                            var employee = new JsonObject()
                                    .put("employee_id",row.getString("empId"))
                                    .put("department_id",row.getString("depId"))
                                    .put("employee_name", row.getString("empName"))
                                    .put("employee_gmail", row.getString("empGmail"))
                                    .put("description", row.getString("description"))
                                    .put("adminReview",row.getString("adminReview"));

                            employees.add(employee);

                        }
                        context.response()
                                .putHeader("content-type", "application/json")
                                .setStatusCode(200)
                                .end(new JsonObject()
                                        .put("employees", employees)
                                        .encodePrettily());
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
                    connection.close();
                });
            }
            else {
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

    public static void handleGetAdminData(RoutingContext context, Pool client){

        String query = "SELECT adminId, depId, adminName, adminGmail FROM admin_details";

        client.getConnection(ar ->{
            if(ar.succeeded()){
                SqlConnection connection = ar.result();
                connection.preparedQuery(query).execute(res ->{
                    if(res.succeeded()){
                        var rows = res.result();
                        var admins = new JsonArray();
                        for(Row admin: rows){
                            var adminObject = new JsonObject()
                                    .put("admin_id",admin.getString("adminId"))
                                    .put("department_id",admin.getString("depId"))
                                    .put("admin_name", admin.getString("adminName"))
                                    .put("admin_gmail", admin.getString("adminGmail"));

                            admins.add(adminObject);

                        }
                        context.response()
                                .putHeader("content-type", "application/json")
                                .setStatusCode(200)
                                .end(new JsonObject()
                                        .put("admins", admins)
                                        .encodePrettily());
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
                    connection.close();
                });
            }
            else {
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

    public static void handleUpdateAdminReview(RoutingContext context, Pool client){

        String empId = context.pathParam("empId");
        context.request().bodyHandler(buffer -> {
            JsonObject jsonObject = buffer.toJsonObject();

            String adminReview = jsonObject.getString("adminReview");

            String sql = "UPDATE employee_details SET adminReview = ? WHERE empId = ?";

            client.getConnection(ar -> {
                if(ar.succeeded()){
                    SqlConnection connection = ar.result();
                    connection.preparedQuery(sql).execute(Tuple.of(adminReview, empId), res ->{
                        if(res.succeeded()){
                            if(res.result().rowCount() > 0){
                                System.out.println("update adminReview: empID: " + empId + " adminReview: "+adminReview);
                                context.response()
                                        .putHeader("status","application/json")
                                        .setStatusCode(200)
                                        .end(new JsonObject()
                                                .put("status", "success")
                                                .put("adminReview", adminReview)
                                                .encodePrettily());
                            }
                            else{
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
                            System.out.println("Failed to update data in MySQL: " + res.cause().getMessage());
                            context.response()
                                    .putHeader("content-type", "application/json")
                                    .setStatusCode(500)
                                    .end(new JsonObject()
                                            .put("status", "error")
                                            .put("message", res.cause().getMessage())
                                            .encodePrettily());
                        }
                    });
                }
                else {
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

    public static void handleGetEmpDetailsById(RoutingContext context, Pool client){
        String empId = context.pathParam("empId");

        String query = "SELECT empId, depId, empName, empGmail, description, adminReview FROM employee_details WHERE empId = ?";

        client.getConnection(ar -> {
            if (ar.succeeded()) {
                SqlConnection connection = ar.result();
                connection.preparedQuery(query).execute(Tuple.of(empId), res -> {
                    if (res.succeeded()) {
                        Row row = res.result().iterator().hasNext() ? res.result().iterator().next() : null;
                        if (row != null) {
                            System.out.println("hi");
                            JsonObject employee = new JsonObject()
                                    .put("empId", row.getString("empId"))
                                    .put("depId", row.getString("depId"))
                                    .put("empName", row.getString("empName"))
                                    .put("empGmail", row.getString("empGmail"))
                                    .put("description", row.getString("description"))
                                    .put("adminReview", row.getString("adminReview"));

                            context.response()
                                    .putHeader("content-type", "application/json")
                                    .setStatusCode(200)
                                    .end(employee.encodePrettily());
                        } else {
                            context.response()
                                    .putHeader("content-type", "application/json")
                                    .setStatusCode(404)
                                    .end(new JsonObject()
                                            .put("status", "error")
                                            .put("message", "Employee not found")
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
