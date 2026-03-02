import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

/**
 * Starter demo that shows why mutability is risky.
 *
 * After refactor:
 * - direct mutation should not compile (no setters)
 * - external modifications to tags should not affect the ticket
 * - service "updates" should return a NEW ticket instance
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        // 1. Creation
        IncidentTicket t = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Initial Ticket:   " + t);

        // 2. Service "Updates" (returns new instances)
        IncidentTicket assigned = service.assign(t, "agent@example.com");
        System.out.println("Assigned Ticket:  " + assigned);

        IncidentTicket escalated = service.escalateToCritical(assigned);
        System.out.println("Escalated Ticket: " + escalated);

        // Verify original ticket 't' hasn't changed
        System.out.println("\nOriginal ticket after 'updates': " + t);

        // 3. Attempting to modify tags via list leak (should fail or have no effect)
        try {
            List<String> tags = escalated.getTags();
            tags.add("HACKED_FROM_OUTSIDE");
            System.out.println("\nAfter external tag mutation (Hacked?): " + escalated);
        } catch (UnsupportedOperationException e) {
            System.out.println("\nSuccessfully blocked external tag mutation: " + e.getMessage());
        }

        // 4. Validation Example
        try {
            IncidentTicket.builder()
                .id("INVALID_ID_TOO_LONG_1234567890")
                .reporterEmail("not-an-email")
                .title("") // empty
                .build();
        } catch (IllegalArgumentException e) {
            System.out.println("\nValidation worked! Caught error: " + e.getMessage());
        }
    }
}
