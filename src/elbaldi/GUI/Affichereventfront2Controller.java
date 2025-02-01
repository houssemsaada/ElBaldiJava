/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.Evenement;
import elbaldi.services.EvenementService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class Affichereventfront2Controller implements Initializable {

    @FXML
    private GridPane gridpane;
    @FXML
    private TextField searchField;
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
EvenementService es = new EvenementService();
    Evenement e1;
    /**
     * Initializes the controller class.
     */
   @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
             
            List<Evenement> events = es.recuperer();
            int row = 0;
            int column = 0;
            for (int i = 0; i < events.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("eventfront.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
                EventfrontController controller = loader.getController();
                controller.setEvenement(events.get(i));
                

                gridpane.add(pane, column, row);
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }
                
                
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(AfficherevenFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }    }     

  
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


    
}
