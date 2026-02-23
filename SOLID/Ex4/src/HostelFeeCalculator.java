import java.util.*;

public class HostelFeeCalculator {
    private final BookingRepository repo;
    private final FeePrinter printer;

    public HostelFeeCalculator(BookingRepository repo, FeePrinter printer) {
        this.repo = repo;
        this.printer = printer;
    }

    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        printer.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
        double base = RoomPricing.getPrice(req.roomType);
        
        double add = 0.0;
        for (AddOn a : req.addOns) {
            add += AddOnPricing.getPrice(a);
        }

        return new Money(base + add);
    }
}
