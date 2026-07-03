package com.orderflow.service;

import com.orderflow.database.DatabaseManager;
import com.orderflow.exception.OrderProcessingException;
import com.orderflow.model.Order;
import org.springframework.stereotype.Service;

import java.util.PriorityQueue;
import java.util.logging.Logger;

@Service
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

    public String processSingleOrder(Order order) throws OrderProcessingException {
        if (!inventoryManager.hasSufficientStock(order.getItems())) {
            return "Failed: Insufficient inventory for order " + order.getOrderId();
        }

        orderQueue.add(order);
        logger.info("Enqueued " + order.getClass().getSimpleName() + " - ID: " + order.getOrderId());
        
        // Process immediately for the REST API simplicity
        Order currentOrder = orderQueue.poll();
        
        try {
            // Deduct inventory
            for (var entry : currentOrder.getItems().entrySet()) {
                inventoryManager.deductInventory(entry.getKey(), entry.getValue());
            }
            
            // Polymorphic calculation
            double fee = currentOrder.calculateTotalProcessingFee(); 
            
            String successMsg = "Processed " + currentOrder.getOrderId() + 
                        " | Value: $" + currentOrder.getOrderValue() +
                        " | Fee Collected: $" + String.format("%.2f", fee);
            logger.info(successMsg);
            
            // Persist
            databaseManager.logProcessedOrder(currentOrder.getOrderId(), fee);
            return successMsg;

        } catch (Exception e) {
            throw new OrderProcessingException("Error processing order " + currentOrder.getOrderId(), e);
        }
    }
}
