
package views;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AlertsView extends VBox {
    
    public AlertsView() {
        setPadding(new Insets(20));
        setSpacing(10);
        
        Label title = new Label("Emergency Alerts");
        title.getStyleClass().add("section-title");
        
        Label comingSoonLabel = new Label("Alerts view will be implemented in the next version.");
        
        getChildren().addAll(title, comingSoonLabel);
    }
}
