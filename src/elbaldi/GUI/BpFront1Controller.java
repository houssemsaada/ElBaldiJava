/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.bonplan;
import elbaldi.services.BonplanCrud;
import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class BpFront1Controller implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    private List<bonplan> listebonplan = new ArrayList<>();
    BonplanCrud bp = new BonplanCrud();
    @FXML
    private ComboBox<String> Typefx;
    @FXML
    private TextField search;
    private Button bonplan1;
    @FXML
    private Button insc;
    @FXML
    private Button cnx;
    @FXML
    private Button prodfx;
    @FXML
    private Button bonplanfx;
    @FXML
    private Button eventfx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Typefx.getItems().add("restaurant");
        Typefx.getItems().add("Hotel");
        Typefx.getItems().add("Tous");
        // TODO
        afficher();

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Texte changé : " + oldValue + " -> " + newValue);
//            if (!"".equals(newValue)) {
            try {
                grid.getChildren().remove(0, listebonplan.size());

                search(newValue);
            } catch (SQLException ex) {
                Logger.getLogger(BpFront1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
//            } else {
//                afficher();
//            }

        });

        // afficher();
        search.setPromptText("Rechercher...");
    }

    public void afficher() {
         try {

            listebonplan = bp.afficherBonplan();

            int column = 0;
            int row = 1;
            for (int i = 0; i < listebonplan.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/elbaldi/GUI/itembpFront1.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
                ItembpFront1Controller itemController = fxmlLoader.getController();
                itemController.setData(listebonplan.get(i));
                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorpane, column++, row);
                GridPane.setMargin(anchorpane, new Insets(10));
            }
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void filtrer(ActionEvent event) {
        try {
            if (Typefx.getSelectionModel().getSelectedItem().toString() == "Tous") {
                afficher();
            } else {
                //System.out.println(Typefx.getSelectionModel().toString());
                listebonplan = bp.filtreByType(Typefx.getSelectionModel().getSelectedItem().toString());
                grid.getChildren().clear();
                int column = 0;
                int row = 1;
                for (int i = 0; i < listebonplan.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/elbaldi/GUI/itembpFront1.fxml"));
                    AnchorPane anchorpane = fxmlLoader.load();
                    ItembpFront1Controller itemController = fxmlLoader.getController();
                    itemController.setData(listebonplan.get(i));
                    if (column == 3) {
                        column = 0;
                        row++;
                    }

                    grid.add(anchorpane, column++, row);
                    GridPane.setMargin(anchorpane, new Insets(10));
                }
            }
        } catch (IOException ex) {
        }

    }

    public void search(String titre) throws SQLException {
        try {
            listebonplan = bp.rechercher(titre);

            //listebonplan = bp.afficherBonplan();
            //grid.getChildren().remove(0, listebonplan.size());
            int column = 0;
            int row = 1;
            for (int i = 0; i < listebonplan.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/elbaldi/GUI/itembpFront1.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
                ItembpFront1Controller itemController = fxmlLoader.getController();
                itemController.setData(listebonplan.get(i));
                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorpane, column++, row);
                GridPane.setMargin(anchorpane, new Insets(10));
            }
        } catch (IOException ex) {
        }

    }

//    private void bpbtn(ActionEvent event) throws IOException {
//         FXMLLoader loader = new FXMLLoader();
//
//        loader.setLocation(getClass().getResource("BpFront.fxml"));
//        Parent root = loader.load();
//        bonplan1.getScene().setRoot(root);
//    }
    private void bpbtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("BpFront1.fxml"));
        Parent root = loader.load();
        bonplan1.getScene().setRoot(root);
    }

  

       @FXML
    private void produitsf(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("Front1.fxml"));
        Parent root = loader.load();
        prodfx.getScene().setRoot(root);
    }

       @FXML
    private void bonplanAction(ActionEvent event) {
        commandeGUI.changeScene(event, "BpFront1.fxml", "Login");

    }

    @FXML
    private void eventAction(ActionEvent event) {
                commandeGUI.changeScene(event, "afficherevenFront.fxml", "Login");

    }

    @FXML
    private void connecter(ActionEvent event) {
        commandeGUI.changeScene(event, "Login.fxml", "Login");

    }

    @FXML
    private void inscr(ActionEvent event) {
        commandeGUI.changeScene(event, "inscription.fxml", "Login");

    }



    

}
