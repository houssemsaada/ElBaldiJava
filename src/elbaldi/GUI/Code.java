package elbaldi.GUI;

import com.twilio.type.PhoneNumber;
import elbaldi.services.UtilisateurCRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javafx.scene.Parent;

public class Code {

    public int x;
    public String y, z;
    public String username, pass, mesg, email, code;

    Stage stage = new Stage();


    UtilisateurCRUD userService = new UtilisateurCRUD();


    @FXML
    private Button btnid;

    @FXML
    private TextField mailid;
    @FXML
    private Button Back1;

    @FXML
    private void SendMail(ActionEvent event) throws AddressException, MessagingException, IOException, SQLException {

        if (mailid.getText().equals(this.code)) {
            System.out.println(mailid);
            Node node = (Node) event.getSource();
            stage = (Stage) node.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("resetpassword.fxml")));
            stage.setScene(scene);
            stage.show();

        } else {
            System.out.println("Code Invalide");
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
    public void setEmail(String email) {
        this.email = email;
    }

    public void setCode(String code) {
        this.code = code;
        System.out.println("====>" + code);
    }

    @FXML
    private void goBack(ActionEvent event) {
          // Redirection vers BrouillonController
    // Vous pouvez remplacer "Brouillon.fxml" par le nom de votre fichier FXML
    FXMLLoader loader = new FXMLLoader(getClass().getResource("forgetPassword.fxml"));
    try {
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) Back1.getScene().getWindow(); // backButton est le bouton de retour
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    }
}
