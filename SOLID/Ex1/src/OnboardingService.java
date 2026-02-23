import java.util.*;

public class OnboardingService {
    private final StudentRepository db;
    private final StudentParser parser = new StudentParser();
    private final StudentValidator validator = new StudentValidator();
    private final ResultPrinter printer = new ResultPrinter();

    public OnboardingService(StudentRepository db) { this.db = db; }

    // Intentionally violates SRP: parses + validates + creates ID + saves + prints.
    public void registerFromRawInput(String raw) {
        printer.printInput(raw);

        Map<String, String> attributes = parser.parse(raw);
        List<String> errors = validator.validate(attributes);

        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        String name = attributes.getOrDefault("name", "");
        String email = attributes.getOrDefault("email", "");
        String phone = attributes.getOrDefault("phone", "");
        String program = attributes.getOrDefault("program", "");

        String id = IdUtil.nextStudentId(db.count());
        StudentRecord rec = new StudentRecord(id, name, email, phone, program);

        db.save(rec);

        printer.printSuccess(rec, db.count());
    }
}
