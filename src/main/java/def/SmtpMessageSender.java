package def;

public class SmtpMessageSender implements EmailNotifier
{
    @Override
    public void sendNotification(String email, String title, String body) {
        // Real smtp service sends emails .. to do
    }

}
