package elbaldi.GUI;

import elbaldi.models.SharedData;
import elbaldi.models.Utilisateur;
import elbaldi.services.UtilisateurCRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class ResetPassword {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button btnid;

    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField newPassword1;
    private Utilisateur u;
    @FXML
    private Button Back2;

    @FXML
    void ResetPassword(ActionEvent event) throws IOException {

        String newMdp = newPassword.getText();
        System.out.println(newMdp);
        String confirmMdp = newPassword1.getText();
        if (newMdp.equals(confirmMdp)) {
            UtilisateurCRUD us = new UtilisateurCRUD();
            Utilisateur utilisateur = us.GetUserByMailE(SharedData.data);
            utilisateur.setMdp(newMdp);
            us.UpdateUserPassword(utilisateur);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Login.fxml"));
            Parent root = loader.load();
            btnid.getScene().setRoot(root);

        }
    }

    private void mini(MouseEvent event) {
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setIconified(true);
    }

    private void max(MouseEvent event) {
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setFullScreen(true);
    }

    private void close(javafx.scene.input.MouseEvent event) {
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.close();

    }

    @FXML
    private void goBack(ActionEvent event) {
          // Redirection vers BrouillonController
    // Vous pouvez remplacer "Brouillon.fxml" par le nom de votre fichier FXML
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Code.fxml"));
    try {
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) Back2.getScene().getWindow(); // backButton est le bouton de retour
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    }

}
