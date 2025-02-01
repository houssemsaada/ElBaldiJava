/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author selim
 */
public class StartQUIZController implements Initializable {

    @FXML
    private Button fixdemarrez;
    @FXML
    private ImageView fximage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fixdemarrez.setOnAction(event -> {
    try {
        // Charger la vue FXML de la page JouerQuizController
        Parent root = FXMLLoader.load(getClass().getResource("JouerQuiz.fxml"));
        Scene scene = new Scene(root);
        
        // Obtenir la fenêtre actuelle et définir la nouvelle scène
        Stage stage = (Stage) fixdemarrez.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (Exception e) {
        System.out.println("Erreur : " + e.getMessage());
    }
});
    }    
    
}
