public abstract class ShipmentOrder implements SummaryPrintable {

    protected String orderNumber;
    protected String customerName;
    protected double distanceKm;
    protected double baseFee;
    protected boolean insured;
    protected double lastCalculatedPrice;

    public ShipmentOrder(String orderNumber, String customerName,
                         double distanceKm, double baseFee, boolean insured) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.distanceKm = distanceKm;
        this.baseFee = baseFee;
        this.insured = insured;
    }

    public final void processOrder() {
        validateOrder();
        validateSpecificRules();

        double price = calculateBasePrice();
        price += calculateAdditionalFee();
        price = applyInsurance(price);
        price = applyBusinessDiscount(price);

        lastCalculatedPrice = price;
        printProcessingResult();
    }

    private void validateOrder() {
        if (orderNumber == null || orderNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Numer zamówienia nie może być pusty.");
        }
        if (distanceKm < 0) {
            throw new IllegalArgumentException("Odległość dostawy nie może być ujemna.");
        }
    }

    protected void validateSpecificRules() {

    }

    private double applyInsurance(double price) {
        if (insured) {
            return price * 1.07; // Dodaje 7%
        }
        return price;
    }

    protected double applyBusinessDiscount(double price) {
        return price;
    }

    private void printProcessingResult() {
        System.out.println("Pomyślnie przetworzono zamówienie: " + orderNumber);
    }

    @Override
    public String buildSummaryLine() {
        return String.format("[%s] %s - %s | Do zapłaty: %.2f PLN",
                orderNumber, getShipmentType(), customerName, lastCalculatedPrice);
    }

    protected abstract double calculateBasePrice();
    protected abstract double calculateAdditionalFee();
    public abstract String getShipmentType();
}