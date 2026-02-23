import java.util.*;

public class EligibilityEngine {
    private final EligibilityStore store;
    private final List<EligibilityRule> rules;

    public EligibilityEngine(EligibilityStore store, List<EligibilityRule> rules) {
        this.store = store;
        this.rules = rules;
    }

    public void runAndPrint(StudentProfile s) {
        ReportPrinter p = new ReportPrinter();
        EligibilityEngineResult r = evaluate(s);
        p.print(s, r);
        store.save(s.rollNo, r.status);
    }

    public EligibilityEngineResult evaluate(StudentProfile s) {
        for (EligibilityRule rule : rules) {
            Optional<String> failureReason = rule.check(s);
            if (failureReason.isPresent()) {
                return new EligibilityEngineResult("NOT_ELIGIBLE", List.of(failureReason.get()));
            }
        }
        return new EligibilityEngineResult("ELIGIBLE", Collections.emptyList());
    }
}
