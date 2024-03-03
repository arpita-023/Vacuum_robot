package com.example.robot1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class bluetoothPageController implements Initializable {

    @FXML
    private ListView<String> pairedListView;

    @FXML
    private ListView<String> availableListView;

    @FXML
    private Label pairedLabel;

    @FXML
    private Label availableLabel;

    @FXML
    private Button toggleButton;

    private ObservableList<String> pairedDevices = FXCollections.observableArrayList();
    private ObservableList<String> availableDevices = FXCollections.observableArrayList();
    @FXML
    private VBox rootBox = new VBox(10); // VBox to hold all items
    private boolean isAdditionalVisible = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize components if needed
        pairedListView.setItems(pairedDevices);
        availableListView.setItems(availableDevices);

        toggleButton.setOnAction(e -> toggleAdditionalItems());

        HBox bluetoothBox = new HBox(10);
        bluetoothBox.getChildren().addAll(new Label("Bluetooth"), toggleButton);

        rootBox.getChildren().addAll(bluetoothBox);
        rootBox.setVisible(true);

        generateDevices(); // Generate initial device lists
    }


    @FXML
    private void toggleAdditionalItems() {
        System.out.println("Toggle button clicked.");

        if (!isAdditionalVisible) {
            // Display additional items after clicking the toggle button
            if (!rootBox.getChildren().contains(pairedLabel)) {
                rootBox.getChildren().addAll(pairedLabel, pairedListView, availableLabel, availableListView);
            } else {
                System.out.println("Additional items are already present.");
            }
        } else {
            // Hide additional items when toggled off
            rootBox.getChildren().removeAll(pairedLabel, pairedListView, availableLabel, availableListView);

            // Navigate to the login page
            System.out.println("Navigating to the login page...");
            navigateToLogin();
        }

        isAdditionalVisible = !isAdditionalVisible;
    }


    private void navigateToLogin() {
        try {
            // Load the login page FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            AnchorPane loginRoot = loader.load();


            // Create a new stage for the login page
            Stage loginStage = new Stage();
            loginStage.setTitle("Login");
            loginStage.setScene(new Scene(loginRoot));

            // Show the login stage
            loginStage.show();

            // Close the current Bluetooth page stage
            Stage bluetoothStage = (Stage) toggleButton.getScene().getWindow();
            bluetoothStage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void generateDevices() {
        List<String> deviceNames = Arrays.asList("opp354", "realmeA52", "SamsungS21", "iPhone13", "GooglePixel5");
        Collections.shuffle(deviceNames);
        availableDevices.addAll(deviceNames);
        pairedDevices.addAll(Arrays.asList("AB5 Robotic Cleaner", "Airdopes141", "HC-05", "Sesh Evo", "OnePlus 10T", "Galaxy Watch3 LE"));
    }

    @FXML
    private void refreshAvailableDevices() {
        // Real device names for simulation
        List<String> realDeviceNames = Arrays.asList("OnePlus9", "XiaomiRedmiNote10", "SonyXperia1", "HuaweiP50", "MotorolaEdge");

        Random random = new Random();
        int devicesToAdd = random.nextInt(5) + 1;

        Collections.shuffle(realDeviceNames);
        availableDevices.clear();
        availableDevices.addAll(realDeviceNames.subList(0, devicesToAdd));
    }
}
