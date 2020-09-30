package def;

import def.PatienAppointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

public class ClinicCalendar {
    private List<PatienAppointment> patienAppointmentList;
    private LocalDate today;

    public ClinicCalendar() {
        patienAppointmentList = new ArrayList<PatienAppointment>();
    }

    public ClinicCalendar(LocalDate now) {
        this();
        setToday(now);
    }

    void addAppointment(String patienFirstName, String patienLastName, int height, int weigth, String doctorKey, String dateTime) {
        Doctor doc = Doctor.valueOf(doctorKey);
        LocalDateTime localDateTime;
        localDateTime = DateTimeConverter.convertToDateTimeFromString(dateTime, getToday());
        Patien p = Patien.of(patienFirstName, patienLastName, height, weigth);
        PatienAppointment patienAppointment = new PatienAppointment(p, doc, localDateTime);
        patienAppointmentList.add(patienAppointment);
    }



    public List<PatienAppointment> getAppointments () {
        return patienAppointmentList;
    }
    public List<PatienAppointment> getTodayAppointment () {
        return patienAppointmentList.stream()
                .filter(app ->
                        app.getDateTime().toLocalDate().isEqual(LocalDate.now())
                )
                .collect(Collectors.toList());
    }

    public LocalDate getToday() {
        return Objects.isNull(this.today) ? LocalDate.now() : this.today;
    }

    public void setToday(LocalDate today) {
        Objects.requireNonNull(today);
        this.today = today;
    }

    public boolean hasAppointment (LocalDate date) {
        return patienAppointmentList.stream()
                                                .anyMatch(patienAppointment ->
                                                        patienAppointment.getDateTime().toLocalDate().isEqual(date));
    }


    public List<PatienAppointment>  getTomorrowAppointments() {
        return patienAppointmentList.stream()
                .filter(app ->
                        app.getDateTime().toLocalDate().isEqual(LocalDate.now().plusDays(1))
                )
                .collect(Collectors.toList());
    }
}
