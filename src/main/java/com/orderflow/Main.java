package com.orderflow;

import com.orderflow.database.DatabaseManager;
import com.orderflow.exception.OrderProcessingException;
import com.orderflow.model.ExpressOrder;
import com.orderflow.model.Order;
import com.orderflow.model.StandardOrder;
import com.orderflow.service.InventoryManager;
import com.orderflow.service.OrderProcessor;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== OrderFlow: Enterprise E-Commerce Processing Engine ===\n");

        // 1. Initialize Services
        InventoryManager inventoryManager = new InventoryManager();
        DatabaseManager databaseManager = new DatabaseManager();
        OrderProcessor processor = new OrderProcessor(inventoryManager, databaseManager);
        
        // 2. Setup Inventory
        inventoryManager.addInventory("LAPTOP-X", 50);
        inventoryManager.addInventory("MOUSE-Y", 200);

        // 3. Create Orders (Polymorphism)
        Order o1 = new StandardOrder("ORD-1001", 500.00, Map.of("LAPTOP-X", 1));
        Order o2 = new ExpressOrder("ORD-1002", 1200.00, Map.of("LAPTOP-X", 2, "MOUSE-Y", 1));
        Order o3 = new StandardOrder("ORD-1003", 150.00, Map.of("MOUSE-Y", 3));
        Order o4 = new ExpressOrder("ORD-1004", 300.00, Map.of("LAPTOP-X", 1));

        // 4. Enqueue Orders (PriorityQueue handles sorting)
        processor.enqueueOrder(o1);
        processor.enqueueOrder(o2);
        processor.enqueueOrder(o3);
        processor.enqueueOrder(o4);

        // 5. Process Batch
        try {
            processor.processAllOrders();
        } catch (OrderProcessingException e) {
            System.err.println("Critical Error in processing loop: " + e.getMessage());
        }
    }
}
