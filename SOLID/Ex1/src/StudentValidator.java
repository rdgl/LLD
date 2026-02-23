import java.util.*;

public class StudentValidator {
    private static final Set<String> ALLOWED_PROGRAMS = Set.of("CSE", "AI", "SWE");

    public List<String> validate(Map<String, String> attributes) {
        List<String> errors = new ArrayList<>();

        if (val(attributes, "name").isBlank()) errors.add("name is required");

        String email = val(attributes, "email");
        if (email.isBlank() || !email.contains("@")) errors.add("email is invalid");

        String phone = val(attributes, "phone");
        if (phone.isBlank() || !phone.chars().allMatch(Character::isDigit)) errors.add("phone is invalid");

        if (!ALLOWED_PROGRAMS.contains(val(attributes, "program"))) errors.add("program is invalid");

        return errors;
    }

    private String val(Map<String, String> attributes, String key) {
        return attributes.getOrDefault(key, "");
    }
}
