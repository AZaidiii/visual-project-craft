
package models;

import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
    private List<String> permissions;
    
    public Admin(String id, String username, String name) {
        super(id, username, name, UserRole.ADMIN);
        this.permissions = new ArrayList<>();
    }
    
    // Getters and Setters
    public List<String> getPermissions() {
        return permissions;
    }
    
    public void addPermission(String permission) {
        permissions.add(permission);
    }
    
    public void removePermission(String permission) {
        permissions.remove(permission);
    }
}
