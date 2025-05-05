
package views;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import services.UserService;

public class DoctorDashboardView extends BorderPane {
    
    public DoctorDashboardView() {
        setPadding(new Insets(20));
        getStyleClass().add("dashboard-view");
        
        Label titleLabel = new Label("Doctor Dashboard");
        titleLabel.getStyleClass().add("dashboard-title");
        
        Label subtitleLabel = new Label("Welcome back, Dr. " + 
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
        SummaryCard patientsCard = new SummaryCard("Total Patients", "42", "users");
        SummaryCard alertsCard = new SummaryCard("Active Alerts", "3", "alert");
        SummaryCard appointmentsCard = new SummaryCard("Today's Appointments", "8", "calendar");
        SummaryCard monitorsCard = new SummaryCard("Monitored Vitals", "168", "chart");
        
        // Add cards to grid
        grid.add(patientsCard, 0, 0);
        grid.add(alertsCard, 1, 0);
        grid.add(appointmentsCard, 2, 0);
        grid.add(monitorsCard, 3, 0);
        
        return grid;
    }
    
    private TabPane createTabContent() {
        TabPane tabPane = new TabPane();
        tabPane.getStyleClass().add("dashboard-tabs");
        
        Tab patientsTab = new Tab("Patients");
        patientsTab.setClosable(false);
        patientsTab.setContent(createPatientsContent());
        
        Tab alertsTab = new Tab("Alerts");
        alertsTab.setClosable(false);
        alertsTab.setContent(new AlertsView());
        
        Tab appointmentsTab = new Tab("Appointments");
        appointmentsTab.setClosable(false);
        appointmentsTab.setContent(new AppointmentsView());
        
        tabPane.getTabs().addAll(patientsTab, alertsTab, appointmentsTab);
        
        return tabPane;
    }
    
    private ScrollPane createPatientsContent() {
        VBox container = new VBox(20);
        container.setPadding(new Insets(20));
        
        // Create patient cards
        PatientCard patient1 = new PatientCard(
            "John Smith", 
            new String[]{"Heart Rate: 78 bpm", "Blood Pressure: 120/80 mmHg", 
                         "Oxygen Level: 98%", "Temperature: 36.8°C"},
            "normal"
        );
        
        PatientCard patient2 = new PatientCard(
            "Emily Johnson", 
            new String[]{"Heart Rate: 95 bpm", "Blood Pressure: 145/95 mmHg", 
                         "Oxygen Level: 94%", "Temperature: 37.8°C"},
            "warning"
        );
        
        PatientCard patient3 = new PatientCard(
            "Robert Garcia", 
            new String[]{"Heart Rate: 115 bpm", "Blood Pressure: 165/100 mmHg", 
                         "Oxygen Level: 89%", "Temperature: 39.2°C"},
            "critical"
        );
        
        container.getChildren().addAll(patient1, patient2, patient3);
        
        ScrollPane scrollPane = new ScrollPane(container);
        scrollPane.setFitToWidth(true);
        return scrollPane;
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
    
    // Inner class for patient cards
    private class PatientCard extends VBox {
        public PatientCard(String name, String[] vitals, String status) {
            super(15);
            setPadding(new Insets(15));
            getStyleClass().addAll("patient-card", "patient-card-" + status);
            
            Label nameLabel = new Label(name);
            nameLabel.getStyleClass().add("patient-card-name");
            
            GridPane vitalsGrid = new GridPane();
            vitalsGrid.setHgap(15);
            vitalsGrid.setVgap(10);
            
            for (int i = 0; i < vitals.length; i++) {
                Label vitalLabel = new Label(vitals[i]);
                vitalsGrid.add(vitalLabel, i % 2, i / 2);
            }
            
            Button viewButton = new Button("View Details");
            viewButton.getStyleClass().add("view-details-button");
            
            getChildren().addAll(nameLabel, vitalsGrid, viewButton);
        }
    }
}
