/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.commande;
import elbaldi.models.panier;
import elbaldi.services.CommandeCRUD;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author houss
 */
public class Commandeupdate2Controller implements Initializable {

    @FXML
    private Button exit;
    @FXML
    private Button exit1;
    @FXML
    private ComboBox<String> etatField;
    private TextField dateField;
    @FXML
    private Button update2Btn;
    @FXML
    private DatePicker datepicker;
    private commande comm;
    ArrayList<String> my_list = new ArrayList<>();
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

    /**/
    /**
     *
     * @param com
     */
    public void setCom(commande com) {
        comm = com;

        etatField.setValue(comm.getEtat());

        datepicker.setValue(comm.getDate_cmd().toLocalDate());

    }
//    
//    public void setIdField(String idField) {
//        this.idField.setText(idField);
//    }
//
//    public void setEtatField(String etatField) {
//        this.etatField.setText(etatField);
//    }
//
//    public void setDateField(String dateField) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy") ; 
//        LocalDate date =LocalDate.parse(dateField, formatter);
//        this.datepicker.setValue(date);
//    }
//
//    public void setPanierField(String panierField) {
//        this.panierField.setText(panierField);
//    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        etatField.getItems().add("En attente");
        etatField.getItems().add("Acceptée");

    }

    @FXML
    private void exitCommandeScene(ActionEvent event) {
    }

    @FXML
    private void backonAction(ActionEvent event) {
        commandeGUI.changeScene(event, "commandeinterface.fxml", "commande update");
    }

    @FXML
    private void update2OnAction(ActionEvent event) {
        try {

            CommandeCRUD cc = new CommandeCRUD();
            commande c = new commande();
            commande c2 = new commande();
            c2.setId_cmd(comm.getId_cmd());
            int id;
            Date date_com;
            String etat;
            int id_panier;

            id = comm.getId_cmd();
            c.setId_cmd(id);

            etat = etatField.getValue();
            c.setEtat(etat);

//            try {
//                Date.valueOf(dateField.getText());
//            } catch (Exception e) {
//                commandeGUI.AlertShow("Please enter a valid date, use this format YYYY-MM-DD", "Invalid Date ", Alert.AlertType.ERROR);
//                return;
//            }
            date_com = Date.valueOf(datepicker.getValue());

            //date_com = Date.valueOf(dateField.getText());
            c.setDate_cmd(date_com);

            id_panier = comm.getPan().getId_panier();
            panier pan = new panier(id_panier);
            c.setPan(pan);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Êtes-vous sûr de vouloir mettre à jour les données de la commande ?");
            alert.setHeaderText("Veuillez confirmer votre action");
            Optional<ButtonType> result = alert.showAndWait();
            // if the user confirms the update action
            if (result.isPresent() && result.get() == ButtonType.OK) {
                cc.modifierCommande(c2, c);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            // refreshTable();
            etatField.setValue(null);
            datepicker.setValue(null);
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
    }

}
