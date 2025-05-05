
package views;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class UserManagementView extends VBox {
    
    public UserManagementView() {
        setPadding(new Insets(20));
        setSpacing(10);
        
        Label title = new Label("User Management");
        title.getStyleClass().add("section-title");
        
        Label comingSoonLabel = new Label("User management interface will be implemented in the next version.");
        
        getChildren().addAll(title, comingSoonLabel);
    }
}
