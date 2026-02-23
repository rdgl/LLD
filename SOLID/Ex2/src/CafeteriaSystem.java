import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceStore store;
    private final TaxPolicy taxPolicy;
    private final DiscountPolicy discountPolicy;
    private final InvoiceFormatter formatter;
    private int invoiceSeq = 1000;

    public CafeteriaSystem(InvoiceStore store, TaxPolicy taxPolicy, DiscountPolicy discountPolicy, InvoiceFormatter formatter) {
        this.store = store;
        this.taxPolicy = taxPolicy;
        this.discountPolicy = discountPolicy;
        this.formatter = formatter;
    }

    public void addToMenu(MenuItem i) { menu.put(i.id, i); }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);
        Invoice invoice = new Invoice(invId);

        double subtotal = 0.0;
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
            invoice.lines.add(new Invoice.InvoiceLine(item.name, l.qty, lineTotal));
        }

        invoice.subtotal = subtotal;
        invoice.taxPct = taxPolicy.getTaxRate(customerType);
        invoice.taxAmount = taxPolicy.calculateTax(subtotal, customerType);
        invoice.discountAmount = discountPolicy.calculateDiscount(subtotal, lines, customerType);
        invoice.total = subtotal + invoice.taxAmount - invoice.discountAmount;

        String printable = formatter.format(invoice);
        System.out.print(printable);

        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}
