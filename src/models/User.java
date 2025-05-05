
package models;

public abstract class User {
    protected String id;
    protected String username;
    protected String name;
    protected UserRole role;
    
    public User(String id, String username, String name, UserRole role) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.role = role;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public UserRole getRole() {
        return role;
    }
}
