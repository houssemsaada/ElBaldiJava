/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.question;
import elbaldi.models.quiz;
import elbaldi.services.QuestionCRUD;
import elbaldi.services.QuizCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author selim
 */
public class ItemQuestionController implements Initializable {

    @FXML
    private Label fixquestion;
    @FXML
    private Label fixdiffculte;
    @FXML
    private Label fixreponse1;
    @FXML
    private Label fxrep2;
    @FXML
    private Label fixrep3;
    @FXML
    private Label fxsolution;
    @FXML
    private Label quizfx;
    
    private question question ;
    @FXML
    private Button fixsupp;
    @FXML
    private AnchorPane itemPane;
    @FXML
    private Button fxmodifier;
    @FXML
    private Label idLabel;
    
    
      private question qcc;
      private quiz quizz;
  QuestionCRUD qc = new QuestionCRUD();
    
        
  public void setquestion(question qq,quiz q) {
         this.qcc= qq;   
         this.quizz=q;
        idLabel.setText(String.valueOf(qq.getId_question()));
         fixdiffculte.setText(qq.getDifficulte());
         fixquestion.setText(qq.getQuestionn());
         fixreponse1.setText(qq.getReponse1());
         fxrep2.setText(qq.getReponse2());
         fixrep3.setText(qq.getReponse3());
         fxsolution.setText(qq.getSolution());
         quizfx.setText(qq.getquiz().toString());
         
  }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }
    
 

    @FXML
    private void supprimer(ActionEvent event) throws IOException {
         question q = new question();
         q.setId_question(Integer.parseInt(idLabel.getText()));
         int option = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de supprimer la question ?", "Confirmation de suppression", JOptionPane.YES_NO_OPTION);
    if (option == JOptionPane.YES_OPTION) {
       qc.supprimerquestion(q);
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Information Dialog");
      alert.setHeaderText(null);
      alert.setContentText("Question supprimée avec succés!");
      alert.show();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("QuestionBack.fxml"));
    try {
        Parent root = loader.load();
        Scene scene = new Scene(root);
        QuestionBackController qbc= loader.getController();
            qbc.setQuiz(quizz);
        Stage stage = (Stage) idLabel.getScene().getWindow(); // backButton est le bouton de retour
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    } 
        
      
    }

    @FXML
    private void modifier(ActionEvent event) {
         
        try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("modifierquestion.fxml"));
        Parent root = loader.load();
        ModifierquestionController qbc = loader.getController();
        qbc.setQuestion(qcc,quizz);
        
         fixquestion.getScene().setRoot(root);
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }
}