/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.commande;
import elbaldi.models.livraison;
import elbaldi.models.panier;
import elbaldi.services.CommandeCRUD;
import elbaldi.services.livraisonCRUD;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import static jdk.internal.org.objectweb.asm.commons.GeneratorAdapter.OR;

/**
 * FXML Controller class
 *
 * @author houss
 */
public class LivraisonajoutController implements Initializable {

    @FXML
    private Button addBtn;
    @FXML
    private Button exit1;
    @FXML
    private ComboBox<String> statusField;
    private TextField dateField;
    @FXML
    private TextField adresseField;
    @FXML
    private DatePicker datepicker;
    commande com;
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

    public void setCom(commande com) {
        this.com = com;
        adresseField.setText(com.getAdresse());

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        statusField.getItems().add("En attente");
        statusField.getItems().add("En expédition");
         statusField.getItems().add("Livrée");
    }

    @FXML
    private void addOnAction(ActionEvent event) {
        try {

            try {
                if (commandeGUI.isTextFieldEmpty( adresseField) || datepicker.getValue() == null || statusField.getValue() == null ) {
                    commandeGUI.AlertShow("Veuillez remplir tous les champs!", "Champs vides", Alert.AlertType.ERROR);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }

            int id_cmd = com.getId_cmd();
            System.out.println(id_cmd);
            String status = statusField.getValue();
//            try {
//                Date.valueOf(dateField.getText());
//            } catch (Exception e) {
//                commandeGUI.AlertShow("Please enter a valid date, use this format YYYY-MM-DD", "Invalid Date ", Alert.AlertType.ERROR);
//                return;
//            }
            Date date_liv = Date.valueOf(datepicker.getValue());
//   Date date_liv = Date.valueOf(dateField.getText());
            String adresse = adresseField.getText();
            commande c = new commande();
            c.setId_cmd(id_cmd);
            livraisonCRUD lv = new livraisonCRUD();

            livraison liv = new livraison(status, adresse, date_liv, c);
            try {
                lv.ajouterLivraison(liv);
            } catch (Exception ex) {
                commandeGUI.AlertShow("Identifiant de commande introuvable! ", "Commande", Alert.AlertType.ERROR);
            }

            commandeGUI.AlertShow(" Ajoutée ! ", "Livraison", Alert.AlertType.INFORMATION);
        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getCause();
        } finally {
            commandeGUI.clearTextFields( adresseField);
            statusField.setValue(null);
            datepicker.setValue(null);

        }

    }


    @FXML
    private void backonAction(ActionEvent event) {
        commandeGUI.changeScene(event, "commandeinterface.fxml", "commande interface");
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
