public interface FeePrinter {
    void print(BookingRequest req, Money monthly, Money deposit);
}
