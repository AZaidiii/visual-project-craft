
package views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import services.UserService;

public class AdminDashboardView extends BorderPane {
    
    public AdminDashboardView() {
        setPadding(new Insets(20));
        getStyleClass().add("dashboard-view");
        
        Label titleLabel = new Label("Administrator Dashboard");
        titleLabel.getStyleClass().add("dashboard-title");
        
        Label subtitleLabel = new Label("Welcome back, " + 
                                       UserService.getInstance().getCurrentUsername());
        subtitleLabel.getStyleClass().add("dashboard-subtitle");
        
        VBox headerBox = new VBox(10, titleLabel, subtitleLabel);
        headerBox.setPadding(new Insets(0, 0, 20, 0));
        
        // Create summary cards
        GridPane statCardsGrid = createSummaryCards();
        
        // Create tabs for different sections
        TabPane tabPane = createTabContent();
        
        // Combine all elements
        VBox content = new VBox(20, headerBox, statCardsGrid, tabPane);
        setCenter(content);
    }
    
    private GridPane createSummaryCards() {
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        
        // Create summary cards with mock data
        SummaryCard usersCard = new SummaryCard("Total Users", "124", "users");
        SummaryCard patientsCard = new SummaryCard("Active Patients", "86", "user");
        SummaryCard doctorsCard = new SummaryCard("Active Doctors", "15", "user");
        SummaryCard alertsCard = new SummaryCard("System Alerts", "2", "alert");
        
        // Add cards to grid
        grid.add(usersCard, 0, 0);
        grid.add(patientsCard, 1, 0);
        grid.add(doctorsCard, 2, 0);
        grid.add(alertsCard, 3, 0);
        
        return grid;
    }
    
    private TabPane createTabContent() {
        TabPane tabPane = new TabPane();
        tabPane.getStyleClass().add("dashboard-tabs");
        
        Tab usersTab = new Tab("User Management");
        usersTab.setClosable(false);
        usersTab.setContent(createUsersContent());
        
        Tab logsTab = new Tab("System Logs");
        logsTab.setClosable(false);
        logsTab.setContent(createLogsContent());
        
        Tab settingsTab = new Tab("System Settings");
        settingsTab.setClosable(false);
        settingsTab.setContent(new SettingsView());
        
        tabPane.getTabs().addAll(usersTab, logsTab, settingsTab);
        
        return tabPane;
    }
    
    private BorderPane createUsersContent() {
        BorderPane usersPane = new BorderPane();
        usersPane.setPadding(new Insets(20));
        
        HBox headerBox = new HBox(10);
        Label sectionTitle = new Label("Registered Users");
        sectionTitle.getStyleClass().add("section-title");
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        Button addButton = new Button("Add User");
        addButton.getStyleClass().add("add-button");
        
        headerBox.getChildren().addAll(sectionTitle, spacer, addButton);
        
        // Create table
        TableView<UserTableModel> table = new TableView<>();
        
        TableColumn<UserTableModel, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<UserTableModel, String> roleCol = new TableColumn<>("Role");
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        
        TableColumn<UserTableModel, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        TableColumn<UserTableModel, String> actionsCol = new TableColumn<>("Actions");
        actionsCol.setCellValueFactory(new PropertyValueFactory<>("actions"));
        
        table.getColumns().addAll(nameCol, roleCol, statusCol, actionsCol);
        
        // Add sample data
        ObservableList<UserTableModel> data = FXCollections.observableArrayList(
            new UserTableModel("John Smith", "Patient", "Active"),
            new UserTableModel("Dr. Sarah Johnson", "Doctor", "Active"),
            new UserTableModel("Emily Parker", "Patient", "Inactive"),
            new UserTableModel("Dr. Michael Chen", "Doctor", "Active"),
            new UserTableModel("Admin User", "Admin", "Active")
        );
        
        table.setItems(data);
        
        usersPane.setTop(headerBox);
        usersPane.setCenter(table);
        
        return usersPane;
    }
    
    private BorderPane createLogsContent() {
        BorderPane logsPane = new BorderPane();
        logsPane.setPadding(new Insets(20));
        
        Label sectionTitle = new Label("System Logs");
        sectionTitle.getStyleClass().add("section-title");
        
        // Create table
        TableView<LogTableModel> table = new TableView<>();
        
        TableColumn<LogTableModel, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        
        TableColumn<LogTableModel, String> userCol = new TableColumn<>("User");
        userCol.setCellValueFactory(new PropertyValueFactory<>("user"));
        
        TableColumn<LogTableModel, String> timestampCol = new TableColumn<>("Timestamp");
        timestampCol.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        
        TableColumn<LogTableModel, String> detailsCol = new TableColumn<>("Details");
        detailsCol.setCellValueFactory(new PropertyValueFactory<>("details"));
        detailsCol.setPrefWidth(300);
        
        table.getColumns().addAll(typeCol, userCol, timestampCol, detailsCol);
        
        // Add sample data
        ObservableList<LogTableModel> data = FXCollections.observableArrayList(
            new LogTableModel("Login", "Dr. Sarah Johnson", "2025-05-05 09:15:22", "Login successful"),
            new LogTableModel("Alert", "System", "2025-05-05 08:30:44", "Critical alert for patient Robert Garcia"),
            new LogTableModel("Upload", "John Smith", "2025-05-05 07:45:11", "CSV vitals data uploaded"),
            new LogTableModel("Consultation", "Dr. Michael Chen", "2025-05-05 07:30:05", "Video consultation completed"),
            new LogTableModel("Report", "Admin User", "2025-05-04 18:22:31", "System report generated")
        );
        
        table.setItems(data);
        
        logsPane.setTop(sectionTitle);
        logsPane.setCenter(table);
        
        return logsPane;
    }
    
    // Inner class for summary cards
    private class SummaryCard extends VBox {
        public SummaryCard(String title, String value, String icon) {
            super(10);
            setPadding(new Insets(15));
            setPrefWidth(200);
            getStyleClass().add("summary-card");
            
            HBox headerBox = new HBox(10);
            Label titleLabel = new Label(title);
            titleLabel.getStyleClass().add("summary-card-title");
            // In a real app, add an icon here
            headerBox.getChildren().add(titleLabel);
            
            Label valueLabel = new Label(value);
            valueLabel.getStyleClass().add("summary-card-value");
            
            getChildren().addAll(headerBox, valueLabel);
        }
    }
    
    // Inner class for user table data model
    public static class UserTableModel {
        private String name;
        private String role;
        private String status;
        private String actions; // Not actually used, just for the column
        
        public UserTableModel(String name, String role, String status) {
            this.name = name;
            this.role = role;
            this.status = status;
            this.actions = ""; // In a real app, we'd use a custom cell factory for buttons
        }
        
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
        
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        
        public String getActions() { return actions; }
        public void setActions(String actions) { this.actions = actions; }
    }
    
    // Inner class for log table data model
    public static class LogTableModel {
        private String type;
        private String user;
        private String timestamp;
        private String details;
        
        public LogTableModel(String type, String user, String timestamp, String details) {
            this.type = type;
            this.user = user;
            this.timestamp = timestamp;
            this.details = details;
        }
        
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        
        public String getUser() { return user; }
        public void setUser(String user) { this.user = user; }
        
        public String getTimestamp() { return timestamp; }
        public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
        
        public String getDetails() { return details; }
        public void setDetails(String details) { this.details = details; }
    }
}
