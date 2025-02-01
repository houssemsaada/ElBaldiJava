/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.GUI.ModifierEvenementController;
import elbaldi.models.Participation;
import elbaldi.models.Evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import elbaldi.services.EvenementService;
import static java.lang.Math.E;
import java.util.ArrayList;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.input.InputMethodEvent;
import elbaldi.services.ParticipationService;

/**
 * FXML Controller class
 *
 * @author Meddeb sofien
 */
public class EventcontroleurController implements Initializable {

    @FXML
    private Label nomLabel;
    @FXML
    private Label date_debut_label;
    @FXML
    private Label date_fin_label;
    @FXML
    private Button supprimerEventbtn;
    @FXML
    private Button modifierEvenbtn;

    EvenementService es = new EvenementService();
    @FXML
    private Label id_label;
    @FXML
    private ComboBox<Integer> id_client;
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
        date_fin_label.setText(E.getDescription());
        
      id_label.setText(String.valueOf(E.getId_event()));
      awardslbl.setText(E.getAwards());
         System.out.println(E.getAwards());
      e=E;
      lblclientt.setText(String.valueOf(ps.getidclientt()));
//       ObservableList<Integer> list1 = FXCollections.observableArrayList();        
//        list1 = ps.getidclient();
//        id_client.setItems(list1);
       
    }
//     public int getIdEvenement(Evenement E){
//         
//         return E.getId_event();
//     }

    @FXML
    private void supprimerEvenement(ActionEvent event ) throws IOException {
       
   Evenement e =new Evenement();
    e.setId_event(Integer.parseInt(id_label.getText()));
        try {
            
              Alert alert = new Alert(Alert.AlertType.WARNING);

              alert.setTitle("Boîte de dialogue de confirmation");

              alert.setHeaderText(null);

              alert.setContentText("Voulez-vous supprimer cet événement ?");
              ButtonType cancelBtn = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
             alert.getButtonTypes().setAll(cancelBtn, ButtonType.OK);
              Optional<ButtonType> result = alert.showAndWait();
           
           if (result.isPresent() && result.get() == ButtonType.OK) {
    // User clicked OK 
           es.supprimer(e);
} else {
    // User clicked cancel or closed the dialog
               System.out.println("Suppression annulée");
    
}
            Parent loader = FXMLLoader.load(getClass().getResource("afficherEvenement.fxml"));
            nomLabel.getScene().setRoot(loader);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void modifierEvenement(ActionEvent event) throws IOException {
        
      
    
            
            
            ModifierEvenementController.getIdd(e);
            
             try {
          Parent loader = FXMLLoader.load(getClass().getResource("ModifierEvenement.fxml"));
         // Parent root = loader.load();
         
         
                 
                         
                 

             nomLabel.getScene().setRoot(loader);
        
            
        } catch (NullPointerException ex) {
            System.out.println("error" + ex.getMessage());
        }
           
            
    
        
    }

//     public String setnomm(){
//         return nomLabel.getText();
//        
//    }
    private void ParticiperEvent(ActionEvent event) throws IOException {
        

          Participation p = new Participation();
          p.setId_event(e.getId_event());
          //p.setId_user(id_client.getValue());
          p.setId_user(ps.getidclientt());
          
            List<Integer> l = new ArrayList<Integer>();
      l= ps.verif_existance(ps.getidclientt());
        System.out.println(l);
        System.out.println(ps.getidev());

        try {
//            System.out.println(ps.getidclientt());
//            System.out.println(ps.getidev());

     
    
            ps.ajouter(p,e);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

              alert.setTitle("Boîte de dialogue d'information");

              alert.setHeaderText(null);

              alert.setContentText("L'événement a été inséré avec succès !");

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
            
            System.out.println(ex.getMessage());
        }
               

       
        
    
    }

    
}
