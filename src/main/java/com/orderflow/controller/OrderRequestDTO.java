package com.orderflow.controller;

import java.util.Map;

public class OrderRequestDTO {
    private String orderId;
    private double value;
    private String type; // "STANDARD" or "EXPRESS"
    private Map<String, Integer> items;

    // Getters and Setters
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Map<String, Integer> getItems() { return items; }
    public void setItems(Map<String, Integer> items) { this.items = items; }
}
