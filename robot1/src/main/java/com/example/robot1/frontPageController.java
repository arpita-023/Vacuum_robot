package com.example.robot1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class frontPageController {
    private Stage primaryStage;

    @FXML
    private Button On_button;



    @FXML
    private void navigateToBluetooth() throws IOException {
        Stage stage = (Stage) On_button.getScene().getWindow();
        stage.close();

        // Load the BluetoothDevice FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("bluetoothPage.fxml"));
        Parent root = loader.load();

        // Create a new stage for the BluetoothDevice
        Stage bluetoothStage = new Stage();
        bluetoothStage.setTitle("Bluetooth Device");
        bluetoothStage.setScene(new Scene(root));

        // Get the controller after loading the FXML
        bluetoothPageController bluetoothController = loader.getController();

        if (bluetoothController != null) {
            // Initialize the BluetoothDeviceController if needed
            bluetoothController.initialize(null, null);
        } else {
            System.err.println("BluetoothController is null. Check your FXML file.");
        }

        // Show the BluetoothDevice stage
        bluetoothStage.show();
    }


    @FXML
    private void feedback_view() {
        // Prompt the user for feedback
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Feedback");
        dialog.setHeaderText("Please provide your feedback:");
        dialog.setContentText("Feedback:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(feedback -> {
            System.out.println("Feedback received: " + feedback);
            // Implement your logic to handle the feedback
            showConfirmationDialog("Feedback Received", "Thank you for your feedback:\n" + feedback);
        });
    }

    private void showConfirmationDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
