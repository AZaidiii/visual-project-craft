
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RPMSApplication extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/Login.fxml"));
        primaryStage.setTitle("Remote Patient Monitoring System");
        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
