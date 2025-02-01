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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author selim
 */
public class PlayController implements Initializable {

    @FXML
    private Text fxquestion;
    @FXML
    private RadioButton rep1;
    @FXML
    private RadioButton rep2;
    @FXML
    private RadioButton rep3;
    private quiz quiz;
    
    private List<question> listeQuestion = new ArrayList<>();
     QuestionCRUD ds = new QuestionCRUD();
     question q =new question();
     
     
     
     public void setQuiz(quiz quiz) {
        this.quiz = quiz;
        System.out.println(quiz);
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listeQuestion = ds.filtreByidquiz(quiz);
        for (question question : listeQuestion) {
            String laQuestion = question.getQuestionn();
            // Faites quelque chose avec la question récupérée (par exemple, afficher la question)
            System.out.println(laQuestion);
            
            fxquestion.setText(laQuestion);
        }
    }}
