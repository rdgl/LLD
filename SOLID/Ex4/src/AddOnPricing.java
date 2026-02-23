import java.util.HashMap;
import java.util.Map;

public class AddOnPricing {
    private static final Map<AddOn, Double> PRICES = new HashMap<>();

    static {
        PRICES.put(AddOn.MESS, 1000.0);
        PRICES.put(AddOn.LAUNDRY, 500.0);
        PRICES.put(AddOn.GYM, 300.0);
    }

    public static double getPrice(AddOn addOn) {
        return PRICES.getOrDefault(addOn, 0.0);
    }
}
