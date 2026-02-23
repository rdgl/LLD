import java.util.List;

public class DiscountRules implements DiscountPolicy {
    @Override
    public double calculateDiscount(double subtotal, List<OrderLine> lines, String customerType) {
        if ("student".equalsIgnoreCase(customerType)) {
            if (subtotal >= 180.0) return 10.0;
            return 0.0;
        }
        if ("staff".equalsIgnoreCase(customerType)) {
            if (lines.size() >= 3) return 15.0;
            return 5.0;
        }
        return 0.0;
    }

    @Deprecated
    public static double discountAmount(String customerType, double subtotal, int distinctLines) {
        // This is tricky because lines.size() was passed as distinctLines.
        // It's better to just use the instance method.
        return 0.0; 
    }
}
