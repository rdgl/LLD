import java.nio.charset.StandardCharsets;

public class JsonExporter extends Exporter {
    @Override
    protected int getMaxBodyLength() { return Integer.MAX_VALUE; }

    @Override
    protected String getName() { return "JSON"; }

    @Override
    protected ExportResult doExport(ExportRequest req) {
        String json = "{\"title\":\"" + escape(req.title) + "\",\"body\":\"" + escape(req.body) + "\"}";
        return new ExportResult("application/json", json.getBytes(StandardCharsets.UTF_8));
    }

    private String escape(String s) {
        return s.replace("\"", "\\\"");
    }
}
