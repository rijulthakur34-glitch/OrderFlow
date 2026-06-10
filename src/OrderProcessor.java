import java.util.*;

public class OrderProcessor {
    // Collection API: PriorityQueue for processing highest priority orders first (DSA)
    private PriorityQueue<Order> orderQueue;
    
    // Collection API: HashMap to act as an in-memory fast-lookup inventory
    private Map<String, Integer> inventoryMap;

    public OrderProcessor() {
        orderQueue = new PriorityQueue<>();
        inventoryMap = new HashMap<>();
    }

    public void addInventory(String productId, int quantity) {
        inventoryMap.put(productId, quantity);
    }

    public boolean checkInventory(String productId) {
        return inventoryMap.getOrDefault(productId, 0) > 0;
    }

    public void enqueueOrder(Order order) {
        orderQueue.add(order);
        System.out.println("Enqueued " + order.getClass().getSimpleName() + " - ID: " + order.getOrderId());
    }

    public void processAllOrders() {
        System.out.println("\n--- Processing Orders ---");
        while (!orderQueue.isEmpty()) {
            Order currentOrder = orderQueue.poll(); // O(log N) retrieval
            double fee = currentOrder.calculateTotalProcessingFee(); // Polymorphic call
            
            System.out.println("Processing " + currentOrder.getOrderId() + 
                               " | Value: $" + currentOrder.getOrderValue() +
                               " | Fee: $" + String.format("%.2f", fee));
        }
    }
}
