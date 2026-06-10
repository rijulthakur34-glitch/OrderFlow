package com.orderflow.service;

import com.orderflow.database.DatabaseManager;
import com.orderflow.exception.OrderProcessingException;
import com.orderflow.model.Order;

import java.util.PriorityQueue;
import java.util.logging.Logger;

public class OrderProcessor {
    private static final Logger logger = Logger.getLogger(OrderProcessor.class.getName());
    
    // Collection API & DSA: PriorityQueue for processing highest priority orders first
    private PriorityQueue<Order> orderQueue;
    private InventoryManager inventoryManager;
    private DatabaseManager databaseManager;

    public OrderProcessor(InventoryManager inventoryManager, DatabaseManager databaseManager) {
        this.orderQueue = new PriorityQueue<>();
        this.inventoryManager = inventoryManager;
        this.databaseManager = databaseManager;
    }

    public void enqueueOrder(Order order) {
        if (inventoryManager.hasSufficientStock(order.getItems())) {
            orderQueue.add(order);
            logger.info("Enqueued " + order.getClass().getSimpleName() + " - ID: " + order.getOrderId());
        } else {
            logger.warning("Failed to enqueue order " + order.getOrderId() + ": Insufficient inventory.");
        }
    }

    public void processAllOrders() throws OrderProcessingException {
        logger.info("--- Starting Order Processing Batch ---");
        
        while (!orderQueue.isEmpty()) {
            Order currentOrder = orderQueue.poll(); // O(log N) retrieval
            
            try {
                // Deduct inventory
                for (var entry : currentOrder.getItems().entrySet()) {
                    inventoryManager.deductInventory(entry.getKey(), entry.getValue());
                }
                
                // Polymorphic calculation
                double fee = currentOrder.calculateTotalProcessingFee(); 
                
                logger.info("Processed " + currentOrder.getOrderId() + 
                            " | Value: $" + currentOrder.getOrderValue() +
                            " | Fee Collected: $" + String.format("%.2f", fee));
                
                // Persist
                databaseManager.logProcessedOrder(currentOrder.getOrderId(), fee);

            } catch (Exception e) {
                throw new OrderProcessingException("Error processing order " + currentOrder.getOrderId(), e);
            }
        }
        logger.info("--- Order Processing Complete ---");
    }
}
