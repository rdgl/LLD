import java.util.HashMap;
import java.util.Map;

public class RoomPricing {
    private static final Map<Integer, Double> PRICES = new HashMap<>();

    static {
        PRICES.put(LegacyRoomTypes.SINGLE, 14000.0);
        PRICES.put(LegacyRoomTypes.DOUBLE, 15000.0);
        PRICES.put(LegacyRoomTypes.TRIPLE, 12000.0);
        PRICES.put(LegacyRoomTypes.DELUXE, 16000.0);
    }

    public static double getPrice(int roomType) {
        return PRICES.getOrDefault(roomType, 16000.0);
    }
}
