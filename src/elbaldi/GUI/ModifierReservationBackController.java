/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import static elbaldi.GUI.ModifierBonplanBackController.bnp;
import elbaldi.models.Reservation;
import elbaldi.models.Utilisateur;
import elbaldi.models.bonplan;
import elbaldi.services.BonplanCrud;
import elbaldi.services.ReservationCrud;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ModifierReservationBackController implements Initializable {
    
    @FXML
    private Button modifier;
    @FXML
    private Button annuler;
    @FXML
    private TextField nombreR;
    @FXML
    private TextField dateR;
    @FXML
    private TextField statutR;
    @FXML
    private TextField idplanTF;
    @FXML
    private TextField IDuserTF;
    
    public static Reservation rsv;
   
    /**
     * Initializes the controller class.
     
     */
    
    public void setId_reservation(Reservation r) throws IOException {
        
       rsv=r;
       this.nombreR.setText(r.getNombre_personnes()+"");
        this.dateR.setText(r.getDate_reservation()+"");
        this.statutR.setText(r.getStatut_reservation());
        this.idplanTF.setText(r.getBonplan2().getId_bonplan()+"");
        this.IDuserTF.setText(r.getUser2().getid_user()+"");
        
        
        
        
        
       
       
    }    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    

    @FXML
    private void modifierRsv(ActionEvent event) {
         ReservationCrud rs= new ReservationCrud();
         Reservation r= new Reservation();
         r.setId_reservation(rsv.getId_reservation());
         r.setNombre_personnes(Integer.parseInt(nombreR.getText()));
        try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        r.setDate_reservation(dateFormat.parse(dateR.getText()));
    } catch (ParseException e) {
        // Gérer l'exception si la saisie n'est pas une date valide
    }
         r.setStatut_reservation(statutR.getText());
         
         rs.modifierReservation(r);
     
       r.setStatut_reservation(statutR.getText());
         rs.modifierReservation(r); 
          
         
         
         
         
         
         
         
         
                Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
                alert0.setTitle("information Dialog");
                alert0.setHeaderText(null);
                alert0.setContentText("Votre modification est enregistrée avec succes ");
                alert0.show();
                ((Node) event.getSource()).getScene().getWindow().hide();
        
        
    }
        



    }
   
    

       
    
    
    
