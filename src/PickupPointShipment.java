public class PickupPointShipment extends ShipmentOrder {

    private String lockerSize;
    private boolean fragile;

    public PickupPointShipment(String orderNumber, String customerName, double distanceKm,
                               double baseFee, boolean insured, String lockerSize,
                               boolean fragile) {
        super(orderNumber, customerName, distanceKm, baseFee, insured);
        this.lockerSize = lockerSize;
        this.fragile = fragile;
    }

    @Override
    public String getShipmentType() {
        return "Pickup point";
    }

    @Override
    protected double calculateBasePrice() {
        return baseFee + (distanceKm * 0.75);
    }

    @Override
    protected double calculateAdditionalFee() {
        double additionalFee = 0.0;

        switch (lockerSize) {
            case "S": additionalFee += 5.0; break;
            case "M": additionalFee += 10.0; break;
            case "L": additionalFee += 18.0; break;
        }

        if (fragile) {
            additionalFee += 12.0;
        }
        return additionalFee;
    }

    @Override
    protected void validateSpecificRules() {
        if (!"S".equals(lockerSize) && !"M".equals(lockerSize) && !"L".equals(lockerSize)) {
            throw new IllegalArgumentException("Niewłaściwy rozmiar skrytki. Możliwe rozmiary to:" +
                    " 'S', 'M' lub 'L'.");
        }
    }
}
