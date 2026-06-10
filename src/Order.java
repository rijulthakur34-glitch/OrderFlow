public abstract class Order implements Comparable<Order> {
    protected String orderId;
    protected double orderValue;
    protected int priorityScore;

    public Order(String orderId, double orderValue, int priorityScore) {
        this.orderId = orderId;
        this.orderValue = orderValue;
        this.priorityScore = priorityScore;
    }

    // Abstract method showcasing Polymorphism
    public abstract double calculateTotalProcessingFee();

    public String getOrderId() { return orderId; }
    public double getOrderValue() { return orderValue; }
    
    // For sorting in PriorityQueue
    @Override
    public int compareTo(Order other) {
        // Higher priority score is processed first
        return Integer.compare(other.priorityScore, this.priorityScore);
    }
}
