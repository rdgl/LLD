import java.util.Objects;

public abstract class Exporter {
    public final ExportResult export(ExportRequest req) {
        validatePreconditions(req);
        ExportResult result = doExport(req);
        validatePostconditions(result);
        return result;
    }

    protected void validatePreconditions(ExportRequest req) {
        if (req == null) throw new IllegalArgumentException("Request cannot be null");
        if (req.title == null || req.body == null) {
            throw new IllegalArgumentException("Title and body cannot be null");
        }
        if (req.body.length() > getMaxBodyLength()) {
            throw new IllegalArgumentException(getName() + " cannot handle content > " + getMaxBodyLength() + " chars");
        }
    }

    private void validatePostconditions(ExportResult result) {
        Objects.requireNonNull(result, "Exporter returned null result");
        Objects.requireNonNull(result.contentType, "Result content type is null");
        Objects.requireNonNull(result.bytes, "Result bytes are null");
    }

    protected abstract int getMaxBodyLength();
    protected abstract String getName();
    protected abstract ExportResult doExport(ExportRequest req);
}
