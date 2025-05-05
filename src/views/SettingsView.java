
package views;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SettingsView extends VBox {
    
    public SettingsView() {
        setPadding(new Insets(20));
        setSpacing(10);
        
        Label title = new Label("System Settings");
        title.getStyleClass().add("section-title");
        
        Label comingSoonLabel = new Label("Settings view will be implemented in the next version.");
        
        getChildren().addAll(title, comingSoonLabel);
    }
}
