package def;

import def.notify.EmailNotifierTestDouble;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Appointment notified should ")
class TomorrowAppointmentNotifierTest {
    ClinicCalendar calendar;
    TomorrowAppointmentNotifier tomorrowAppointmentNotifier;
    ArrayList<EmailNotifierTestDouble.Message> messeges;
    LocalDateTime appointTimeForTmr;
    @BeforeEach
    void init() {
        appointTimeForTmr = LocalDateTime.now().plusDays(1);
        calendar = new ClinicCalendar(LocalDate.now());
        calendar.addAppointment("aa","bb",1,2, "smith",
                appointTimeForTmr.format(DateTimeFormatter.ofPattern("M/d/yyyy h:mm a")));
        calendar.addAppointment("aa","bb",1,2, "smith",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("M/d/yyyy h:mm a")));
        EmailNotifierTestDouble emailNotifierTestDouble = new EmailNotifierTestDouble();
        tomorrowAppointmentNotifier = new TomorrowAppointmentNotifier(calendar, emailNotifierTestDouble);
        tomorrowAppointmentNotifier.run();
        messeges = emailNotifierTestDouble.getMessageList();
    }
    @Test
    @DisplayName("send msg only for appointments for tomorrow")
    void sendAppointmentsForTomorrow() {
        assertEquals(1, messeges.size());
    }
    @Test
    @DisplayName("send msg to correct email with correct title and body")
    void sendAppointmentsForTomorrowEmail() {
        LocalDateTime appointTimeForTmr = LocalDateTime.now().plusDays(1);
        String dateToCompInTheBody = appointTimeForTmr.format(DateTimeFormatter.ofPattern("h:mm a"));
        String email = messeges.get(0).getEmail();
        String title = messeges.get(0).getTitle();
        String msgBody = messeges.get(0).getBody();

        assertAll(  () -> assertEquals("aabb@email.com", email),
                    () -> assertEquals("Appointment Reminder", title),
                    () -> assertEquals("You have appointment tomorrow at " + dateToCompInTheBody +
                                                " with dr. Carol Smith", msgBody)
        );
    }

}