package com.orderflow.exception;

public class InventoryShortageException extends OrderProcessingException {
    public InventoryShortageException(String message) {
        super(message);
    }
}
