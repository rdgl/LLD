import java.nio.charset.StandardCharsets;

public class CsvExporter extends Exporter {
    @Override
    protected int getMaxBodyLength() { return Integer.MAX_VALUE; }

    @Override
    protected String getName() { return "CSV"; }

    @Override
    protected ExportResult doExport(ExportRequest req) {
        // Fix: Quote the fields to preserve data semantics (newlines, commas)
        String body = quote(req.body);
        String title = quote(req.title);
        String csv = "title,body\n" + title + "," + body + "\n";
        return new ExportResult("text/csv", csv.getBytes(StandardCharsets.UTF_8));
    }

    private String quote(String s) {
        return "\"" + s.replace("\"", "\"\"") + "\"";
    }
}
