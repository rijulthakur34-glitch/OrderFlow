public class Main {
    public static void main(String[] args) {
        System.out.println("--- OrderFlow: E-Commerce Processing Engine ---\n");

        OrderProcessor processor = new OrderProcessor();
        
        // Setup inventory (HashMaps)
        processor.addInventory("LAPTOP-X", 50);
        processor.addInventory("MOUSE-Y", 200);

        // Create orders (Polymorphism)
        Order o1 = new StandardOrder("ORD-1001", 500.00);
        Order o2 = new ExpressOrder("ORD-1002", 1200.00);
        Order o3 = new StandardOrder("ORD-1003", 150.00);
        Order o4 = new ExpressOrder("ORD-1004", 300.00);

        // Enqueue orders (PriorityQueue will sort them)
        processor.enqueueOrder(o1);
        processor.enqueueOrder(o2);
        processor.enqueueOrder(o3);
        processor.enqueueOrder(o4);

        // Process orders polymorphically based on priority
        processor.processAllOrders();

        System.out.println("\n--- Syncing to PostgreSQL Database ---");
        DatabaseManager db = new DatabaseManager();
        db.logProcessedOrder(o2.getOrderId(), o2.calculateTotalProcessingFee());
    }
}
