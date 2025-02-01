/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.Evenement;
import elbaldi.services.ParticipationService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Eventfront1Controller implements Initializable {

    @FXML
    private Label nomLabel;
    @FXML
    private Label date_debut_label;
    @FXML
    private Label desclabel;
    @FXML
    private Label id_label;
    @FXML
    private Label lblclientt;
    @FXML
    private Label awardslbl;

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
    
}
