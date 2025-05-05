
package services;

import models.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserService {
    private static UserService instance;
    private Map<String, User> users;
    private User currentUser;
    
    private UserService() {
        users = new HashMap<>();
        initializeDemoUsers();
    }
    
    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }
    
    private void initializeDemoUsers() {
        // Create demo patient
        Patient patient = new Patient(UUID.randomUUID().toString(), "patient", "John Smith");
        users.put(patient.getUsername(), patient);
        
        // Create demo doctor
        Doctor doctor = new Doctor(UUID.randomUUID().toString(), "doctor", "Dr. Sarah Johnson");
        doctor.setSpecialty("Cardiology");
        users.put(doctor.getUsername(), doctor);
        
        // Create demo admin
        Admin admin = new Admin(UUID.randomUUID().toString(), "admin", "Admin User");
        users.put(admin.getUsername(), admin);
    }
    
    public boolean login(String username, String password) {
        // For demo purposes, any password works
        User user = users.get(username);
        if (user != null) {
            currentUser = user;
            return true;
        }
        return false;
    }
    
    public void logout() {
        currentUser = null;
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    public UserRole getCurrentUserRole() {
        return currentUser != null ? currentUser.getRole() : null;
    }
    
    public String getCurrentUsername() {
        return currentUser != null ? currentUser.getUsername() : "";
    }
}
