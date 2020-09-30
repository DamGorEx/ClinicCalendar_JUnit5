package def;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.function.Consumer;

public class TomorrowAppointmentNotifier {
    private ClinicCalendar calendar;
    private EmailNotifier sender;
    public TomorrowAppointmentNotifier(ClinicCalendar calendar,EmailNotifier sender) {
        this.calendar = calendar;
        this.sender = sender;
    }

    void run() {
        calendar.getTomorrowAppointments().forEach(app -> {
            String email = app.getPatienFirstName() + app.getPatienLastName() + "@email.com";
            String title = "Appointment Reminder";
            String msgBody = generateBody(app);
            sender.sendNotification(email, title, msgBody);
        });
    }

    private String generateBody(PatienAppointment app) {
        return "You have appointment tomorrow at "
                + app.getDateTime().format(DateTimeFormatter.ofPattern("h:mm a", Locale.GERMAN))
                + " with dr. " + app.getDoctor().getName();
    }

}
