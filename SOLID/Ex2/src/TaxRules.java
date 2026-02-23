public class TaxRules implements TaxPolicy {
    @Override
    public double getTaxRate(String customerType) {
        if ("student".equalsIgnoreCase(customerType)) return 5.0;
        if ("staff".equalsIgnoreCase(customerType)) return 2.0;
        return 8.0;
    }

    @Override
    public double calculateTax(double subtotal, String customerType) {
        return subtotal * (getTaxRate(customerType) / 100.0);
    }
    
    @Deprecated
    public static double taxPercent(String customerType) {
        return new TaxRules().getTaxRate(customerType);
    }
}
