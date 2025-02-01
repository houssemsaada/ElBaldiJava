/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

/**
 *
 * @author MSI
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
         try {

        //FXMLLoader loader = new FXMLLoader(getClass().getResource("afficher participation.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterEvenement.fxml"));
            
             //     FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherevenFront.fxml"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("afficher participation.fxml"));
       // FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterEvenement.fxml"));
            
            //       FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherevenFront.fxml"));


            Parent root = loader.load();
            Scene sc = new Scene(root);
            primaryStage.setTitle("Personnes");
            primaryStage.setScene(sc);
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

