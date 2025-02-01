/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import static com.barcodelib.barcode.a.i.pc;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;

import elbaldi.models.Etat;
import elbaldi.models.Role;
import elbaldi.models.Utilisateur;
import elbaldi.models.panier;
import elbaldi.services.UtilisateurCRUD;
import elbaldi.services.panierCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static sun.security.jgss.GSSUtil.login;

/**
 * FXML Controller class
 *
 * @author mEtrOpOliS
 */
public class InscriptionController implements Initializable {

    private String[] roles = {
            "client",
            
            "gerant"
    };

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private DatePicker daten;

    @FXML
    private TextField emailtext;

    @FXML
    private TextField nomtext;

    @FXML
    private TextField numteltext;

    @FXML
    private PasswordField passwordtxt;

    @FXML
    private TextField prenomtext;

    @FXML
    private Button signup;


    @FXML
    private TextField villetext;

panierCRUD pc=new panierCRUD();
    @FXML
    private ChoiceBox<String> role;
    @FXML
    private PasswordField passwordtxt1;

    @FXML
    void signup(ActionEvent event) {
         String confirmMdp = passwordtxt1.getText();
         String Mdp = passwordtxt.getText();

        Role rolee = Role.valueOf(role.getValue());
        if (event.getSource() == signup) {
            
            if (validatenGuest_Name(nomtext) & validatenGuest_Name(prenomtext) & validatenGuest_Email(emailtext) & validatenGuest_Password(passwordtxt) &validateTel(numteltext) & TestDate() &validatenGuest_Password(passwordtxt1)) {
                       java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(daten.getValue());
                       if(confirmMdp.equals(Mdp)){
                             if(rolee.equals(Role.client)){
                    user = new Utilisateur(nomtext.getText(), prenomtext.getText(),
                            emailtext.getText(),gettedDatePickerDate,Integer.parseInt(numteltext.getText()), villetext.getText(), passwordtxt.getText(),
                            rolee, Etat.accepted);
                    cRUD.ajouterUtlisateur(user);
                    panier p = new panier(cRUD.GetUserByMailSession(emailtext.getText())); 
                    pc.ajouterPanier(p);
                }else {
                    user = new Utilisateur(nomtext.getText(), prenomtext.getText(),
                            emailtext.getText(),gettedDatePickerDate,Integer.parseInt(numteltext.getText()), villetext.getText(), passwordtxt.getText(),
                            rolee, Etat.pending);
                    cRUD.ajouterUtlisateur(user);
                }
                       }
              


            }
        }
    }

     UtilisateurCRUD cRUD = new UtilisateurCRUD();
    private Button btnValider;
    private Utilisateur user;

    /**
     * Initializes the controller class.
    
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        role.getItems().addAll(roles);
    }

    @FXML
    private void mini(MouseEvent event) {
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setIconified(true);
    }

    @FXML
    private void max(MouseEvent event) {
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setFullScreen(true);
    }

    @FXML
    private void close(javafx.scene.input.MouseEvent event) {
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.close();

    }

    @FXML
    private void redirect_login(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();
        stage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
        stage.setScene(scene);
        stage.show();
    }


    
    private boolean validatenGuest_Name(TextField name) {
        Pattern p = Pattern.compile("[a-zA-Z_]+");
        Matcher m = p.matcher(name.getText());
        if ((name.getText().length() == 0)
                || (!m.find() && m.group().equals(name.getText()))) {
            new animatefx.animation.Shake(name).play();
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            name.setEffect(in);
            return false;
        } else {
            name.setEffect(null);
            return true;
        }
    }
    
    private boolean TestDate() {
        java.sql.Date birthday = java.sql.Date.valueOf(daten.getValue());
        long millis = System.currentTimeMillis();
        java.sql.Date now = new java.sql.Date(millis);
        
        if ( birthday.compareTo(now) < 0) {
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#52FF00"));

            daten.setEffect(in);
            return true;

        } else {
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));

            daten.setEffect(in);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Syntaxe Email");
            alert.setHeaderText(null);
            alert.setContentText("S'il vous plait saisir une date valide");
            alert.showAndWait();

            return false;
        }
    }

    private boolean validateTel(TextField tel) {
        Pattern p = Pattern.compile("^\\d{8}$");
        Matcher m = p.matcher(tel.getText());

        if ((tel.getText().length() == 8)
                || (m.find() && m.group().equals(tel.getText()))) {
            tel.setEffect(null);
            return true;
        } else {
            new animatefx.animation.Shake(tel).play();
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            tel.setEffect(in);
            return false;
        }
    }

    private boolean validatenGuest_Email(TextField email) {

        Pattern p = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher m = p.matcher(email.getText());
        if (((email.getText().length() > 8))
                && (m.find() && m.group().equals(email.getText()))) {
            email.setEffect(null);
            return true;
        } else {
            new animatefx.animation.Shake(email).play();
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            email.setEffect(in);
            return false;
        }

    }

    private boolean validatenGuest_Password(TextField password) {

        Pattern p = Pattern.compile("[a-zA-Z_0-9]+");
        Matcher m = p.matcher(password.getText());
        if (((password.getText().length() > 7))
                && (m.find() && m.group().equals(password.getText()))) {
            password.setEffect(null);
            return true;
        } else {
            new animatefx.animation.Shake(password).play();
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            password.setEffect(in);
            return false;
        }

    }
}