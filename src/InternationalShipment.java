public class InternationalShipment extends ShipmentOrder {

    private String destinationCountry;
    private boolean customsDocumentsRequired;
    private boolean expressDelivery;

    public InternationalShipment(String orderNumber, String customerName, double distanceKm,
                                 double baseFee, boolean insured, String destinationCountry,
                                 boolean customsDocumentsRequired, boolean expressDelivery) {
        super(orderNumber, customerName, distanceKm, baseFee, insured);
        this.destinationCountry = destinationCountry;
        this.customsDocumentsRequired = customsDocumentsRequired;
        this.expressDelivery = expressDelivery;
    }

    @Override
    public String getShipmentType() {
        return "International";
    }

    @Override
    protected double calculateBasePrice() {
        return baseFee + (distanceKm * 2.10);
    }

    @Override
    protected double calculateAdditionalFee() {
        double additionalFee = 0.0;
        if (customsDocumentsRequired) {
            additionalFee += 45.0;
        }
        if (expressDelivery) {
            additionalFee += 80.0;
        }
        return additionalFee;
    }

    @Override
    protected void validateSpecificRules() {
        if (destinationCountry == null || destinationCountry.trim().isEmpty()) {
            throw new IllegalArgumentException("Kraj docelowy w przesyłce międzynarodowej nie może być pusty.");
        }
    }

    @Override
    protected double applyBusinessDiscount(double price) {
        if (!expressDelivery && distanceKm > 1000) {
            return price * 0.97;
        }
        return price;
    }
}