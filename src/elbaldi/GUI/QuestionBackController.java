/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.question;
import elbaldi.models.quiz;
import elbaldi.services.QuestionCRUD;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import elbaldi.gui.AjouterQuestionController;

/**
 * FXML Controller class
 *
 * @author selim
 */
public class QuestionBackController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane gridd;
    
     private List<question> listeQuestion = new ArrayList<>();
     QuestionCRUD ds = new QuestionCRUD();
     question q =new question();
    @FXML
    private Button back;
    @FXML
    private Button ajouterfx;
    private quiz quiz ; 
    @FXML
    private TextField quizid;
private question qcc;
  
  QuestionCRUD qc = new QuestionCRUD();
    /**
     *
     * @param quiz
     */
    public void setQuiz(quiz quiz) {
        this.quiz = quiz;
        System.out.println(quiz);
         try {
            listeQuestion = ds.filtreByidquiz(quiz);
            
            int column = 0;
            int row = 1;
            for (int i = 0; i < listeQuestion.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ItemQuestion.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
               ItemQuestionController itemController = fxmlLoader.getController();
                itemController.setquestion(listeQuestion.get(i),quiz );
                if (column == 1) {
                    column = 0;
                    row++;
                }

                gridd.add(anchorpane, column++, row);
                 gridd.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridd.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridd.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridd.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridd.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridd.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorpane, new Insets(10));
            }
      

        } catch (IOException ex) {
        }
    }
    
   

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }
        // TODO

  
    public void afficher(){
     
    }

    @FXML
    private void goBack(ActionEvent event) {
           
    // Redirection vers frontController
    FXMLLoader loader = new FXMLLoader(getClass().getResource("front.fxml"));
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

    @FXML
    private void ajouter(ActionEvent event) {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterQuestion.fxml"));
    try {
        Parent root = loader.load();
        AjouterQuestionController b = loader.getController();
        System.out.println("questionbackcontroll" + quiz);
        b.setQuizz(quiz);
       
        Scene scene = new Scene(root);
        Stage stage = (Stage) back.getScene().getWindow(); // backButton est le bouton de retour
        stage.setScene(scene);
        stage.show();
          
    } catch (IOException ex) {
        ex.printStackTrace();
    }
        
    }

    

    @FXML
    private void afficheronclick(MouseEvent event) {
                //    afficher();

    }

    }
    

