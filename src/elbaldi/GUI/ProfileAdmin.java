package elbaldi.GUI;

import elbaldi.models.Role;
import elbaldi.models.Utilisateur;
import elbaldi.services.UserSession;
import elbaldi.services.UtilisateurCRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ProfileAdmin implements Initializable {


    @FXML
    private TextField adminpageaddress;

    @FXML
    private DatePicker adminpagedate;

    @FXML
    private TextField adminpagelastname;

    @FXML
    private TextField adminpagename;

    @FXML
    private TextField adminpagetel;

    @FXML
    private Pane pnlOverview;

    @FXML
    private Button updateadmin;
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

    void image_admin(MouseEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserSession userSession = new UserSession();
        
        UtilisateurCRUD userService = new UtilisateurCRUD();
        LocalDate ddn = userSession.getUser().getDateNaissance().toLocalDate();
        adminpagename.setText(userSession.getUser().getNom());
        adminpagelastname.setText(userSession.getUser().getPrenom());
        adminpagetel.setText(String.valueOf(userSession.getUser().getNumTel()));
        adminpageaddress.setText(userSession.getUser().getVille());
        adminpagedate.setValue(ddn);
    }
     @FXML
    void updtahost(ActionEvent event) {
        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(adminpagedate.getValue());
        UtilisateurCRUD u = new UtilisateurCRUD();
        UserSession session = new UserSession();
        int id = session.getUser().getid_user();
        String email = session.getUser().getEmail();

        String name = adminpagename.getText();
        String lastname = adminpagelastname.getText();
        int tel = Integer.parseInt(adminpagetel.getText());
        String ville = adminpageaddress.getText();
        Utilisateur user = new Utilisateur(id,name, lastname,email, gettedDatePickerDate, tel, ville );
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Êtes-vous sûr de vouloir modifier vos informations ?");
        alert.setHeaderText("Veuillez confirmer votre action");

        Optional<ButtonType> result = alert.showAndWait();

        // if the user confirms the deletion
        if (result.isPresent() && result.get() == ButtonType.OK) {
            u.UpdateUser(user);
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
