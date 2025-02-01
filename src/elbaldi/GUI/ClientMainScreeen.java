package elbaldi.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ClientMainScreeen implements Initializable{

    @FXML
    private Button btnMenus;


    @FXML
    private GridPane grid;

    @FXML
    private Pane pnlCustomer;
    @FXML
    private Button produitfx;
    @FXML
    private Button Bonplanfx;
    @FXML
    private Button Quizfx;
    @FXML
    private Button Eventfx;
    @FXML
    private Button btnSignout1;
    @FXML
    private Button produitfx1;

    @FXML
    void ProfileUser(ActionEvent event) {
                commandeGUI.changeScene(event, "ClientMainScreeen.fxml", "Profile");

    }
 


    private void produitsf(ActionEvent event) throws IOException {
        
    }

    private void profilAction(ActionEvent event) {

    }

    private void bonplanAction(ActionEvent event) {
        commandeGUI.changeScene(event, "BpFront.fxml", "Bons plans");

    }

    private void EventAction(ActionEvent event) {

        commandeGUI.changeScene(event, "affichereventfront2.fxml", "évènement");

    }

    @FXML
    private void QuizAction(ActionEvent event) {
        commandeGUI.changeScene(event, "Client.fxml", "Quiz ");

    }

    private void deconfx(ActionEvent event) {
       commandeGUI.changeScene(event, "Front1.fxml", "Profile");

    }


    @FXML
    private void histcommandes(ActionEvent event) {
         grid.getChildren().clear();
        try {
            // TODO
            FXMLLoader cards = new FXMLLoader();
            cards.setLocation(getClass().getResource("commandeuser.fxml"));

            AnchorPane anchorPane = cards.load();

            grid.add(anchorPane, 1, 1);

            GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
grid.getChildren().clear();
        try {
            // TODO
            FXMLLoader cards = new FXMLLoader();
            cards.setLocation(getClass().getResource("ProfileUser.fxml"));

            AnchorPane anchorPane = cards.load();

            grid.add(anchorPane, 1, 1);

            GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }    }

    @FXML
    private void prodd(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("ProduitFront2.fxml"));
        Parent root = loader.load();
        produitfx.getScene().setRoot(root);
    }

    @FXML
    private void BonpalnsAction(ActionEvent event) {
                commandeGUI.changeScene(event, "BpFront.fxml", "Bons plans");

    }

    @FXML
    private void eventaction(ActionEvent event) {
        
    }
        
    }