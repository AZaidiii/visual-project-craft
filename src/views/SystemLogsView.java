
package views;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SystemLogsView extends VBox {
    
    public SystemLogsView() {
        setPadding(new Insets(20));
        setSpacing(10);
        
        Label title = new Label("System Logs");
        title.getStyleClass().add("section-title");
        
        Label comingSoonLabel = new Label("System logs interface will be implemented in the next version.");
        
        getChildren().addAll(title, comingSoonLabel);
    }
}
