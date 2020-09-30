package def.notify;

import def.EmailNotifier;

import java.util.ArrayList;
import java.util.List;

public class EmailNotifierTestDouble implements EmailNotifier {
    List<Message> messageList = new ArrayList<>();
    @Override
    public void sendNotification(String email, String title, String body) {
        messageList.add(new Message(email, title, body));
    }

    public class Message {
        private String email, title, body;

        public String getEmail() {
            return email;
        }

        public String getTitle() {
            return title;
        }

        public String getBody() {
            return body;
        }

        Message (String email, String title, String body) {
            this.email = email;
            this.title = title;
            this.body = body;
        }
    }
    public ArrayList<Message> getMessageList () {
        return new ArrayList<Message>(messageList);
    }
}
