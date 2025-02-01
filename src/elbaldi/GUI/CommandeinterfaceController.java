/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.Utilisateur;
import elbaldi.models.commande;
import elbaldi.models.panier;
import elbaldi.services.CommandeCRUD;
import elbaldi.services.UtilisateurCRUD;
import elbaldi.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.geometry.Pos;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author houss
 */
public class CommandeinterfaceController implements Initializable {

    private TextField userField;
    @FXML
    private Button updBtn;
    @FXML
    private Button delBtn;

    @FXML
    private Button loginButton12;
    @FXML
    private TextField searchTextField;
    Statement ste;
    Connection conn = MyConnection.getInstance().getConn();
    ObservableList<commande> commandeObservableList = FXCollections.observableArrayList();

    @FXML
    private Button refreshbtn;
    @FXML
    private ListView<commande> ListView;
    @FXML
    private Button addBtn1;
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

        CommandeCRUD cc = new CommandeCRUD();

        commandeObservableList = FXCollections.observableList(cc.sortCommandesByDate());

        ListView.setCellFactory(lv -> new CommandeListCell());
        ListView.setItems(commandeObservableList);

        //search bar 
        FilteredList<commande> filteredData = new FilteredList<>(commandeObservableList, b -> true);
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(commande -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKeywords = newValue.toLowerCase();

                if (commande.getEtat().toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                if ((commande.getDate_cmd() + "").toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                if (commande.getPan().getU1().getNom().toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                if (commande.getPan().getU1().getPrenom().toLowerCase().contains(searchKeywords)) {
                    return true;
                }
//                if (String.valueOf(commande.getPan().getU1().getid_user()).toLowerCase().contains(searchKeywords)) {
//                    return true;
//                }
                if (String.valueOf(commande.getPan().getU1().getNumTel()).toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                if (commande.getPan().getU1().getEmail().toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                return (commande.getId_cmd() + "").toLowerCase().contains(searchKeywords);
            });
        });
        // wrap the FilteredList in a SortedList.
        SortedList<commande> sortedList = new SortedList<>(filteredData);
        //sortedList.comparatorProperty().bind(ListView.comparatorProperty());
        ListView.setItems(filteredData);
        // TODO
    }
    // method to refresh the table view to show the updated data after any action

    void refreshTable() {
        CommandeCRUD cc = new CommandeCRUD();

        commandeObservableList = FXCollections.observableList(cc.sortCommandesByDate());

        ListView.setCellFactory(lv -> new CommandeListCell());
        ListView.setItems(commandeObservableList);
    }

    private void addBtnOnAction(ActionEvent event) {
        commandeGUI.changeScene(event, "commandeajout.fxml", "Ajouter commande");
    }

    @FXML
    private void updateBtnOnAction(ActionEvent event) {
try {
            if (ListView.getSelectionModel().getSelectedItem() == null) {
                commandeGUI.AlertShow("Veuillez sélectionner une commande à mettre à jour !", "Aucune commande sélectionnée", Alert.AlertType.ERROR);
                return;
            }
        } catch (Exception ewww) {
            ewww.printStackTrace();
            ewww.getCause();
        }

//        commande c = ListView.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("commandeupdate2.fxml"));
        try {
            Parent root = loader.load();
           Commandeupdate2Controller dc = loader.getController();
//            dc.setIdField(c.getId_cmd()+"");
//            dc.setEtatField(c.getEtat());
//            dc.setDateField(c.getDate_cmd()+"");
//            dc.setPanierField(c.getPan().getId_panier()+"");
            searchTextField.getScene().setRoot(root);
           dc.setCom(ListView.getSelectionModel().getSelectedItem());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void deleteBtnOnAction(ActionEvent event) {
        try {
            if (ListView.getSelectionModel().getSelectedItem() == null) {
                commandeGUI.AlertShow("Veuillez sélectionner une commande à supprimer!", "Aucune commande sélectionnée", Alert.AlertType.ERROR);
                return;
            }

        } catch (Exception ewww) {
            ewww.printStackTrace();
            ewww.getCause();
        }
        try {
            commande c = new commande();
            CommandeCRUD cc = new CommandeCRUD();
            c.setId_cmd(ListView.getSelectionModel().getSelectedItem().getId_cmd());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Êtes-vous sûr de vouloir supprimer la commande ?");
            alert.setHeaderText("Veuillez confirmer votre action");

            Optional<ButtonType> result = alert.showAndWait();

            // if the user confirms the deletion
            if (result.isPresent() && result.get() == ButtonType.OK) {
                cc.supprimerCommande(c);
                refreshTable();
            }

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
    private void addlivraisonOnAction(ActionEvent event) {
       try {
            if (ListView.getSelectionModel().getSelectedItem() == null) {
                commandeGUI.AlertShow("Veuillez sélectionner une commande !", "Aucune commande sélectionnée", Alert.AlertType.ERROR);
                return;
            }
        } catch (Exception ewww) {
            ewww.printStackTrace();
            ewww.getCause();
        }

//        commande c = ListView.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("livraisonajout.fxml"));
        try {
            Parent root = loader.load();
           LivraisonajoutController dc = loader.getController();
//            dc.setIdField(c.getId_cmd()+"");
//            dc.setEtatField(c.getEtat());
//            dc.setDateField(c.getDate_cmd()+"");
//            dc.setPanierField(c.getPan().getId_panier()+"");
            searchTextField.getScene().setRoot(root);
           dc.setCom(ListView.getSelectionModel().getSelectedItem());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
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

}

