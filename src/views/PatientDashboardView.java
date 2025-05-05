
package views;

import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import services.UserService;

public class PatientDashboardView extends BorderPane {
    
    public PatientDashboardView() {
        setPadding(new Insets(20));
        getStyleClass().add("dashboard-view");
        
        Label titleLabel = new Label("Patient Dashboard");
        titleLabel.getStyleClass().add("dashboard-title");
        
        Label subtitleLabel = new Label("Welcome back, " + 
                                       UserService.getInstance().getCurrentUsername());
        subtitleLabel.getStyleClass().add("dashboard-subtitle");
        
        VBox headerBox = new VBox(10, titleLabel, subtitleLabel);
        headerBox.setPadding(new Insets(0, 0, 20, 0));
        
        // Create vital stats cards
        GridPane statCardsGrid = createVitalStatsCards();
        
        // Create tabs for different sections
        TabPane tabPane = createTabContent();
        
        // Combine all elements
        VBox content = new VBox(20, headerBox, statCardsGrid, tabPane);
        setCenter(content);
    }
    
    private GridPane createVitalStatsCards() {
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        
        // Create vital stats cards with mock data
        VitalStatCard heartRateCard = new VitalStatCard("Heart Rate", "78 bpm", true);
        VitalStatCard bloodPressureCard = new VitalStatCard("Blood Pressure", "120/80 mmHg", false);
        VitalStatCard oxygenCard = new VitalStatCard("Oxygen Level", "98%", true);
        VitalStatCard temperatureCard = new VitalStatCard("Temperature", "36.8°C", false);
        
        // Add cards to grid
        grid.add(heartRateCard, 0, 0);
        grid.add(bloodPressureCard, 1, 0);
        grid.add(oxygenCard, 2, 0);
        grid.add(temperatureCard, 3, 0);
        
        return grid;
    }
    
    private TabPane createTabContent() {
        TabPane tabPane = new TabPane();
        tabPane.getStyleClass().add("dashboard-tabs");
        
        Tab healthTrendsTab = new Tab("Health Trends");
        healthTrendsTab.setClosable(false);
        healthTrendsTab.setContent(createHealthTrendsContent());
        
        Tab appointmentsTab = new Tab("Appointments");
        appointmentsTab.setClosable(false);
        appointmentsTab.setContent(new AppointmentsView());
        
        Tab uploadTab = new Tab("Upload Data");
        uploadTab.setClosable(false);
        uploadTab.setContent(new VitalsUploadView());
        
        tabPane.getTabs().addAll(healthTrendsTab, appointmentsTab, uploadTab);
        
        return tabPane;
    }
    
    private BorderPane createHealthTrendsContent() {
        BorderPane trendsPane = new BorderPane();
        trendsPane.setPadding(new Insets(20));
        
        // Create chart for heart rate
        final NumberAxis xAxis = new NumberAxis(1, 7, 1);
        final NumberAxis yAxis = new NumberAxis(50, 120, 10);
        xAxis.setLabel("Day");
        yAxis.setLabel("BPM");
        
        final LineChart<Number,Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Heart Rate Trend (Last 7 Days)");
        
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Heart Rate");
        
        // Add mock data
        series.getData().add(new XYChart.Data<>(1, 78));
        series.getData().add(new XYChart.Data<>(2, 82));
        series.getData().add(new XYChart.Data<>(3, 76));
        series.getData().add(new XYChart.Data<>(4, 84));
        series.getData().add(new XYChart.Data<>(5, 90));
        series.getData().add(new XYChart.Data<>(6, 88));
        series.getData().add(new XYChart.Data<>(7, 76));
        
        lineChart.getData().add(series);
        trendsPane.setCenter(lineChart);
        
        return trendsPane;
    }
    
    // Inner class for vital stats cards
    private class VitalStatCard extends VBox {
        public VitalStatCard(String title, String value, boolean positive) {
            super(10);
            setPadding(new Insets(15));
            setPrefWidth(200);
            getStyleClass().add("vital-card");
            
            Label titleLabel = new Label(title);
            titleLabel.getStyleClass().add("vital-card-title");
            
            Label valueLabel = new Label(value);
            valueLabel.getStyleClass().add("vital-card-value");
            
            if (positive) {
                getStyleClass().add("vital-card-positive");
            }
            
            getChildren().addAll(titleLabel, valueLabel);
        }
    }
}
