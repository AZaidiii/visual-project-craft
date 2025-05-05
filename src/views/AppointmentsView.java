
package views;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AppointmentsView extends VBox {
    
    public AppointmentsView() {
        setPadding(new Insets(20));
        setSpacing(10);
        
        Label title = new Label("Upcoming Appointments");
        title.getStyleClass().add("section-title");
        
        Label comingSoonLabel = new Label("Appointments view will be implemented in the next version.");
        
        getChildren().addAll(title, comingSoonLabel);
    }
}
