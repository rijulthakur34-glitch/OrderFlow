package com.orderflow.model;

import java.util.Map;

public abstract class Order implements Comparable<Order> {
    protected String orderId;
    protected double orderValue;
    protected int priorityScore;
    protected Map<String, Integer> items;

    public Order(String orderId, double orderValue, int priorityScore, Map<String, Integer> items) {
        this.orderId = orderId;
        this.orderValue = orderValue;
        this.priorityScore = priorityScore;
        this.items = items;
    }

    // Abstract method showcasing Polymorphism
    public abstract double calculateTotalProcessingFee();

    public String getOrderId() { return orderId; }
    public double getOrderValue() { return orderValue; }
    public Map<String, Integer> getItems() { return items; }
    
    // For sorting in PriorityQueue (DSA)
    @Override
    public int compareTo(Order other) {
        // Higher priority score is processed first
        return Integer.compare(other.priorityScore, this.priorityScore);
    }
}
