
package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import services.UserService;
import models.UserRole;

public class LoginController {
    @FXML
    private TextField usernameField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private ComboBox<String> roleComboBox;
    
    @FXML
    private Button loginButton;
    
    @FXML
    public void initialize() {
        roleComboBox.getItems().addAll("Patient", "Doctor", "Admin");
        roleComboBox.setValue("Patient");
    }
    
    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String selectedRole = roleComboBox.getValue().toUpperCase();
        
        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Login Error", "Please enter both username and password.");
            return;
        }
        
        // In a real app, validate credentials against a database
        boolean loginSuccess = UserService.getInstance().login(username.toLowerCase(), password);
        
        if (loginSuccess) {
            try {
                // Load the dashboard based on role
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Dashboard.fxml"));
                Parent dashboardRoot = loader.load();
                
                // Set the controller data
                DashboardController dashboardController = loader.getController();
                dashboardController.initData();
                
                // Get stage and set new scene
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.setScene(new Scene(dashboardRoot, 1000, 700));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error", "Could not load the dashboard: " + e.getMessage());
            }
        } else {
            showAlert("Login Failed", "Invalid username or password.");
        }
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
