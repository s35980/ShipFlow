public class DomesticCourierShipment extends ShipmentOrder {

    private double packageWeightKg;
    private boolean weekendDelivery;

    public DomesticCourierShipment(String orderNumber, String customerName, double distanceKm,
                                   double baseFee, boolean insured, double packageWeightKg,
                                   boolean weekendDelivery) {
        super(orderNumber, customerName, distanceKm, baseFee, insured);
        this.packageWeightKg = packageWeightKg;
        this.weekendDelivery = weekendDelivery;
    }

    @Override
    public String getShipmentType() {
        return "Domestic courier";
    }

    @Override
    protected double calculateBasePrice() {
        return baseFee + (distanceKm * 1.20);
    }

    @Override
    protected double calculateAdditionalFee() {
        double additionalFee = packageWeightKg * 4.00;
        if (weekendDelivery) {
            additionalFee += 25.0;
        }
        return additionalFee;
    }

    @Override
    protected double applyBusinessDiscount(double price) {
        if (distanceKm >= 300) {
            return price * 0.95;
        }
        return price;
    }
}