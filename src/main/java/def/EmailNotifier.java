package def;

public interface EmailNotifier {
    void sendNotification(String email, String title, String body);
}
