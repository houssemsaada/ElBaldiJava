/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.Evenement;
import elbaldi.models.Participation;
import elbaldi.services.EvenementService;
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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AjouterEvenementController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField awards;
    @FXML
    private TextField description;
    @FXML
    private DatePicker mydatedebut;
    @FXML
    private DatePicker mydatefin;
    @FXML
    private TextField id;
    Evenement events = new Evenement();
    Evenement ev = new Evenement();
    EvenementService es = new EvenementService();
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
// TODO
        events = es.recherche(idd);
        //setData();
         mydatedebut.setValue(LocalDate.now());
            mydatefin.setValue(LocalDate.now());
            }    

    @FXML
    private void AfficheEvenement(ActionEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("afficherEvenement.fxml"));
            nom.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ajouterEvenement(ActionEvent event) {
         
        
 try {
          LocalDate date_debut,date_fin;
           
          date_debut = mydatedebut.getValue();
          date_fin = mydatefin.getValue();
          Evenement e = new Evenement();
          e.setNom(nom.getText());
          e.setDate_debut(date_debut.format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
          e.setDate_fin(date_fin.format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
          e.setAwards(awards.getText());
          e.setDescription(description.getText());
          if(nom.getText().isEmpty()   || awards.getText().isEmpty() || description.getText().isEmpty() || e.getDate_debut().isEmpty() || e.getDate_fin()==null)
            {
                
                //showMessageDialog(null, "Vérifier Vos Champs" ); 
                Alert alert = new Alert(Alert.AlertType.ERROR);

              alert.setTitle("Boîte de dialogue d'information");

              alert.setHeaderText(null);

              alert.setContentText("Vérifiez que tous les champs nécessaires ont été remplis correctement");

              alert.show();
            }
          
          else
          {
              
              Participation p=new Participation();
          
               es.ajouter(e,p);
               //showMessageDialog(null, "Evenement Ajouté Avec Succès" );  
               Alert alert = new Alert(Alert.AlertType.INFORMATION);

              alert.setTitle("Boîte de dialogue d'information");

              alert.setHeaderText(null);

              alert.setContentText("Evènement insérée avec succés!");

              alert.show();
               reset();
          }
           } catch (SQLException ex) {
                
            System.out.println(ex.getMessage());
      }
          
    }
     private void reset() {
//                   Evenement e = new Evenement();
//    LocalDate date ;
//    date=LocalDate.of(00, 00, 00);
        nom.setText("");     
        awards.setText("");
        description.setText("");
       // mydatedebut.setValue(date);
        
        
    } 
     public  void setData(){
         events = es.recherche(idd);
          int a =idd;
        String b= events.getNom();
        String c= events.getAwards();
        String d = events.getDate_debut();
         nom.setText(b);     
        awards.setText(c);
        description.setText(d);
         
     }
     

     
     
     
     private static int idd;
      
    public static int getIdd(int id) {
        idd = id;
        return idd;
        
        
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
