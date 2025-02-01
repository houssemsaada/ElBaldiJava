/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.Utilisateur;
import elbaldi.models.produit;
import elbaldi.models.quiz;
import elbaldi.services.QuizCRUD;
import elbaldi.services.UserSession;
import elbaldi.services.UtilisateurCRUD;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author selim
 */
public class ItemQuizController implements Initializable {

    
    @FXML
    private Label nomLabel;
    @FXML
    private Label dfiffiLabel;
    @FXML
    private Button jouerLabel;
    private quiz qcc;
    private quiz quiz;
      UserSession userSession = new UserSession();
    Utilisateur u = userSession.getUser();
      
      QuizCRUD qc = new QuizCRUD();
      
      public void setquiz(quiz q) {
          
          this.qcc=q;       
          nomLabel.setText(q.getNom());
          dfiffiLabel.setText(q.getDifficulte());
          // idLabel.setText(String.valueOf(q.getId_quiz()));
          // imgLabel.setText(q.getImgview());            
       }
       
    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
    }    
          
    

    @FXML
    private void jouez(ActionEvent event) {
         
    
//    System.out.println(u.getId_user());
//    System.out.println(qc.getNombreJouer(u.getId_user()));

     
    if (qc.getNombreJouer(u.getId_user()) >= 3) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Vous avez déjà joué le quiz plus que 3 fois. A la prochaine !");
        alert.showAndWait();
        return;
    }
    int nbRestant = 3 - qc.getNombreJouer(u.getId_user());
    if (nbRestant == 1) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Il vous reste qu'une seule fois pour jouer au quiz.");
        alert.showAndWait();
    } else if (nbRestant == 2) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Il vous reste encore deux fois pour jouer au quiz.");
        alert.showAndWait();
    }else if (nbRestant == 3) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Vous pouvez jouer au quiz seulement 3 fois.");
        alert.showAndWait();
    }
    
    
    
    // le code qui permet de prendre la confirmation de la part du client
    if (nbRestant > 0) {
    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setTitle("Confirmation");
    dialog.setHeaderText("Voulez-vous jouer au quiz ?");
    ButtonType jouerButtonType = new ButtonType("Jouer", ButtonData.OK_DONE);
    ButtonType annulerButtonType = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
    dialog.getDialogPane().getButtonTypes().addAll(jouerButtonType, annulerButtonType);

    dialog.setResultConverter(dialogButton -> {
        if (dialogButton == jouerButtonType) {
            return jouerButtonType;
        } else if (dialogButton == annulerButtonType) {
            return annulerButtonType;
        }
        return null;
    });

    Optional<ButtonType> result = dialog.showAndWait();
    if (result.isPresent() && result.get() == jouerButtonType) {
        try {
            qc.setNombreJouer(u.getId_user(), (qc.getNombreJouer(u.getId_user()) + 1)); // i
            
            
            
//    FXMLLoader loader = new FXMLLoader(getClass().getResource("JouerQuiz.fxml"));
//    try {
//            Parent root = loader.load();
//            JouerQuizController qbc= loader.getController();
//            qbc.setQuiz(qcc);
//            //nomLabel.getScene().setRoot(root);
//            Scene scene = new Scene(root);
//        Stage stage = (Stage) nomLabel.getScene().getWindow(); // backButton est le bouton de retour
//        stage.setScene(scene);
//        stage.show();
//    } catch (IOException ex) {
//        ex.printStackTrace();
//    }

FXMLLoader loader = new FXMLLoader();
loader.setLocation(getClass().getResource("JouerQuiz.fxml"));
Parent root = loader.load();

JouerQuizController cb = loader.getController();
  cb.setQuiz(qcc);
Stage stage = new Stage();
stage.setTitle("Quiz");
Scene scene = new Scene(root);
stage.setScene(scene);
stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(ItemQuizController.class.getName()).log(Level.SEVERE, null, ex);
        }

   
    
    
    
    }}
    
    }}

