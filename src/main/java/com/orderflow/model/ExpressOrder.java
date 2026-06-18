package com.orderflow.model;

import java.util.Map;

public class ExpressOrder extends Order {
    private static final double EXPRESS_FEE_PERCENTAGE = 0.10;
    private static final double RUSH_SURCHARGE = 5.00;

    public ExpressOrder(String orderId, double orderValue, Map<String, Integer> items) {
        super(orderId, orderValue, 10, items); // Priority 10 (Highest)
    }

    @Override
    public double calculateTotalProcessingFee() {
        return (getOrderValue() * EXPRESS_FEE_PERCENTAGE) + RUSH_SURCHARGE;
    }
}
