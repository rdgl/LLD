import java.util.ArrayList;
import java.util.List;

public class Invoice {
    public final String id;
    public final List<InvoiceLine> lines = new ArrayList<>();
    public double subtotal;
    public double taxPct;
    public double taxAmount;
    public double discountAmount;
    public double total;

    public Invoice(String id) {
        this.id = id;
    }

    public static class InvoiceLine {
        public final String name;
        public final int qty;
        public final double lineTotal;

        public InvoiceLine(String name, int qty, double lineTotal) {
            this.name = name;
            this.qty = qty;
            this.lineTotal = lineTotal;
        }
    }
}
