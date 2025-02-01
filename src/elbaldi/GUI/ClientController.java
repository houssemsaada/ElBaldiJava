/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.quiz;
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
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author selim
 */
public class ClientController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
  
     private List<quiz> listeQuiz = new ArrayList<>();
     QuizCRUD ds = new QuizCRUD();
    @FXML
    private Button profilfx;
    @FXML
    private Button prodfx;
    @FXML
    private Button bonplanfx;
    @FXML
    private Button Eventfx;
    @FXML
    private Button Quizfx;
    @FXML
    private Button decofx;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    afficher();
     
    }
        // TODO
    public void afficher(){
        try {
         
            listeQuiz = ds.afficherQuiz();
            
            int column = 0;
            int row = 1;
            for (int i = 0; i < listeQuiz.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ItemQuiz.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
               ItemQuizController itemController = fxmlLoader.getController();
                itemController.setquiz(listeQuiz.get(i)
                
                );
                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorpane, column++, row);
                 grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorpane, new Insets(10));
            }
      

        } catch (IOException ex) {
        }
    }

 @FXML
    private void produitsf(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("ProduitFront2.fxml"));
        Parent root = loader.load();
        prodfx.getScene().setRoot(root);
    }

    @FXML
    private void profilAction(ActionEvent event) {
        commandeGUI.changeScene(event, "ClientMainScreeen.fxml", "Profile");

    }

    @FXML
    private void bonplanAction(ActionEvent event) {
        commandeGUI.changeScene(event, "BpFront.fxml", "Bons plans");

    }

    @FXML
    private void EventAction(ActionEvent event) {

        commandeGUI.changeScene(event, "affichereventfront2.fxml", "évènement");

    }

    @FXML
    private void QuizAction(ActionEvent event) {
        commandeGUI.changeScene(event, "Client.fxml", "Quiz ");

    }

    @FXML
    private void deconfx(ActionEvent event) {
       commandeGUI.changeScene(event, "Front1.fxml", "Profile");

    }

    @FXML
    private void panierAction(MouseEvent event) {
                try {
            FXMLLoader fxmlLoader = new FXMLLoader(commandeGUI.class.getResource("consulterPanier.fxml"));
            Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene1 = new Scene(fxmlLoader.load());
            stage1.setTitle("Panier");
            stage1.setScene(scene1);
            stage1.setResizable(false);
            stage1.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  

    
}
