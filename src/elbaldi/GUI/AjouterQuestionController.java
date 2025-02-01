/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.gui;


import elbaldi.models.question;
import elbaldi.models.quiz;
import elbaldi.services.QuestionCRUD;
import elbaldi.services.QuizCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import elbaldi.GUI.QuestionBackController;

/**
 * FXML Controller class
 *
 * @author selim
 */
public class AjouterQuestionController implements Initializable {

    @FXML
    private Button ajoutQuestion;
    @FXML
    private TextField fxquestion;
    @FXML
    private ComboBox<String> fxdifficulte;
    @FXML
    private TextField fxreponse1;
    @FXML
    private TextField fxreponse2;
    @FXML
    private TextField fxreponse3;
    @FXML
    private ComboBox<String> fxsolution;
   
    @FXML
    private Button backfix;

    private quiz qui;
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

    public void setQuizz(quiz selectedQuiz) {
        this.qui = selectedQuiz;
        System.out.println("aaaa"+selectedQuiz);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        QuizCRUD quizCRUD = new QuizCRUD();
  
        
    
        ObservableList<String> difficultes = FXCollections.observableArrayList("Facile", "Moyenne", "Difficile");
        fxdifficulte.setItems(difficultes);
        
     ObservableList<String> solutions = FXCollections.observableArrayList("Réponse1", "Réponse2", "Réponse3");
fxsolution.setItems(solutions);

}


        
    
    @FXML
    private void ajouter(ActionEvent event) {
    
  String difficulte = fxquestion.getText();
    String questionn = fxdifficulte.getValue();
    String reponse1 = fxreponse1.getText();
    String reponse2 = fxreponse2.getText();
    String reponse3 = fxreponse3.getText();
    String solution = fxsolution.getValue(); 
    /// Récupérer le quiz sélectionné
quiz selectedQuiz = this.qui;
int selectedQuizId = selectedQuiz == null ? 0 : selectedQuiz.getId_quiz();

 // Créer un Map qui associe chaque réponse à sa valeur correspondante
    Map <String, String> reponses = new HashMap<>();
    reponses.put("Réponse1", reponse1);
    reponses.put("Réponse2", reponse2);
    reponses.put("Réponse3", reponse3);

    // Récupérer la valeur de la réponse correspondant à la solution sélectionnée
    String solutionValue = reponses.get(solution);

    // Créer l'objet question avec le quiz sélectionné
     if (difficulte.isEmpty() || questionn.isEmpty() || reponse1.isEmpty() || reponse2.isEmpty() || reponse3.isEmpty() || solution.isEmpty() ) {
        // Afficher une alerte si l'un des champs est vide
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs.");
        alert.showAndWait();
    } else {
   
        question qt = new question(questionn,difficulte,reponse1,reponse2,reponse3,solutionValue);
        QuestionCRUD qcr = new QuestionCRUD();
        
      if (difficulte.matches(".*[a-zA-Z]{6,}.*")) {
           
    qcr.ajouterQuestion(qt, selectedQuiz.getId_quiz());
    
                // Afficher une alerte en fonction de l'état de l'ajout
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText(null);
                alert.setContentText("La question a été ajoutée avec succès.");
                alert.showAndWait();
        } else {
            // Afficher une alerte en fonction de l'état de l'ajout
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La question doit contenir au moins 6 alphabets.");
            alert.showAndWait();
        }
    }
    }
    @FXML
    private void goBack(ActionEvent event) {
     try {  
    // Redirection vers BrouillonController
    // Vous pouvez remplacer "Brouillon.fxml" par le nom de votre fichier FXML
    FXMLLoader loader = new FXMLLoader(getClass().getResource("QuestionBack.fxml"));
    
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) backfix.getScene().getWindow(); // backButton est le bouton de retour
        QuestionBackController qc = loader.getController();
        qc.setQuiz(qui);
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
    

