
package views;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PatientProfileView extends VBox {
    
    public PatientProfileView() {
        setPadding(new Insets(20));
        setSpacing(10);
        
        Label title = new Label("Patient Profile");
        title.getStyleClass().add("section-title");
        
        Label comingSoonLabel = new Label("Patient profile will be implemented in the next version.");
        
        getChildren().addAll(title, comingSoonLabel);
    }
}
