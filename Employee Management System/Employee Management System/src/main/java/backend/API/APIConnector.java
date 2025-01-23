package backend.API;

import backend.Access.Access;
import backend.Controllers.Controllers;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.sqlclient.Pool;

public class APIConnector {

    public static Router setupRouter(Vertx vertx, Pool client){
        Router router = Router.router(vertx);

        //employee
        router.post("/api/post/signIn").handler(context -> Access.handleSignIn(context, client));
        router.post("/api/post/logIn").handler(context -> Access.handleLogIn(context,client));
        router.put("/api/put/:empId").handler(context -> Controllers.handleUpdateEmpDetails(context,client));
        router.get("/api/get/getDetails").handler(context -> Controllers.handleGetEmployeeDetails(context,client));
        router.get("/api/get/:empId").handler(context -> Controllers.handleGetEmpDetailsById(context,client));

        //admin
        router.post("/api/post/adminSignIn").handler(context -> Access.handleAdminSignIn(context,client));
        router.post("/api/post/adminLogIn").handler(context -> Access.handleAdminLogIn(context,client));
        router.get("/api/adminDetails").handler(context -> Controllers.handleGetAdminData(context,client));
        router.put("/api/put/adminReview/:empId").handler(context -> Controllers.handleUpdateAdminReview(context,client));

        return router;
    }


}
