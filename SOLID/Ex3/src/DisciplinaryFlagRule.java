import java.util.Optional;

public class DisciplinaryFlagRule implements EligibilityRule {
    @Override
    public Optional<String> check(StudentProfile student) {
        if (student.disciplinaryFlag != LegacyFlags.NONE) {
            return Optional.of("disciplinary flag present");
        }
        return Optional.empty();
    }
}
