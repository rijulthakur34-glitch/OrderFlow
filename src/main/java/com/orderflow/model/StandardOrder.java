package com.orderflow.model;

import java.util.Map;

public class StandardOrder extends Order {
    private static final double STANDARD_FEE_PERCENTAGE = 0.05;

    public StandardOrder(String orderId, double orderValue, Map<String, Integer> items) {
        super(orderId, orderValue, 1, items); // Priority 1 (Lowest)
    }

    // Method Overriding
    @Override
    public double calculateTotalProcessingFee() {
        return getOrderValue() * STANDARD_FEE_PERCENTAGE;
    }
}
