public class InvoiceFormatter {
    public String format(Invoice inv) {
        StringBuilder sb = new StringBuilder();
        sb.append("Invoice# ").append(inv.id).append("\n");

        for (Invoice.InvoiceLine line : inv.lines) {
            sb.append(String.format("- %s x%d = %.2f\n", line.name, line.qty, line.lineTotal));
        }

        sb.append(String.format("Subtotal: %.2f\n", inv.subtotal));
        sb.append(String.format("Tax(%.0f%%): %.2f\n", inv.taxPct, inv.taxAmount));
        sb.append(String.format("Discount: -%.2f\n", inv.discountAmount));
        sb.append(String.format("TOTAL: %.2f\n", inv.total));
        
        return sb.toString();
    }

    @Deprecated
    public static String identityFormat(String s) { return s; }
}
