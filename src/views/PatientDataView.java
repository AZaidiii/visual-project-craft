
package views;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PatientDataView extends VBox {
    
    public PatientDataView() {
        setPadding(new Insets(20));
        setSpacing(10);
        
        Label title = new Label("Patient Data");
        title.getStyleClass().add("section-title");
        
        Label comingSoonLabel = new Label("Patient data view will be implemented in the next version.");
        
        getChildren().addAll(title, comingSoonLabel);
    }
}
