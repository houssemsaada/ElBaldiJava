/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.*;
import elbaldi.services.livraisonCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author houss
 */
public class LivraisoninterfaceController implements Initializable {

    @FXML
    private Button updBtn;
    @FXML
    private Button delBtn;

    @FXML
    private Button loginButton12;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button refreshbtn;
    ObservableList<livraison> livraisonObservableList = FXCollections.observableArrayList();
    @FXML
    private ListView<livraison> ListView;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        livraisonCRUD lv = new livraisonCRUD();
        livraisonObservableList = FXCollections.observableList(lv.sortlivraisonByDate());

           ListView.setCellFactory(lva -> new LivraisonListCell());
        ListView.setItems(livraisonObservableList);

        //search bar 
        FilteredList<livraison> filteredData = new FilteredList<>(livraisonObservableList, b -> true);
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(livraison -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKeywords = newValue.toLowerCase();

                if (livraison.getStatus_livraison().toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                if ((livraison.getDate_livraison() + "").toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                if (livraison.getC1().getPan().getU1().getNom().toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                if (livraison.getC1().getPan().getU1().getPrenom().toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                if (String.valueOf(livraison.getC1().getPan().getU1().getid_user()).toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                if (String.valueOf(livraison.getC1().getPan().getU1().getNumTel()).toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                if (livraison.getC1().getPan().getU1().getEmail().toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                if ((livraison.getC1().getDate_cmd() + "").toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                if ((livraison.getC1().getId_cmd() + "").toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                return (livraison.getId_livraison() + "").toLowerCase().contains(searchKeywords);
            });
        });
        // wrap the FilteredList in a SortedList.
//        SortedList<livraison> sortedList = new SortedList<>(filteredData);
//        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        ListView.setItems(filteredData);
    }

     void refreshTable() {
             livraisonCRUD lv = new livraisonCRUD();
        livraisonObservableList = FXCollections.observableList(lv.sortlivraisonByDate());

           ListView.setCellFactory(lva -> new LivraisonListCell());
        ListView.setItems(livraisonObservableList);
    }

    private void addBtnOnAction(ActionEvent event) {
        commandeGUI.changeScene(event, "livraisonajout.fxml", "Ajouter livraison");
    }

    @FXML
    private void updateBtnOnAction(ActionEvent event) {
try {
            if (ListView.getSelectionModel().getSelectedItem() == null) {
                commandeGUI.AlertShow("Veuillez sélectionner une commande à mettre à jour", "Aucune commande sélectionnée", Alert.AlertType.ERROR);
                return;
            }
        } catch (Exception ewww) {
            ewww.printStackTrace();
            ewww.getCause();
        }

        livraison liv = ListView.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("livraisonupdate2.fxml"));
        try {
            Parent root = loader.load();
            Livraisonupdate2Controller lc = loader.getController();
            lc.setLivrai(liv);
            lc.setAdresseField(liv.getAdresse_livraison());
           // lc.setStatusField(liv.getStatus_livraison());
            lc.setDateField(liv.getDate_livraison());
            
            searchTextField.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }    }

    @FXML
    private void deleteBtnOnAction(ActionEvent event) {
try {
            if (ListView.getSelectionModel().getSelectedItem() == null) {
                commandeGUI.AlertShow("Veuillez sélectionner une commande à supprimer", "Aucune commande sélectionnée", Alert.AlertType.ERROR);
                return;
            }

        } catch (Exception ewww) {
            ewww.printStackTrace();
            ewww.getCause();
        }
        try {
            livraison liv = new livraison();
            livraisonCRUD lv= new livraisonCRUD();
          
            liv.setId_livraison(ListView.getSelectionModel().getSelectedItem().getId_livraison());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Êtes-vous sûr(e) de vouloir supprimer cette commande ?");
            alert.setHeaderText("Veuillez confirmer votre action");

            Optional<ButtonType> result = alert.showAndWait();

            // if the user confirms the deletion
            if (result.isPresent() && result.get() == ButtonType.OK) {
                lv.supprimerLivraison(liv);
               refreshTable();           }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }    }

    @FXML
    private void exitCommandeScene(ActionEvent event) {
    }

    @FXML
    private void refreshOnAction(ActionEvent event) {
        refreshTable();

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
        commandeGUI.changeScene(event, "AjouterEvenement.fxml", "evenemets ");

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

}
