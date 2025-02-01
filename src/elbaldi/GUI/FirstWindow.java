
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package elbaldi.gui;

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

//import com.fasterxml.jackson.databind.JsonMappingException;
/**
 *
 * @author Yasmine
=======

/**
 *
 * @author mEtrOpOliS
>>>>>>> Origin/sami
 */
public class FirstWindow extends Application {
    
    @Override
    public void start(Stage primaryStage) {
//
//        try {
//            
//            
//           //Parent root = root=FXMLLoader.load(getClass().getResource("prodbacklist.fxml"));
//           //    Parent root = root=FXMLLoader.load(getClass().getResource("Front1.fxml"));
//           Parent root = root=FXMLLoader.load(getClass().getResource("ProduitFront2.fxml"));
//           
//           
//           
//            // Parent root = root=FXMLLoader.load(getClass().getResource("catgeorielistBack.fxml"));
//         
//        
//                     
//           
//            
//          //  Parent root = root=FXMLLoader.load(getClass().getResource("CommentaireBack.fxml"));
//            //Parent root = root=FXMLLoader.load(getClass().getResource("ProduitItem.fxml"));
//            Scene scene = new Scene(root);
//            
//            primaryStage.setTitle("Application Elbaldi ");
//            primaryStage.setScene(scene);
//            primaryStage.show();
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//
//        
//        try {
//            Parent root = FXMLLoader.load(getClass().getResource("Inscription.fxml"));
//            
//            
//            Scene scene = new Scene(root);
//            
//            primaryStage.setTitle("Inscription");
//            primaryStage.setScene(scene);
//            primaryStage.show();
//        } catch (IOException ex2) {
//            System.out.println(ex2.getMessage());       
//
//        }
//    }
//    Parent root;
//        try {
//                root = FXMLLoader.load(getClass().getResource("client.fxml"));
//       
//        
//        Scene scene = new Scene(root);
//        
//        primaryStage.setTitle("QUIZ");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());  }
//  
  /*Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("client.fxml"));
       
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("QUIZ");
        primaryStage.setScene(scene);
        primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());  }} */
    
    Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("front.fxml"));
       
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Afficher le module Quiz et promotion");
        primaryStage.setScene(scene);
        primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());  }}
    
    
 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
    
/**
 *
 * @author selim
 */


   
       
     
  
