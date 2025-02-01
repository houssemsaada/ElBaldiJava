/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.Reservation;
import elbaldi.models.Utilisateur;
import elbaldi.models.bonplan;
import elbaldi.services.BonplanCrud;
import elbaldi.services.ReservationCrud;
import elbaldi.services.UserSession;
import elbaldi.services.UtilisateurCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjouReservationFront1Controller implements Initializable {

    @FXML
    private Button confirmerBTN;
    @FXML
    private Button annulerBTN;
    @FXML
    private TextField nombreR;
    @FXML
    private DatePicker dateR;
    private bonplan bonplan1;
    BonplanCrud bp= new BonplanCrud();
        UserSession user = new UserSession();
        Utilisateur u = user.getUser();
    public void setBonplan1(bonplan bonplan1) {
        this.bonplan1 = bonplan1;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void confirmerReservation(ActionEvent event) {
        try {
            System.out.println(u);
            try {
                if (nombreR.getText().isEmpty() || dateR.getValue() == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                      alert.setTitle("Echec de l'ajout");
                      alert.setHeaderText(null);
                      alert.setContentText("Attention ! Veuillez saisir toutes les données requises.");
                      alert.showAndWait();
                   return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
           
            int nombre_personnes = Integer.parseInt(nombreR.getText());
             LocalDate date_selectionnee = dateR.getValue();
             Date date_reservation = java.sql.Date.valueOf(date_selectionnee);
             
             ReservationCrud R= new ReservationCrud();
             UtilisateurCRUD uti = new UtilisateurCRUD();

           Reservation res = new Reservation(nombre_personnes, date_reservation,"En attente",bonplan1,u);
            System.out.println(res);
             R.ajouterReservation(res);
               
            Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
            alert0.setTitle("Boîte de dialogue d'information");
            alert0.setHeaderText(null);
            alert0.setContentText("Ajoutée avec succés ");
            alert0.show();
                        ((Node) event.getSource()).getScene().getWindow().hide();

        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getCause(); }
    }

    @FXML
    private void annulerReservation(ActionEvent event) throws IOException {
        nombreR.clear();
         dateR.setValue(null);
       
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BpDetailsFront.fxml"));
        
        Parent root = loader.load();
        
    }

    @FXML
    private void prefixe(MouseEvent event) {
    }

   
}
