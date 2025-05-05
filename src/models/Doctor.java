
package models;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends User {
    private String specialty;
    private List<String> patientIds;
    
    public Doctor(String id, String username, String name) {
        super(id, username, name, UserRole.DOCTOR);
        this.patientIds = new ArrayList<>();
    }
    
    // Getters and Setters
    public String getSpecialty() {
        return specialty;
    }
    
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
    
    public List<String> getPatientIds() {
        return patientIds;
    }
    
    public void addPatient(String patientId) {
        patientIds.add(patientId);
    }
    
    public void removePatient(String patientId) {
        patientIds.remove(patientId);
    }
}
