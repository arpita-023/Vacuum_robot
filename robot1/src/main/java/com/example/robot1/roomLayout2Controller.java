package com.example.robot1;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.stage.Stage;
import org.bson.Document;

public class roomLayout2Controller {
        @FXML
        private TextField roomNameField;
        @FXML
        private TextField lengthNamefield;
        @FXML
        private TextField widthNamefield;
        @FXML
        private Button next_button;

        private Stage primaryStage;

        @FXML
        private void showDimensionsInput() throws IOException {
            String roomname = roomNameField.getText();
            String length = lengthNamefield.getText();
            String width = widthNamefield.getText();
            try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
                MongoDatabase database = mongoClient.getDatabase("robo");
                Document userDocumnet = new Document().append("Room", roomname);
                userDocumnet.append("Length", length);
                userDocumnet.append("Width", width);
                database.getCollection("roomlayout").insertOne(userDocumnet);

                System.out.println("Room, Length and Width stored in MongoDB!");
                Stage stage = (Stage) next_button.getScene().getWindow();
                stage.close();
                primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ObstacleInput.fxml"));
                Parent root = loader.load();

                // Pass the room name to the DimensionsInputController
                ObstaclesInputController controller = loader.getController();
                controller.setRoomName(roomNameField.getText());

                // Set up the new stage
                Stage dimensionsStage = new Stage();
                dimensionsStage.setTitle("Enter Dimensions");
                dimensionsStage.setScene(new Scene(root, 536, 596));
                dimensionsStage.show();
                if (controller != null) {
                    // Pass the primary stage to the directionalPageController
                    controller.setPrimaryStage(primaryStage);
                } else {
                    System.err.println("Controller is null. Check your FXML file.");
                }

                // Close the current stage
                Stage currentStage = (Stage) roomNameField.getScene().getWindow();
                currentStage.close();

        }


    }

    public void setPrimaryStage(Stage primaryStage) {
            this.primaryStage=primaryStage;
    }
}


