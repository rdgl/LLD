import java.util.Optional;

public class CgrThresholdRule implements EligibilityRule {
    @Override
    public Optional<String> check(StudentProfile student) {
        if (student.cgr < 8.0) {
            return Optional.of("CGR below 8.0");
        }
        return Optional.empty();
    }
}
