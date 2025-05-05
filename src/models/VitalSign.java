
package models;

import java.util.Date;

public class VitalSign {
    public enum VitalType {
        HEART_RATE, 
        BLOOD_PRESSURE, 
        OXYGEN_LEVEL, 
        TEMPERATURE
    }
    
    public enum Status {
        NORMAL, 
        WARNING, 
        CRITICAL
    }
    
    private VitalType type;
    private double value;
    private String unit;
    private Status status;
    private Date timestamp;
    
    public VitalSign(VitalType type, double value, String unit, Status status) {
        this.type = type;
        this.value = value;
        this.unit = unit;
        this.status = status;
        this.timestamp = new Date();
    }
    
    // Getters
    public VitalType getType() {
        return type;
    }
    
    public double getValue() {
        return value;
    }
    
    public String getUnit() {
        return unit;
    }
    
    public Status getStatus() {
        return status;
    }
    
    public Date getTimestamp() {
        return timestamp;
    }
    
    // Helper method to get type as display string
    public String getTypeDisplay() {
        switch (type) {
            case HEART_RATE: return "Heart Rate";
            case BLOOD_PRESSURE: return "Blood Pressure";
            case OXYGEN_LEVEL: return "Oxygen Level";
            case TEMPERATURE: return "Temperature";
            default: return "Unknown";
        }
    }
}
