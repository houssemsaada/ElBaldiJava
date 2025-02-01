/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import elbaldi.models.Evenement;
import elbaldi.models.Participation;
import elbaldi.services.EvenementService;
import elbaldi.services.ParticipationService;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ModifierEvenementController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField description;
    @FXML
    private DatePicker mydatedebut;
    @FXML
    private DatePicker mydatefin;
    @FXML
    private Button updatebtn;
    @FXML
    private TextField id;
    @FXML
    private Label lbl1;
    String nomm1,awards1,description1;
      
private static int idd;
    
        EvenementService es = new EvenementService();
        Evenement events = new Evenement();
        Evenement ev = new Evenement();
    /**
     * Initializes the controller class.
     */
    
    
    /////////////////////////////////////////////////////////////////// GET ID LIVREUR FUNCTION //////////////////////////////////////////////////////////////
    
    
    
    
    
     public static int getIdd(Evenement l) {
        
        idd = l.getId_event();
          System.out.println(idd);
   
        return idd;
        
    }
    @FXML
    private Button annulUpd;
    @FXML
    private TextField award;
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
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ev= es.TrouverById(idd);
        System.out.println(ev);
        award.setText(ev.getAwards());
        description.setText(ev.getDescription());
        nom.setText(ev.getNom());
        mydatedebut.setValue(LocalDate.now());
        mydatefin.setValue(LocalDate.now());
       // mydatedebut.setValue( LocalDate.parse(ev.getDate_debut(), DateTimeFormatter.ISO_DATE) );
// e.setDate_debut(date_debut.format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
     

    }    

    
  
    @FXML
    private void modifierEvenemet(ActionEvent event) throws IOException {
        
        LocalDate date_debut,date_fin;
          date_debut = mydatedebut.getValue();
          date_fin = mydatefin.getValue();
          Evenement e = new Evenement();         
          e.setId_event(idd);
          e.setNom(nom.getText());
          e.setDate_debut(date_debut.format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
          e.setDate_fin(date_fin.format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
          e.setAwards(award.getText());
          e.setDescription(description.getText());
         ///////////////////////////////////////////////
         id.setText(String.valueOf(idd)); 
         
        
          
   
          
           try {
               
               es.modifier(e);
               Alert alert = new Alert(Alert.AlertType.INFORMATION);

              alert.setTitle("Boîte de dialogue d'information");

              alert.setHeaderText(null);

              alert.setContentText("L'événement a été modifié avec succès !");

              alert.show();
               //reset();
               Parent loader = FXMLLoader.load(getClass().getResource("AfficherEvenement.fxml"));
           nom.getScene().setRoot(loader);
               
           } catch (SQLException ex) {
            System.out.println(ex.getMessage());
      }
    
}

    @FXML
    private void AnnulerModification(ActionEvent event) throws IOException {
        
         Parent loader = FXMLLoader.load(getClass().getResource("AfficherEvenement.fxml"));
           nom.getScene().setRoot(loader);
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
