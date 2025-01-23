package backend.db;

import backend.API.APIConnector;
import io.vertx.core.Vertx;
import io.vertx.mysqlclient.MySQLBuilder;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.sqlclient.*;

public class DBConnection {
    public static void main(String[] args) {

        Vertx vertx = Vertx.vertx();

        MySQLConnectOptions connectOptions = new MySQLConnectOptions()
                .setPort(3306)
                .setHost("localhost")
                .setDatabase("employee_management_system")
                .setUser("root")
                .setPassword("madawa@2000");

        PoolOptions poolOptions = new PoolOptions().setMaxSize(5);

        //Pool client = Pool.pool(vertx, connectOptions, poolOptions);

        Pool client = MySQLBuilder.pool()
                .with(poolOptions)
                .connectingTo(connectOptions)
                .using(vertx)
                .build();

        APIConnector apiConnector = new APIConnector();

        vertx.createHttpServer()
                .requestHandler(apiConnector.setupRouter(vertx, client))
                .listen(8080, http -> {
                    if (http.succeeded()) {
                        System.out.println("HTTP server started on port 8080");
                    } else {
                        System.out.println("Failed to start HTTP server: " + http.cause().getMessage());

                    }
                });
    }
}
