/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author USER
 */
public class elbaldiBackoff extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {

            Image icon = new Image(getClass().getResourceAsStream("/Ressources/LogoXL.png"));

            primaryStage.getIcons().add(icon);

             //Parent root = FXMLLoader.load(getClass().getResource("templateBack.fxml"));
      // Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
             Parent root = FXMLLoader.load(getClass().getResource("Front1.fxml"));


            Scene scene = new Scene(root);
            primaryStage.setTitle("login");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
