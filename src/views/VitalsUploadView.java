
package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;

import java.io.File;

public class VitalsUploadView extends BorderPane {
    
    private Label fileNameLabel;
    private Button uploadButton;
    
    public VitalsUploadView() {
        setPadding(new Insets(20));
        getStyleClass().add("vitals-upload-view");
        
        VBox content = new VBox(20);
        content.setAlignment(Pos.CENTER);
        content.setMaxWidth(500);
        
        Label titleLabel = new Label("Upload Vitals Data");
        titleLabel.getStyleClass().add("section-title");
        
        // File selector area
        VBox fileBox = createFileSelector();
        
        // Info text
        Label infoLabel = new Label("Upload CSV files with heart rate, oxygen level, temperature, and blood pressure data");
        infoLabel.getStyleClass().add("info-text");
        infoLabel.setWrapText(true);
        
        // Upload button
        uploadButton = new Button("Upload Vitals");
        uploadButton.getStyleClass().add("upload-button");
        uploadButton.setDisable(true);
        uploadButton.setMaxWidth(Double.MAX_VALUE);
        uploadButton.setOnAction(e -> handleUpload());
        
        content.getChildren().addAll(titleLabel, fileBox, infoLabel, uploadButton);
        
        setCenter(content);
    }
    
    private VBox createFileSelector() {
        VBox box = new VBox(15);
        box.setAlignment(Pos.CENTER);
        box.getStyleClass().add("file-selector");
        box.setPadding(new Insets(30));
        
        // File icon/image would go here in a real app
        Label uploadIconLabel = new Label("CSV");
        uploadIconLabel.getStyleClass().add("upload-icon");
        
        // File selection prompt
        Label promptLabel = new Label("Click to browse or drag & drop CSV file here");
        promptLabel.getStyleClass().add("file-prompt");
        
        // File name display
        fileNameLabel = new Label("No file selected");
        fileNameLabel.getStyleClass().add("file-name");
        
        // Browse button
        Button browseButton = new Button("Browse Files");
        browseButton.setOnAction(e -> browseForFile());
        
        box.getChildren().addAll(uploadIconLabel, promptLabel, fileNameLabel, browseButton);
        
        return box;
    }
    
    private void browseForFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select CSV File");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("CSV Files", "*.csv")
        );
        
        File file = fileChooser.showOpenDialog(getScene().getWindow());
        
        if (file != null) {
            fileNameLabel.setText(file.getName());
            uploadButton.setDisable(false);
        }
    }
    
    private void handleUpload() {
        // Show uploading state
        uploadButton.setDisable(true);
        uploadButton.setText("Uploading...");
        
        // Simulate upload delay
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                
                // Update UI on JavaFX thread
                javafx.application.Platform.runLater(() -> {
                    showUploadSuccess();
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
    
    private void showUploadSuccess() {
        uploadButton.setText("Upload Complete");
        uploadButton.getStyleClass().add("upload-success");
        
        // Create alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Upload Successful");
        alert.setHeaderText(null);
        alert.setContentText("Your vitals data has been successfully uploaded and processed.");
        alert.showAndWait();
        
        // Reset state after a delay
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                
                javafx.application.Platform.runLater(() -> {
                    uploadButton.setText("Upload Vitals");
                    uploadButton.getStyleClass().remove("upload-success");
                    fileNameLabel.setText("No file selected");
                    uploadButton.setDisable(true);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
