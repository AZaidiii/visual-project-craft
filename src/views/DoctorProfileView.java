
package views;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DoctorProfileView extends VBox {
    
    public DoctorProfileView() {
        setPadding(new Insets(20));
        setSpacing(10);
        
        Label title = new Label("Doctor Profile");
        title.getStyleClass().add("section-title");
        
        Label comingSoonLabel = new Label("Doctor profile will be implemented in the next version.");
        
        getChildren().addAll(title, comingSoonLabel);
    }
}
