package elbaldi.GUI;

import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.activation.DataSource;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class ForgetPassword {

    private Stage stage;
    private Scene scene;
    private Parent root;
    public static final String ACCOUNT_SID = "ACb6357a110c9e1974aa0e9d7a0acc0bcc";
    public static final String AUTH_TOKEN = "b0367929d3d77a8135975295c48543da";

    private DataSource cnx;
    public ResultSet rs;
    public String y, z;
    public String username, pass, mesg;
    UtilisateurCRUD userService = new UtilisateurCRUD();
    //UserSession userSession;

    @FXML
    private Button btnid;

    @FXML
    private TextField mailid;
    @FXML
    private Button Back;

    @FXML
    void SendMail(ActionEvent event) throws IOException {
        if (mailid.getText().isEmpty()) {
            mailid.setText("remarque : email vide");
        } else if (!mailid.getText().matches("[a-zA-Z0-9\\.]+@[a-zA-Z0-9\\-\\_\\.]+\\.[a-zA-Z0-9]{2}")) {
            mailid.setText("remarque : email non valide");
        } else {

            userService.GetuserBytel(mailid.getText()).toString();
            y = getSaltString();
            z = mailid.getText();
            SharedData.data = z ;
            try {
                String user = userService.sendMail(mailid.getText());
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            mesg = "votre code est : " + y;

            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            com.twilio.rest.api.v2010.account.Message messages = com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber("+216" + userService.GetuserBytel(mailid.getText()).toString()),
                    new PhoneNumber("+13157918686"), y).create();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Code.fxml"));
            Parent root = loader.load();
            Code ccc = loader.getController();
            Utilisateur utilisateur = new Utilisateur();
            ccc.setEmail(z);
            ccc.setCode(y);
            mailid.getScene().setRoot(root);

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

    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    private void close(javafx.scene.input.MouseEvent event) {
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.close();

    }

    @FXML
    private void goBack(ActionEvent event) {
          // Redirection vers BrouillonController
    // Vous pouvez remplacer "Brouillon.fxml" par le nom de votre fichier FXML
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
    try {
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) Back.getScene().getWindow(); // backButton est le bouton de retour
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    }

}
