public class EmailSender extends NotificationSender {
    public EmailSender(AuditLog audit) { super(audit); }

    @Override
    protected void validate(Notification n) {
        // Basic validation for email channel
    }

    @Override
    protected void doSend(Notification n) {
        // Handle the channel's body limit as a known constraint
        String body = n.body;
        if (body.length() > 40) body = body.substring(0, 40);
        System.out.println("EMAIL -> to=" + n.email + " subject=" + n.subject + " body=" + body);
    }

    @Override
    protected String getAuditMessage() {
        return "email sent";
    }
}
