public class SmsSender extends NotificationSender {
    public SmsSender(AuditLog audit) { super(audit); }

    @Override
    protected void validate(Notification n) {
        // SMS specific validation
    }

    @Override
    protected void doSend(Notification n) {
        // Explicitly only using parts of Notification relevant to SMS
        System.out.println("SMS -> to=" + n.phone + " body=" + n.body);
    }

    @Override
    protected String getAuditMessage() {
        return "sms sent";
    }
}
