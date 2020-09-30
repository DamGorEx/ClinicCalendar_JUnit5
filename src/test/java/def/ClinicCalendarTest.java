package def;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClinicCalendarTest {
    ClinicCalendar clinicCalendar;
    @BeforeEach
    void init() {
        clinicCalendar = new ClinicCalendar(LocalDate.now());
    }

    @Test
    //@Disabled
    public void allowEntryOfAppointment() {
        clinicCalendar.addAppointment(  "Jim", "Wee", 98, 176,
                                        "avery",
                                        "09/01/2020 2:00 pm");
        List<PatienAppointment> patienAppointmentList = clinicCalendar.getAppointments();
        assertNotNull(patienAppointmentList);
        assertEquals(1, patienAppointmentList.size());
        PatienAppointment patienAppointment = patienAppointmentList.get(0);
        //check all despite of test failure in first one
        assertAll(  () -> assertEquals("Jim", patienAppointment.getPatienFirstName()),
                    () -> assertEquals("Wee", patienAppointment.getPatienLastName()),
                    () -> assertEquals(Doctor.avery, patienAppointment.getDoctorKey()));

        String expected = "9/1/2020 2:00 PM";
        assertEquals(expected,  patienAppointment
                .getDateTime()
                .format(DateTimeFormatter.ofPattern("M/d/yyyy h:mm a")), "Wrong format of value: " + expected);
    }

    @Test
    void getTodayAppointment() {
        clinicCalendar.addAppointment("aa","bb",1,2, "smith",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("M/d/yyyy h:mm a")));
        assertEquals(1, clinicCalendar.getTodayAppointment().size());

    }

    @Test
    void hasAppointment() {
        clinicCalendar.addAppointment("aa","bb",1,2, "smith",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("M/d/yyyy h:mm a")));
        assertTrue(clinicCalendar.hasAppointment(LocalDate.now()));
        assertFalse(clinicCalendar.hasAppointment(LocalDate.now().minusDays(1)));
    }

    @Test
    @DisplayName("has appointment for tomorrow")
    void getTomorrowAppointmentsTest() {
        clinicCalendar.addAppointment("aa","bb",1,2, "smith",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("M/d/yyyy h:mm a")));
        clinicCalendar.addAppointment("aa","bb",1,2, "smith",
                LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ofPattern("M/d/yyyy h:mm a")));
        assertEquals(1, clinicCalendar.getTomorrowAppointments().size());
    }

    @Test
    void returnCurrentDateAppointment() {
        clinicCalendar.addAppointment("aa","bb",1,2, "smith",
                "today 2:00 pm");
        clinicCalendar.addAppointment("aa","bb",1,2, "smith",
                "today 3:00 pm");
        clinicCalendar.addAppointment("aa","bb",1,2, "smith",
                "09/01/2020 2:00 pm");
        assertEquals(2, clinicCalendar.getTodayAppointment().size());
    }
}