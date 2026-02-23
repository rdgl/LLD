import java.util.Optional;

public class CreditsRule implements EligibilityRule {
    @Override
    public Optional<String> check(StudentProfile student) {
        if (student.earnedCredits < 20) {
            return Optional.of("credits below 20");
        }
        return Optional.empty();
    }
}
