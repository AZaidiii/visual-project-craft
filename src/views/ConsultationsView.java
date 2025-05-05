
package views;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ConsultationsView extends VBox {
    
    public ConsultationsView() {
        setPadding(new Insets(20));
        setSpacing(10);
        
        Label title = new Label("Consultations");
        title.getStyleClass().add("section-title");
        
        Label comingSoonLabel = new Label("Consultations interface will be implemented in the next version.");
        
        getChildren().addAll(title, comingSoonLabel);
    }
}
