
package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import models.UserRole;
import services.UserService;

public class DashboardController {
    @FXML
    private BorderPane rootPane;
    
    @FXML
    private Label userNameLabel;
    
    @FXML
    private Label userRoleLabel;
    
    @FXML
    private VBox sidebarMenu;
    
    @FXML
    private StackPane contentArea;
    
    @FXML
    private Button logoutButton;
    
    @FXML
    private TabPane mainTabPane;
    
    private UserService userService;
    
    @FXML
    public void initialize() {
        userService = UserService.getInstance();
        setupSidebar();
    }
    
    public void initData() {
        // Set user info
        userNameLabel.setText(userService.getCurrentUsername());
        userRoleLabel.setText(userService.getCurrentUserRole().toString());
        
        // Load appropriate dashboard content based on role
        loadDashboardContent();
    }
    
    private void setupSidebar() {
        // Add menu items based on role
        UserRole role = userService.getCurrentUserRole();
        
        if (role == null) {
            return;
        }
        
        // Common menu items for all roles
        addMenuItem("Dashboard", e -> loadDashboardContent());
        
        // Role-specific menu items
        switch (role) {
            case PATIENT:
                addMenuItem("My Profile", e -> loadPatientProfile());
                addMenuItem("Upload Vitals", e -> loadVitalsUpload());
                addMenuItem("Health Trends", e -> loadHealthTrends());
                addMenuItem("Consultations", e -> loadConsultations());
                break;
                
            case DOCTOR:
                addMenuItem("My Profile", e -> loadDoctorProfile());
                addMenuItem("Patient Data", e -> loadPatientData());
                addMenuItem("Alerts", e -> loadAlerts());
                addMenuItem("Appointments", e -> loadAppointments());
                break;
                
            case ADMIN:
                addMenuItem("User Management", e -> loadUserManagement());
                addMenuItem("System Logs", e -> loadSystemLogs());
                addMenuItem("Settings", e -> loadSettings());
                break;
        }
    }
    
    private void addMenuItem(String text, javafx.event.EventHandler<javafx.event.ActionEvent> handler) {
        Button menuItem = new Button(text);
        menuItem.setPrefWidth(200);
        menuItem.getStyleClass().add("sidebar-button");
        menuItem.setOnAction(handler);
        sidebarMenu.getChildren().add(menuItem);
    }
    
    private void loadDashboardContent() {
        UserRole role = userService.getCurrentUserRole();
        if (role == null) return;
        
        Tab dashboardTab = new Tab("Dashboard");
        mainTabPane.getTabs().clear();
        mainTabPane.getTabs().add(dashboardTab);
        
        try {
            switch (role) {
                case PATIENT:
                    dashboardTab.setContent(new PatientDashboardView());
                    break;
                    
                case DOCTOR:
                    dashboardTab.setContent(new DoctorDashboardView());
                    break;
                    
                case ADMIN:
                    dashboardTab.setContent(new AdminDashboardView());
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Could not load dashboard content: " + e.getMessage());
        }
    }
    
    // Placeholder methods that would load different views
    private void loadPatientProfile() {
        openInTab("My Profile", new PatientProfileView());
    }
    
    private void loadVitalsUpload() {
        openInTab("Upload Vitals", new VitalsUploadView());
    }
    
    private void loadHealthTrends() {
        openInTab("Health Trends", new HealthTrendsView());
    }
    
    private void loadConsultations() {
        openInTab("Consultations", new ConsultationsView());
    }
    
    private void loadDoctorProfile() {
        openInTab("Doctor Profile", new DoctorProfileView());
    }
    
    private void loadPatientData() {
        openInTab("Patient Data", new PatientDataView());
    }
    
    private void loadAlerts() {
        openInTab("Alerts", new AlertsView());
    }
    
    private void loadAppointments() {
        openInTab("Appointments", new AppointmentsView());
    }
    
    private void loadUserManagement() {
        openInTab("User Management", new UserManagementView());
    }
    
    private void loadSystemLogs() {
        openInTab("System Logs", new SystemLogsView());
    }
    
    private void loadSettings() {
        openInTab("Settings", new SettingsView());
    }
    
    private void openInTab(String title, javafx.scene.Node content) {
        // Check if tab already exists
        for (Tab tab : mainTabPane.getTabs()) {
            if (tab.getText().equals(title)) {
                mainTabPane.getSelectionModel().select(tab);
                return;
            }
        }
        
        // Create new tab
        Tab tab = new Tab(title);
        tab.setContent(content);
        tab.setClosable(true);
        mainTabPane.getTabs().add(tab);
        mainTabPane.getSelectionModel().select(tab);
    }
    
    @FXML
    private void handleLogout() {
        userService.logout();
        try {
            Parent loginRoot = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
            rootPane.getScene().setRoot(loginRoot);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Could not load login screen: " + e.getMessage());
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
