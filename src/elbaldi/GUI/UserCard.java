package elbaldi.GUI;

import elbaldi.models.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class UserCard {

    private Utilisateur utilisateur;

    private SupprimerCard supprimero;

    private Approve approve;

    @FXML
    private Button supprus;

    @FXML
    private Label user_email;

    @FXML
    private Label user_number;

    @FXML
    private Label user_role;

    @FXML
    private Label user_status;

    @FXML
    void approveUser(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Êtes-vous sûr de vouloir approuver cet utilisateur ?");
        alert.setHeaderText("Veuillez confirmer votre action");

        Optional<ButtonType> result = alert.showAndWait();

        // if the user confirms the deletion
        if (result.isPresent() && result.get() == ButtonType.OK) {
            approve.approve(utilisateur);
        }

    }

    @FXML
    void supprimer(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Êtes-vous sûr de vouloir désactiver cet utilisateur ?");
        alert.setHeaderText("Veuillez confirmer votre action");

        Optional<ButtonType> result = alert.showAndWait();

        // if the user confirms the deletion
        if (result.isPresent() && result.get() == ButtonType.OK) {
            supprimero.supprimer(utilisateur);
        }

    }

    public void setData(Utilisateur utilisateur1, SupprimerCard supp, Approve approve1) throws FileNotFoundException {
        this.utilisateur = utilisateur1;
        this.supprimero = supp;
        this.approve = approve1;
        user_email.setText(utilisateur1.getEmail());
        user_number.setText(String.valueOf(utilisateur1.getNumTel()));
        user_role.setText(String.valueOf(utilisateur1.getRole()));
        user_status.setText(String.valueOf(utilisateur1.getEtat()));

    }

}
