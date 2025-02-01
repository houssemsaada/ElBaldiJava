package elbaldi.GUI;

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

public class ProfileUser implements Initializable {

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

    void image_admin(MouseEvent event) {

    }
UserSession session = new UserSession();
    @FXML
    void updtahost(ActionEvent event) {
        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(adminpagedate.getValue());
        UtilisateurCRUD u = new UtilisateurCRUD();
        
        int id = session.getUser().getid_user();
        String email = session.getUser().getEmail();

        String name = adminpagename.getText();
        String lastname = adminpagelastname.getText();
        int tel = Integer.parseInt(adminpagetel.getText());
        String ville = adminpageaddress.getText();
        Utilisateur user = new Utilisateur(Integer.parseInt(String.valueOf(id)), name, lastname, email, gettedDatePickerDate, tel, ville);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Êtes-vous sûr de vouloir modifier vos informations ?");
        alert.setHeaderText("Veuillez confirmer votre action");

        Optional<ButtonType> result = alert.showAndWait();

        // if the user confirms the deletion
        if (result.isPresent() && result.get() == ButtonType.OK) {
            u.UpdateUser(user);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UtilisateurCRUD userService = new UtilisateurCRUD();
        LocalDate ddn = session.getUser().getDateNaissance().toLocalDate();
        adminpagename.setText(session.getUser().getNom());
        adminpagelastname.setText(session.getUser().getPrenom());
        adminpagetel.setText(String.valueOf(session.getUser().getNumTel()));
        adminpageaddress.setText(session.getUser().getVille());
        adminpagedate.setValue(ddn);
    }
}
