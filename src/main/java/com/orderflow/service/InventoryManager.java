package com.orderflow.service;

import com.orderflow.exception.InventoryShortageException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class InventoryManager {
    private static final Logger logger = Logger.getLogger(InventoryManager.class.getName());
    
    // Collection API: HashMap to act as an in-memory fast-lookup inventory
    private Map<String, Integer> inventoryMap;

    public InventoryManager() {
        this.inventoryMap = new HashMap<>();
    }

    public void addInventory(String productId, int quantity) {
        inventoryMap.put(productId, inventoryMap.getOrDefault(productId, 0) + quantity);
        logger.info("Added inventory: " + quantity + " units of " + productId);
    }

    public void deductInventory(String productId, int quantity) throws InventoryShortageException {
        int currentStock = inventoryMap.getOrDefault(productId, 0);
        if (currentStock < quantity) {
            throw new InventoryShortageException("Insufficient stock for product: " + productId);
        }
        inventoryMap.put(productId, currentStock - quantity);
    }

    public boolean hasSufficientStock(Map<String, Integer> itemsToProcess) {
        for (Map.Entry<String, Integer> entry : itemsToProcess.entrySet()) {
            if (inventoryMap.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }
}
