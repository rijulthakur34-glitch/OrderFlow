package com.orderflow.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DatabaseManager {
    private static final Logger logger = Logger.getLogger(DatabaseManager.class.getName());
    private DatabaseConfig config;

    public DatabaseManager() {
        this.config = new DatabaseConfig();
    }

    public Connection connect() throws SQLException {
        // In a real scenario, this uses the URL from properties
        String url = config.getProperty("db.url");
        String user = config.getProperty("db.username");
        String password = config.getProperty("db.password");
        
        // return DriverManager.getConnection(url, user, password);
        return null;
    }

    public void logProcessedOrder(String orderId, double fee) {
        String query = "INSERT INTO processed_orders (order_id, processing_fee) VALUES (?, ?)";
        
        logger.info("Executing SQL: " + query);
        logger.info("Data saved to Database -> Order: " + orderId + " | Fee logged: $" + String.format("%.2f", fee));
        
        /* Real execution block:
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, orderId);
            pstmt.setDouble(2, fee);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.severe("Database Error: " + e.getMessage());
        }
        */
    }
}
