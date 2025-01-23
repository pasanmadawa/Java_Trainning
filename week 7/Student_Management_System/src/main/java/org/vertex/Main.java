package org.vertex;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.jdbcclient.JDBCConnectOptions;
import io.vertx.jdbcclient.JDBCPool;
import io.vertx.sqlclient.Pool;
import io.vertx.sqlclient.PoolOptions;

public class Main {
    public static void main(String[] args) {

        Vertx vertx = Vertx.vertx();

        final JsonObject config = new JsonObject()
                .put("jdbcUrl", "jdbc:h2:~/test")
                .put("datasourceName", "pool-name")
                .put("username", "sa")
                .put("password", "")
                .put("max_pool_size", 16);

        JDBCPool pool = JDBCPool.pool(
                vertx,
                // configure the connection
                new JDBCConnectOptions()
                        // H2 connection string
                        .setJdbcUrl("jdbc:h2:~/test")
                        // username
                        .setUser("sa")
                        // password
                        .setPassword(""),
                // configure the pool
                new PoolOptions()
                        .setMaxSize(16)
                        .setName("pool-name")
        );

    }
}