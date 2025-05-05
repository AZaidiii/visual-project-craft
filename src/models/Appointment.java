
package models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    public enum AppointmentType {
        VIDEO, CHAT
    }
    
    public enum AppointmentStatus {
        UPCOMING, COMPLETED, CANCELED
    }
    
    private String id;
    private String patientId;
    private String patientName;
    private String doctorId;
    private String doctorName;
    private LocalDate date;
    private LocalTime time;
    private AppointmentType type;
    private AppointmentStatus status;
    
    public Appointment(String id, String patientId, String patientName, 
                       String doctorId, String doctorName, LocalDate date, 
                       LocalTime time, AppointmentType type) {
        this.id = id;
        this.patientId = patientId;
        this.patientName = patientName;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.date = date;
        this.time = time;
        this.type = type;
        this.status = AppointmentStatus.UPCOMING;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public String getPatientId() {
        return patientId;
    }
    
    public String getPatientName() {
        return patientName;
    }
    
    public String getDoctorId() {
        return doctorId;
    }
    
    public String getDoctorName() {
        return doctorName;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public LocalTime getTime() {
        return time;
    }
    
    public AppointmentType getType() {
        return type;
    }
    
    public AppointmentStatus getStatus() {
        return status;
    }
    
    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}
