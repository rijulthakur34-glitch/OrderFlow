package com.orderflow.database;

import org.springframework.stereotype.Service;
import java.util.logging.Logger;

@Service
public class DatabaseManager {
    private static final Logger logger = Logger.getLogger(DatabaseManager.class.getName());
    private DatabaseConfig config;

    public DatabaseManager() {
        this.config = new DatabaseConfig();
    }

    public void logProcessedOrder(String orderId, double fee) {
        String query = "INSERT INTO processed_orders (order_id, processing_fee) VALUES (?, ?)";
        logger.info("Database Save -> Order: " + orderId + " | Fee logged: $" + String.format("%.2f", fee));
    }
}
