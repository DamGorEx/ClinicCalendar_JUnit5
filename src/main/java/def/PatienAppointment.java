package def;

import java.time.LocalDateTime;

public class PatienAppointment {
    private Patien patien;
    private Doctor doctor;
    private LocalDateTime dateTime;

    public PatienAppointment(Patien patien, Doctor doctorKey, LocalDateTime dateTime) {
        this.patien = patien;
        this.doctor = doctorKey;
        this.dateTime = dateTime;
    }
    public String getPatienFirstName() {
        return patien.getName();
    }

    public String getPatienLastName() {
        return patien.getSurname();
    }

    public Doctor getDoctorKey() {
        return doctor;
    }

    public void setDoctorKey(Doctor doctorKey) {
        this.doctor = doctorKey;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Doctor getDoctor() {
        return Enum.valueOf(Doctor.class, doctor.toString());
    }
}
