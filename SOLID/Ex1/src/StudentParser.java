import java.util.*;

public class StudentParser {
    public Map<String, String> parse(String raw) {
        Map<String, String> attributes = new LinkedHashMap<>();
        String[] parts = raw.split(";");
        for (String p : parts) {
            String[] t = p.split("=", 2);
            if (t.length == 2) attributes.put(t[0].trim(), t[1].trim());
        }
        return attributes;
    }
}
