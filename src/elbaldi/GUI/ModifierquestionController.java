
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.Utilisateur;
import elbaldi.models.promotion;
import elbaldi.models.question;
import elbaldi.models.quiz;
import elbaldi.services.PromotionCRUD;
import elbaldi.services.QuestionCRUD;
import elbaldi.services.QuizCRUD;
import elbaldi.services.UtilisateurCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author selim
 */
public class ModifierquestionController implements Initializable {

    @FXML
    private Button backfx;
   
    @FXML
    private TextField fxreponse1;
    @FXML
    private TextField fxreponse2;
    @FXML
    private TextField fxreponse3;
    @FXML
    private TextField fxsolutionn;
    @FXML
    private ComboBox<quiz> fxquiz;
    @FXML
    private Button modifierquestionfx;
   
    @FXML
    private TextField fxquestionn;
    
    private question questionn;
    @FXML
    private ComboBox<String> fxdifficultes;
    private quiz quiz;
    @FXML
    private Button Accueilfx;
    @FXML
    private Button profilfx;
    @FXML
    private Button categoriefx;
    @FXML
    private Button produitfx;
    @FXML
    private Button commandefx;
    @FXML
    private Button Livrfx;
    @FXML
    private Button Bonplanfx;
    @FXML
    private Button Quizfx;
    @FXML
    private Button Eventfx;
    @FXML
    private Button participationfx;
    @FXML
    private Button GestUser;
    @FXML
    private Button Decofx;
    /**
     * Initializes the controller class.
     */
    public void setQuestion(question qu,quiz q) {
    this.questionn = qu;
    this.quiz=q;
    this.fxquestionn.setText(qu.getQuestionn());
    this.fxdifficultes.setValue(qu.getDifficulte());
    this.fxreponse1.setText(qu.getReponse1());
    this.fxreponse2.setText(qu.getReponse2());
    this.fxreponse3.setText(qu.getReponse3());
    this.fxsolutionn.setText(qu.getSolution());
     ObservableList<String> difficultes = FXCollections.observableArrayList("Facile", "Moyenne", "Difficile");
        fxdifficultes.setItems(difficultes);
        QuizCRUD quizCRUD = new QuizCRUD();
        List<quiz> allquizs = quizCRUD.afficherQuiz();
        fxquiz.getItems().addAll(allquizs);

        // Set the selected user in the combo box to the one already assigned to the quiz
        fxquiz.setValue(qu.getquiz());
    modifierquestionfx.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            System.out.println("eeeeeee");
            QuestionCRUD questioncrud = new QuestionCRUD();
            question qu = new question();
            if (!fxquestionn.getText().isEmpty() && !fxdifficultes.getValue().isEmpty() && !fxreponse1.getText().isEmpty() && 
                !fxreponse2.getText().isEmpty() && !fxreponse3.getText().isEmpty() && !fxsolutionn.getText().isEmpty() 
                && fxquiz.getValue() != null) {
                
                if (fxquestionn.getText().matches(".*[a-zA-Z]{6,}.*")) {
                    
                    if (fxreponse1.getText().equalsIgnoreCase(fxsolutionn.getText()) || 
                        fxreponse2.getText().equalsIgnoreCase(fxsolutionn.getText()) || 
                        fxreponse3.getText().equalsIgnoreCase(fxsolutionn.getText())) {
                        
                        qu.setQuestionn(fxquestionn.getText());
                        qu.setDifficulte(fxdifficultes.getValue());
                        qu.setReponse1(fxreponse1.getText());
                        qu.setReponse2(fxreponse2.getText());
                        qu.setReponse3(fxreponse3.getText());
                        qu.setSolution(fxsolutionn.getText());
                        qu.setquiz(fxquiz.getValue());
                        questioncrud.modifierquestion(qu, questionn.getId_question());

                        // Alert if modification is successful 
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Modification de la question");
                        alert.setHeaderText(null);
                        alert.setContentText("La question a été modifiée avec succès !");
                        alert.showAndWait(); 
                    } else {
                        // Afficher une alerte en fonction de l'état de l'ajout
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Erreur");
                        alert.setHeaderText(null);
                        alert.setContentText("La solution doit être compatible avec l'une des réponses.");
                        alert.showAndWait();
                    }
                } else {
                    // Afficher une alerte en fonction de l'état de l'ajout
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("La question doit contenir au moins 6 alphabets.");
                    alert.showAndWait();
                }
            } else {
                // Afficher une alerte en fonction de l'état de l'ajout
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("il faut remplir tout les champs à remplir.");
                    alert.showAndWait();
            }
        } } ); } 

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private void modifierquestion(ActionEvent event) {
        

    }
        
    
      @FXML
    private void goBack(ActionEvent event) {
           // Redirection vers BrouillonController
    // Vous pouvez remplacer "Brouillon.fxml" par le nom de votre fichier FXML
    FXMLLoader loader = new FXMLLoader(getClass().getResource("QuestionBack.fxml"));
    try {
        Parent root = loader.load();
        Scene scene = new Scene(root);
        QuestionBackController qbc= loader.getController();
            qbc.setQuiz(quiz);
        Stage stage = (Stage) backfx.getScene().getWindow(); // backButton est le bouton de retour
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    }

   @FXML
    private void accueilAction(ActionEvent event) {
        commandeGUI.changeScene(event, "templateBack.fxml", "Acceuil");

    }

    @FXML
    private void profilAction(ActionEvent event) {
        commandeGUI.changeScene(event, "ProfileAdmin.fxml", "Profile");

    }


    @FXML
    private void commandesAction(ActionEvent event) {

        commandeGUI.changeScene(event, "commandeinterface.fxml", "commande ");
    }

    @FXML
    private void LivraisonAction(ActionEvent event) {
        commandeGUI.changeScene(event, "livraisoninterface.fxml", "livraison ");

    }

    @FXML
    private void BonpalnsAction(ActionEvent event) {
        commandeGUI.changeScene(event, "bonplanbacklist.fxml", "bonplans ");

    }

    @FXML
    private void QuizAction(ActionEvent event) {
        commandeGUI.changeScene(event, "front.fxml", "quiz ");

    }

    @FXML
    private void eventaction(ActionEvent event) {
        commandeGUI.changeScene(event, "AjouterEvenement.fxml", "evenemets ");

    }

    @FXML
    private void participationaction(ActionEvent event) {
        commandeGUI.changeScene(event, "afficher participation.fxml", "participation ");

    }

    @FXML
    private void GestuserAction(ActionEvent event) {
            commandeGUI.changeScene(event, "MenuAdmin.fxml", "gestion utilisateurs ");

    }

    @FXML
    private void decoAction(ActionEvent event) {
    }

    @FXML
    private void categ(ActionEvent event) {
    }

    @FXML
    private void prodd(ActionEvent event) {
    }

   
        
        
        
        
        
       
 
}