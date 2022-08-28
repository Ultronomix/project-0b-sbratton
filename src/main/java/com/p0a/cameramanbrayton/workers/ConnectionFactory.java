package com.p0a.cameramanbrayton.workers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// Implements the Factory and Singleton design patterns
public class ConnectionFactory {

    private static ConnectionFactory connectionFactory;
    private Properties dbProps = new Properties();

    private ConnectionFactory() {
        try {
            dbProps.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            // TODO replace RuntimeException with a custom exception
            throw new RuntimeException("Could not read from properties file!");
        }
    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }
        return connectionFactory;
    }

    public Connection getConnection() throws SQLException {

        Connection conn = null;
        return DriverManager.getConnection(dbProps.getProperty("db-url"),
                    dbProps.getProperty("db-username"), dbProps.getProperty("db-password"));

    }

}
