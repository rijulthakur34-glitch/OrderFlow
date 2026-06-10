public class ExpressOrder extends Order {
    private static final double EXPRESS_FEE_PERCENTAGE = 0.10;
    private static final double RUSH_SURCHARGE = 5.00;

    public ExpressOrder(String orderId, double orderValue) {
        super(orderId, orderValue, 10); // Priority 10 (Highest)
    }

    // Method Overriding
    @Override
    public double calculateTotalProcessingFee() {
        return (getOrderValue() * EXPRESS_FEE_PERCENTAGE) + RUSH_SURCHARGE;
    }
}
