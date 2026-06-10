public class DatabaseManager {
    // Simulated PostgreSQL Connection
    private static final String URL = "jdbc:postgresql://localhost:5432/ecommerce_db";

    public void logProcessedOrder(String orderId, double fee) {
        String query = "INSERT INTO processed_orders (order_id, processing_fee) VALUES (?, ?)";
        // System.out.println("Connecting to " + URL);
        System.out.println("Executing SQL: " + query);
        System.out.println("Data saved -> Order: " + orderId + " | Fee logged: $" + String.format("%.2f", fee));
    }
}
