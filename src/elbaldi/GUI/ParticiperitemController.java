/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;
import elbaldi.models.*;
import elbaldi.services.EvenementService;
import elbaldi.services.ParticipationService;
import elbaldi.services.UtilisateurCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ParticiperitemController implements Initializable {

    @FXML
    private Label nomLabel;
    @FXML
    private Button supprimerEventbtn;
    @FXML
    private Label id_label;
    @FXML
    private ComboBox<?> id_client;
    @FXML
    private Label lblclientt;
    @FXML
    private Label eventlabel;
    @FXML
    private Label idlabel;
    @FXML
    private Label datelabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    Participation p = new Participation();

    public void setP(Participation p) throws SQLException {
        this.p = p;
        Utilisateur u = new Utilisateur();
//        u.setid_user(p.getId_user());
        UtilisateurCRUD uc= new UtilisateurCRUD();
        u=uc.getUserByID(p.getId_user());
        
        EvenementService ps = new EvenementService();
        Evenement e = ps.TrouverById(p.getId_event());
        nomLabel.setText(u.getNom() +" "+ u.getPrenom());
        eventlabel.setText(e.getNom());
        idlabel.setText(p.getId_participation()+"");
        datelabel.setText(e.getDate_debut());
    }
    
    @FXML
    private void supprimerEvenement(ActionEvent event) throws IOException {
        
   Participation p = new Participation();
        System.out.println(idlabel.getText());
    p.setId_participation(Integer.parseInt(idlabel.getText()));
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
               ParticipationService ps = new ParticipationService();
           ps.supprimer(p);
} else {
    // User clicked cancel or closed the dialog
               System.out.println("Suppression annulée");
    
}
            Parent loader = FXMLLoader.load(getClass().getResource("afficher participation.fxml"));
            nomLabel.getScene().setRoot(loader);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }
    

