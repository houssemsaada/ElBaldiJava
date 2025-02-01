/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;


import java.io.IOException;
import java.net.URL;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author selim
 */
public class AdminController implements Initializable {

    @FXML
    private ComboBox<String> Promotionfix;
    @FXML
    private ComboBox<String> Questionfix;
    @FXML
    private ComboBox<String> quizfix;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    // TODO
        ObservableList<String> list = FXCollections.observableArrayList("Ajouter Promotion","Modifier Promotion","Supprimer Promotion","Afficher Promotion");
         Promotionfix.setItems(list);
         
        ObservableList<String> list2 = FXCollections.observableArrayList("Ajouter question","modifier question","Supprimer question","Afficher question");
         Questionfix.setItems(list2);
          
         ObservableList<String> list3 = FXCollections.observableArrayList("Ajouter quiz","Modifier quiz","Supprimer quiz","Afficher quiz");
         quizfix.setItems(list3);
    }    

    //----------------------------------Promotion--------------------------------------------------------
    
    @FXML
    private void select(ActionEvent event) {
          // String s =  Promotionfix.getSelectionModel().getSelectedItem().toString();
      String selected = Promotionfix.getSelectionModel().getSelectedItem();
    if (selected.equals("Ajouter Promotion")) {
        // Redirection vers AjouterPromotionController
        // Vous pouvez remplacer "AjouterPromotion.fxml" par le nom de votre fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterPromotion.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) Promotionfix.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    } else if (selected.equals("Modifier Promotion")) {
        
        // Redirection vers AjouterPromotionController
        // Vous pouvez remplacer "AjouterPromotion.fxml" par le nom de votre fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Modifierpromotion.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) Promotionfix.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        } } 
      else if (selected.equals("Supprimer Promotion")){
                 // Redirection vers AjouterPromotionController
        // Vous pouvez remplacer "AjouterPromotion.fxml" par le nom de votre fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Supprimer promotion.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) Promotionfix.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
                }
    
    } 
    else if (selected.equals("Afficher Promotion")){
                 // Redirection vers AjouterPromotionController
        // Vous pouvez remplacer "AjouterPromotion.fxml" par le nom de votre fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherPromotion.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) Promotionfix.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
                }


    
    }
    }
    
   //---------------------------------------------Question-------------------------------------------------------------
    
    @FXML
    private void choisir(ActionEvent event) {
        String selected =  Questionfix.getSelectionModel().getSelectedItem();
    if (selected.equals("Ajouter question")) {
        // Redirection vers AjouterPromotionController
        // Vous pouvez remplacer "AjouterPromotion.fxml" par le nom de votre fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterQuestion.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage)  Questionfix.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    } else if (selected.equals("modifier question")) {
        
        // Redirection vers AjouterPromotionController
        // Vous pouvez remplacer "AjouterPromotion.fxml" par le nom de votre fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierQuestion.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage)  Questionfix.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        } } 
      else if (selected.equals("Supprimer question")){
                 // Redirection vers AjouterPromotionController
        // Vous pouvez remplacer "AjouterPromotion.fxml" par le nom de votre fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SupprimerQuestion.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage)  Questionfix.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
                }
    
    } 
    else if (selected.equals("Afficher question")){
                 // Redirection vers AjouterPromotionController
        // Vous pouvez remplacer "AjouterPromotion.fxml" par le nom de votre fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherQuestion.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage)  Questionfix.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
                }


    
    }
    }
    
    
    //-----------------------------------------------Quiz---------------------------------------------------------

    @FXML
    private void selectquiz(ActionEvent event) {
        
        String selected =  quizfix.getSelectionModel().getSelectedItem();
    if (selected.equals("Ajouter quiz")) {
        // Redirection vers AjouterPromotionController
        // Vous pouvez remplacer "AjouterPromotion.fxml" par le nom de votre fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterQuiz.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage)  quizfix.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    } else if (selected.equals("Modifier quiz")) {
        
        // Redirection vers AjouterPromotionController
        // Vous pouvez remplacer "AjouterPromotion.fxml" par le nom de votre fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierQuiz.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage)  quizfix.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        } } 
      else if (selected.equals("Supprimer quiz")){
                 // Redirection vers AjouterPromotionController
        // Vous pouvez remplacer "AjouterPromotion.fxml" par le nom de votre fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SupprimerQuiz.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage)  quizfix.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
                }
    
    } 
    else if (selected.equals("Afficher quiz")){
                 // Redirection vers AjouterPromotionController
        // Vous pouvez remplacer "AjouterPromotion.fxml" par le nom de votre fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherQuiz.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage)  quizfix.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
                }


    
    }
    }
}

