/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import com.google.zxing.WriterException;
import elbaldi.models.question;
import elbaldi.models.quiz;
import elbaldi.services.QuestionCRUD;
import elbaldi.services.QuizCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import elbaldi.GUI.ScoreController;

/**
 * FXML Controller class
 *
 * @author selim
 */
public class JouerQuizController implements Initializable {

    @FXML
    private Text fixquestion;
    @FXML
    private RadioButton fixreponse1;
    @FXML
    private RadioButton fixreponse2;
    @FXML
    private RadioButton fixreponse3;
    
    @FXML
    private ToggleGroup tunisie;
    
    @FXML
    private Button fixsuivante;

    @FXML
    private Label numerofx;

   // private ArrayList<question> questions; // Liste de questions
    private int currentQuestionIndex; // Index de la question courante
    private ArrayList<String> reponsesUtilisateur; // Liste des réponses de l'utilisateur
    private quiz quiz;
    
    private List<question> questions = new ArrayList<>();
    
     QuestionCRUD ds = new QuestionCRUD();
     question q =new question();
    @FXML
    private Button back;
     
     
     
     public void setQuiz(quiz quiz) {
        this.quiz = quiz;
        System.out.println(quiz);
        //  Récupérer les 4 questions à partir de la source de données
        QuestionCRUD qc = new QuestionCRUD();
        question c = new question();
        questions = qc.filtreByidquiz(quiz);
        
       
 
 
        // Mélanger la liste de questions
        Collections.shuffle(questions);
           
      

         //Initialiser l'index de la question courante et la liste des réponses de l'utilisateur
        currentQuestionIndex = 0;
        reponsesUtilisateur = new ArrayList<>();

      //   Afficher la première question
        
        afficherQuestion();
        int numQuestionn = 1 ;
            int totalQuestions = questions.size();
         
            numerofx.setText("Question 1/" + totalQuestions );
        
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

   @FXML
    private void suivante() throws IOException, WriterException {
        // Enregistrer la réponse de l'utilisateur
        String reponse = null;
        if (fixreponse1.isSelected()) {
            reponse = fixreponse1.getText();
        } else if (fixreponse2.isSelected()) {
            reponse = fixreponse2.getText();
        } else if (fixreponse3.isSelected()) {
            reponse = fixreponse3.getText();
        }
        reponsesUtilisateur.add(reponse);
        
        // Vérifier si une réponse a été sélectionnée
        if (tunisie.getSelectedToggle() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez sélectionner une réponse", ButtonType.OK);
            alert.showAndWait();
            return;
        }
   
        
        // Passer à la question suivante 
     //   if (currentQuestionIndex < questions.size() -1) {  
       if (currentQuestionIndex < questions.size() -1) {

            currentQuestionIndex++;
            afficherQuestion();
            int numQuestion = currentQuestionIndex+1 ;
            int totalQuestions = questions.size();
            
            numerofx.setText("Question "+ numQuestion +"/" + totalQuestions );
        } 
       else if (currentQuestionIndex == questions.size()-1) {
      
                // Afficher la classe ScoreControlleur pour afficher le score
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Score.fxml"));
        Parent root = loader.load();
        ScoreController scoreController = loader.getController();
        float score = calculateScore(questions, reponsesUtilisateur);
     
        scoreController.setscore((float) score);
         
        Scene scene = new Scene(root);
        Stage stage = (Stage) fixsuivante.getScene().getWindow();
        stage.setScene(scene);
         stage.show();

                }

        }    
    
    private void afficherQuestion() {
        // Récupérer la question courante
        question q = questions.get(currentQuestionIndex);
      
        // Afficher la question et les réponses
        fixquestion.setText(q.getQuestionn());
        fixreponse1.setText(q.getReponse1());
        fixreponse2.setText(q.getReponse2());
        fixreponse3.setText(q.getReponse3());

        int lastQuestionIndex = questions.size() - 1;
       
              if (lastQuestionIndex == currentQuestionIndex) {
                    fixsuivante.setText("Voir Score");
              } else {
                    fixsuivante.setText("Question suivante");
                      }


        
        // Désélectionner les boutons radio
        tunisie.selectToggle(null);
    }

    
  

    

// Métier Calculer Score      
public float calculateScore(List<question> questions, List<String> userAnswers) {
    float scoree = 0.0f;
    for (int i = 0; i < questions.size(); i++) {
        question q = questions.get(i);
        String correctAnswer = q.getSolution();
        String userAnswer = userAnswers.get(i);
        if (correctAnswer.equals(userAnswer)) {
            scoree += 1.0;
        }
       
    }
   float totalQuestions = questions.size();
    float score = (scoree / totalQuestions) * 100.0f;
    return score;}





    @FXML
    private void goBack(ActionEvent event) {
        
       // alerte pour confirmer si on veut supprimer le quiz 
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Êtes-vous sûr de vouloir quitter ?");
        alert.setContentText("Toutes les réponses seront perdues.");
        ButtonType buttonTypeConfirm = new ButtonType("Confirmer");
        ButtonType buttonTypeCancel = new ButtonType("Annuler");
        alert.getButtonTypes().setAll(buttonTypeConfirm, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeConfirm) {
    // Rediriger vers la page suivante

        FXMLLoader loader = new FXMLLoader(getClass().getResource("client.fxml"));
    try {
        Parent root = loader.load();
        Scene scene = new Scene(root);
       Stage stage = (Stage) back.getScene().getWindow(); // backButton est le bouton de retour
       stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        ex.printStackTrace();
    }

    
}
}
}
