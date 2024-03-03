package com.example.robot1;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;

public class roomLayoutController {
    private Stage primaryStage;

    @FXML
    private Button add_button;
    @FXML
    private TextField user;
    @FXML
    private TextField emailid;
    @FXML
    private PasswordField pass;

    @FXML
    private void room_view() throws IOException {
        String username = user.getText();
        String password = pass.getText();
        String email = emailid.getText();
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("robo");
            Document userDocumnet = new Document().append("user", username);
            userDocumnet.append("password", password);
            userDocumnet.append("email", email);
            database.getCollection("register").insertOne(userDocumnet);

            System.out.println("Username, email and password stored in MongoDB!");
            Stage stage = (Stage) add_button.getScene().getWindow();
            stage.close();
            primaryStage = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("roomLayout2.fxml"));
            Parent root = loader.load();

            // Get the controller after loading the FXML
            roomLayout2Controller controller = loader.getController();

            if (controller != null) {
                // Pass the primary stage to the directionalPageController
                controller.setPrimaryStage(primaryStage);
            } else {
                System.err.println("Controller is null. Check your FXML file.");
            }

            primaryStage.setTitle("Home Page");
            primaryStage.setScene(new Scene(root, 536, 596));
            primaryStage.show();
        }
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}

