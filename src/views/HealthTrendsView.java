
package views;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HealthTrendsView extends VBox {
    
    public HealthTrendsView() {
        setPadding(new Insets(20));
        setSpacing(10);
        
        Label title = new Label("Health Trends");
        title.getStyleClass().add("section-title");
        
        Label comingSoonLabel = new Label("Health trends visualization will be implemented in the next version.");
        
        getChildren().addAll(title, comingSoonLabel);
    }
}
