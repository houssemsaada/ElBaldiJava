/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class Mainclass extends Application {
    
   @Override
    public void start(Stage primaryStage) {
       
        Parent root;
        try {
//root=FXMLLoader.load(getClass().getResource("AfficherReservationBack.fxml"));
                 // root=FXMLLoader.load(getClass().getResource("BonPlanFront.fxml"));
          // root=FXMLLoader.load(getClass().getResource("AfficherBonplan.fxml"));

          //   root=FXMLLoader.load(getClass().getResource("Avisback.fxml"));
            //root=FXMLLoader.load(getClass().getResource("AvisbackController.fxml"));
          root=FXMLLoader.load(getClass().getResource("GerantMainScreen.fxml"));
             //root=FXMLLoader.load(getClass().getResource("BonPlanFront.fxml"));
             // root=FXMLLoader.load(getClass().getResource("AjouReservationFront1.fxml"));
          // root=FXMLLoader.load(getClass().getResource("calendrier.fxml"));
          // root=FXMLLoader.load(getClass().getResource("showReservationBack.fxml"));
            //root=FXMLLoader.load(getClass().getResource("GerantMainScreen.fxml"));
             
            // root=FXMLLoader.load(getClass().getResource("BpDetailsFront.fxml"));
           //root=FXMLLoader.load(getClass().getResource("GerantMainScreen.fxml"));
             

//root=FXMLLoader.load(getClass().getResource("Bonplanbacklist.fxml"));
             
             Scene scene = new Scene(root);
        
        primaryStage.setTitle("Application Elbaldi");
        primaryStage.setScene(scene);
        primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
