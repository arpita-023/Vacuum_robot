package com.example.robot1;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.IOException;

public class ObstaclesInputController {

        @FXML
        private Stage primaryStage;
        @FXML
        private Label roomNameLabel;


        @FXML
        private Label lbloutput;

        @FXML
        private TextField obstacleNameField;

        @FXML
        private TextField obstacleDimensionField1;
        @FXML
        private TextField obstacleDimensionField2;


        private String roomName;


        public void setRoomName(String roomName) {
            this.roomName = roomName;

            roomNameLabel.setText("Room: " + roomName);
        }

        @FXML
        private void displayComment() throws IOException {
            String obstaclename = obstacleNameField.getText();
            String length = obstacleDimensionField1.getText();
            String width = obstacleDimensionField2.getText();
            try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
                MongoDatabase database = mongoClient.getDatabase("robo");
                Document userDocumnet = new Document().append("Obstacle", obstaclename);
                userDocumnet.append("Length", length);
                userDocumnet.append("Width", width);
                database.getCollection("obstacle").insertOne(userDocumnet);

                System.out.println("Obstacle, Length and Width stored in MongoDB!");


                String obstacleName = obstacleNameField.getText();
                String obstacleDimension1 = obstacleDimensionField1.getText();
                String obstacleDimension2 = obstacleDimensionField2.getText();

                // You can customize this comment based on your project requirements
                String comment = "Dusty has cleaned the room: " + roomName +
                        " excluding the obstacles. It completed its job by 15:09. The total area covered is " +
                        calculateTotalArea(obstacleDimension1, obstacleDimension2);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Obstacle Information");
                alert.setHeaderText(null);
                alert.setContentText(comment);

                // Show and wait for the user to close the dialog
                alert.showAndWait();

                // Close the stage
                Stage stage = (Stage) obstacleNameField.getScene().getWindow();
                stage.close();

            }
        }
        private String calculateTotalArea(String dimension1, String dimension2) {
            try {
                double dim1 = Double.parseDouble(dimension1);
                double dim2 = Double.parseDouble(dimension2);

                if (dim1 >= 0 && dim2 >= 0) {
                    double totalArea = Math.abs(dim1 - dim2);
                    return String.format("%.2f square units", totalArea);
                } else {
                    return "Invalid dimension input (negative values not allowed)";
                }
            } catch (NumberFormatException e) {
                return "Invalid dimension input (not a number)";
            }
        }

        public void setPrimaryStage(Stage primaryStage) {
            this.primaryStage=primaryStage;
        }
    }


