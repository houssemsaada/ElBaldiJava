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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class BonplanbacklistController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Button ajoutfx;
    @FXML
    private Button showC;
    private List<bonplan> listebonplan = new ArrayList<>();
    BonplanCrud bp = new BonplanCrud();
    private Button bonp;
    @FXML
    private Button Accueilfx;
    @FXML
    private Button profilfx;
    @FXML
    private Button categoriefx;
    @FXML
    private Button produitfx;
    @FXML
    private Button commandefx;
    @FXML
    private Button Livrfx;
    @FXML
    private Button Bonplanfx;
    @FXML
    private Button Quizfx;
    @FXML
    private Button Eventfx;
    @FXML
    private Button participationfx;
    @FXML
    private Button GestUser;
    @FXML
    private Button Decofx;
    @FXML
    private ComboBox<String> Typefx;
    @FXML
    private TextField searchField;
    @FXML
    private Button stat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            Typefx.getItems().add("Restaurant");
            Typefx.getItems().add("Hotel");
            Typefx.getItems().add("Tous");
        
          afficher();
                searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Texte changé : " + oldValue + " -> " + newValue);
//            if (!"".equals(newValue)) {
                try {
                    grid.getChildren().remove(0, listebonplan.size());

                    searchField(newValue);
                } catch (SQLException ex) {
                    Logger.getLogger(BpFrontController.class.getName()).log(Level.SEVERE, null, ex);
                }
//            } else {
//                afficher();
//            }

        });

        // afficher();
        searchField.setPromptText("Rechercher...");
          
          
    }    
public void afficher() {
        try {
            grid.getChildren().remove(0, listebonplan.size());
           
            listebonplan = bp.afficherBonplan();
      
            int column = 0;
            int row = 1;
            for (int i = 0; i < listebonplan.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/elbaldi/GUI/itembpBack.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
                ItembpBackController itemController = fxmlLoader.getController();
                          
                itemController.setData(listebonplan.get(i));
                            
                if (column == 3) {
                    column = 0;
                    row++;
                }
                //grid.add(anchorpane, column++, row);
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorpane, new Insets(10));
               grid.add(anchorpane, column, row);
                column++;
                //GridPane.setMargin(anchorpane, new Insets(10));
            }
       

        } catch (IOException ex) {
        }
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AjoutBonplanBack.fxml"));
        Parent root = loader.load();

        AjoutBonplanBackController cb = loader.getController();

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
        afficher();

    }

    @FXML
    private void refresh(ActionEvent event) {
            try {
            grid.getChildren().remove(0, listebonplan.size());
            listebonplan = bp.afficherBonplan();

            int column = 0;
            int row = 1;
            for (int i = 0; i < listebonplan.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/elbaldi/GUI/itembpBack.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
                ItembpBackController itemController = fxmlLoader.getController();
                itemController.setData(listebonplan.get(i));
                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorpane, column, row);
                column++;
                //GridPane.setMargin(anchorpane, new Insets(10));
            }
       

        } catch (IOException ex) {
        }
    }

   

   

    @FXML
    private void accueilAction(ActionEvent event) {
        commandeGUI.changeScene(event, "templateBack.fxml", "Acceuil");

    }

    @FXML
    private void profilAction(ActionEvent event) {
        commandeGUI.changeScene(event, "ProfileAdmin.fxml", "Profile");

    }

    @FXML
    private void categ(ActionEvent event) {
        commandeGUI.changeScene(event, "catgeorielistback.fxml", "Categories");

    }

    @FXML
    private void prodd(ActionEvent event) {
        commandeGUI.changeScene(event, "prodbacklist.fxml", "Produits");

    }

    @FXML
    private void commandesAction(ActionEvent event) {

        commandeGUI.changeScene(event, "commandeinterface.fxml", "commande ");
    }

    @FXML
    private void LivraisonAction(ActionEvent event) {
        commandeGUI.changeScene(event, "livraisoninterface.fxml", "livraison ");

    }

    @FXML
    private void BonpalnsAction(ActionEvent event) {
        commandeGUI.changeScene(event, "bonplanbacklist.fxml", "bonplans ");

    }

    @FXML
    private void QuizAction(ActionEvent event) {
        commandeGUI.changeScene(event, "front.fxml", "quiz ");

    }

    @FXML
    private void eventaction(ActionEvent event) {
        commandeGUI.changeScene(event, "AjouterEvenement.fxml", "évènements ");

    }

    @FXML
    private void participationaction(ActionEvent event) {
        commandeGUI.changeScene(event, "afficher participation.fxml", "participation ");

    }

    @FXML
    private void GestuserAction(ActionEvent event) {
            commandeGUI.changeScene(event, "MenuAdmin.fxml", "gestion utilisateurs ");

    }

    @FXML
    private void decoAction(ActionEvent event) {
        commandeGUI.changeScene(event, "Front1.fxml", "Visiteur ");
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
                    fxmlLoader.setLocation(getClass().getResource("/elbaldi/GUI/itembpFront.fxml"));
                    AnchorPane anchorpane = fxmlLoader.load();
                    ItembpFrontController itemController = fxmlLoader.getController();
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
    
     public void searchField(String titre) throws SQLException {
        try {
            listebonplan = bp.rechercher(titre);

            //listebonplan = bp.afficherBonplan();
            //grid.getChildren().remove(0, listebonplan.size());

            int column = 0;
            int row = 1;
            for (int i = 0; i < listebonplan.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/elbaldi/GUI/itembpFront.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
                ItembpFrontController itemController = fxmlLoader.getController();
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

    @FXML
    private void stataction(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("statbp.fxml"));
        Parent root = loader.load();

        StatbpController cb = loader.getController();

        Stage stage = new Stage();
        stage.setTitle("Tops 3 bons plans");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
    }

    
}