
package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Patient extends User {
    private Date dateOfBirth;
    private List<String> medicalHistory;
    private String assignedDoctor;
    private List<VitalSign> vitalSigns;
    
    public Patient(String id, String username, String name) {
        super(id, username, name, UserRole.PATIENT);
        this.medicalHistory = new ArrayList<>();
        this.vitalSigns = new ArrayList<>();
    }
    
    // Getters and Setters
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public List<String> getMedicalHistory() {
        return medicalHistory;
    }
    
    public void addMedicalHistory(String condition) {
        medicalHistory.add(condition);
    }
    
    public String getAssignedDoctor() {
        return assignedDoctor;
    }
    
    public void setAssignedDoctor(String assignedDoctor) {
        this.assignedDoctor = assignedDoctor;
    }
    
    public List<VitalSign> getVitalSigns() {
        return vitalSigns;
    }
    
    public void addVitalSign(VitalSign vitalSign) {
        vitalSigns.add(vitalSign);
    }
}
