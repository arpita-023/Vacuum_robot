package com.example.robot1;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
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

public class LoginController {

    @FXML
    private PasswordField PASSWORD;
    @FXML
    private TextField USER_NAME;

    @FXML
    private Button login_button;
    @FXML
    private Button register_button;
    private Stage primaryStage;

    public void login_funtion(ActionEvent event)throws IOException{
        String username=USER_NAME.getText();
        String password =PASSWORD.getText();
        try(MongoClient mongoClient=MongoClients.create("mongodb://localhost:27017")){
            MongoDatabase database=mongoClient.getDatabase("robo");
            Document userDocumnet=new Document().append("USER_NAME",username);
            userDocumnet.append("PASSWORD",password);
            database.getCollection("login").insertOne(userDocumnet);

            System.out.println("Username and password stored in MongoDB!");

            Stage stage = (Stage) login_button.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("roomLayout2.fxml"));
            stage = new Stage();
            stage.setScene(new Scene((Parent) loader.load(), 536.0, 596.0));
            stage.show();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void register_function() throws IOException {
        System.out.println("hi");
        Stage stage = (Stage) register_button.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("roomLayout.fxml"));
        primaryStage.setTitle("registration");
        primaryStage.setScene(new Scene(root, 536, 596));
        primaryStage.show();

    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage=primaryStage;
    }
}


