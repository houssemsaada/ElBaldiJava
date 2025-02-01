/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.Evenement;
import elbaldi.models.Participation;
import elbaldi.models.Utilisateur;
import elbaldi.services.MailerService;
import elbaldi.services.ParticipationService;
import elbaldi.services.UserSession;
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
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class EventfrontController implements Initializable {

    @FXML
    private Label nomLabel;
    @FXML
    private Label date_debut_label;
    private Label date_fin_label;
    @FXML
    private Button modifierEvenbtn;
    @FXML
    private Label id_label;
    @FXML
    private Label lblclientt;
    @FXML
    private Label awardslbl;
    @FXML
    private Label desclabel;
  UserSession userSession = new UserSession();
    Utilisateur u = userSession.getUser();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     Evenement e = new Evenement();
    ParticipationService ps = new ParticipationService();
    
    // ********************
    int a = ps.getidclientt();
    
    
     public void setEvenement(Evenement  E) {
     
       nomLabel.setText(E.getNom());
        date_debut_label.setText(E.getDate_debut() + " \n " + E.getDate_fin());
        desclabel.setText(E.getDescription());
        
      id_label.setText(String.valueOf(E.getId_event()));
      awardslbl.setText(E.getAwards());
      e=E;
      lblclientt.setText(String.valueOf(ps.getidclientt()));
//       ObservableList<Integer> list1 = FXCollections.observableArrayList();        
//        list1 = ps.getidclient();
//        id_client.setItems(list1);
       
    }

    @FXML
    private void ajouterparticipation(ActionEvent event) {
        try {
            Participation p = new Participation();
            p.setId_event(e.getId_event());
            //p.setId_user(id_client.getValue());
            p.setId_user(u.getId_user());
            
            List<Integer> l = new ArrayList<Integer>();
            l= ps.verif_existance(ps.getidclientt());
            System.out.println(l);
            System.out.println(ps.getidev());
            
            //            System.out.println(ps.getidclientt());
//            System.out.println(ps.getidev());


            System.out.println(e.getDate_debut());
ps.ajouter(p,e);
            MailerService ms = new MailerService();
            ms.sendParticipationMail(p, e, u);
Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Information Dialog");
alert.setHeaderText(null);
alert.setContentText("participation insérée avec succés!");
alert.show();




//                Alert alert = new Alert(Alert.AlertType.ERROR);
//
//              alert.setTitle("Information Dialog");
//
//              alert.setHeaderText(null);
//
//              alert.setContentText("Participation deja Ajouter!");
//
//              alert.show();
        } catch (SQLException ex) {
            Logger.getLogger(EventfrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
}
