public interface TaxPolicy {
    double calculateTax(double subtotal, String customerType);
    double getTaxRate(String customerType);
}
