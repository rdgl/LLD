import java.util.List;

public interface DiscountPolicy {
    double calculateDiscount(double subtotal, List<OrderLine> lines, String customerType);
}
