/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.Evenement;
import elbaldi.models.quiz;
import elbaldi.gui.ModifierQuizController;
import elbaldi.services.QuizCRUD;
import elbaldi.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author selim
 */
public class ItemBackController implements Initializable {

    @FXML
    private ImageView imgLabel;
  
    @FXML
    private Button supprimer;
   
    @FXML
    private Label nomLabell;
    @FXML
    private Label di;
    @FXML
    private Button fxquestion;
    @FXML
    private Button modifierfix;
    @FXML
    public Label idLabel;
    private quiz qcc;
    private int id_quizz;
   
    QuizCRUD qc = new QuizCRUD();
    
     public void setquiz(quiz q) {
          
          this.qcc=q;   
          id_quizz=q.getId_quiz();
          nomLabell.setText(q.getNom());
          di.setText(q.getDifficulte());
          // idLabel.setText(String.valueOf(q.getId_quiz()));
          // imgLabel.setText(q.getImgview());              
    }
     
   
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


        
    @FXML
   private void delete(ActionEvent event) throws IOException {
       
      quiz q = new quiz();
      q.setNom(nomLabell.getText());

  int option = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de supprimer le quiz ?", "Confirmation de suppression", JOptionPane.YES_NO_OPTION);
    if (option == JOptionPane.YES_OPTION) {
      qc.supprimerquiz(q);
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Information Dialog");
      alert.setHeaderText(null);
      alert.setContentText("Quiz a été supprimé avec succés!");
      alert.show();
      Parent loader = FXMLLoader.load(getClass().getResource("front.fxml"));
      nomLabell.getScene().setRoot(loader);
    }
     

    }



   

   
    // Pour naviguer vers la liste des questions : 
    @FXML
    private void question(ActionEvent event) {
        
        
        

        FXMLLoader loader = new FXMLLoader(getClass().getResource("QuestionBack.fxml"));
        try {
            Parent root = loader.load();
            QuestionBackController qbc= loader.getController();
            qbc.setQuiz(qcc);
            
            nomLabell.getScene().setRoot(root);
            
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

   

    @FXML
    private void Modifier(ActionEvent event){
       
        
        try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifierQuiz.fxml"));
        Parent root = loader.load();
        ModifierQuizController qbc = loader.getController();
        qbc.setQuiz(qcc);
       // nomLabell.getScene().setRoot(root);
          Scene scene = new Scene(root);
        Stage stage = (Stage) nomLabell.getScene().getWindow(); // backButton est le bouton de retour
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }
    


    }
    
    
    
