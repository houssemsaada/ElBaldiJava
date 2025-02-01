/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.List;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.apache.http.client.RedirectStrategy;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import elbaldi.models.SMSNotifier;
import elbaldi.models.Utilisateur;
import elbaldi.models.categorie;
import elbaldi.models.produit;
import elbaldi.services.CategorieCRUD;
import elbaldi.services.MailerService;
import elbaldi.services.ProduitCRUD;
import elbaldi.services.SmsServicee;
import elbaldi.services.Upload;
import elbaldi.utils.MyConnection;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.rmi.Naming.list;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Yasmine
 */
public class AjouterProduitBackController implements Initializable {

    @FXML
    private TextField reffx;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Button Ajouterfx;
    @FXML
    private ListView img;
    @FXML
    private Button insérer;
    @FXML
    private TextArea descriptionfx;
    @FXML
    private TextField prixfx;
    @FXML
    private Button annulerfx;
    @FXML
    private ComboBox<categorie> categoriefx;

    @FXML
    private TextField libellefx;
    private TextField quantitefx;
    File selectedfile;

    String path_img;

    Upload u = new Upload();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //reffx.setText("TUN61900");
        
        ListerCategorie();
        insérer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                FileChooser fc = new FileChooser();
                fc.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("image", "*.jpg", "*.png")
                );
                selectedfile = fc.showOpenDialog(null);
                if (selectedfile != null) {
                    try {
                        resizeFile(selectedfile.getAbsolutePath(),"C:\\xampp\\htdocs\\images\\"+selectedfile.getName(),227,207);
                        
                        img.getItems().add(selectedfile.getName());
                        
                        path_img = selectedfile.getAbsolutePath();
                    } catch (IOException ex) {
                        Logger.getLogger(AjouterProduitBackController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    System.out.println("Fichier erroné");
                }

            }

        });
        Ajouterfx.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                try {
                    
                    categorie c = categoriefx.getSelectionModel().getSelectedItem();
                    float prixx = Float.parseFloat(prixfx.getText());
                    produit pp = new produit(reffx.getText(), libellefx.getText(), descriptionfx.getText(),
                            selectedfile.getName(), prixx, c);
                    
                    ProduitCRUD PROD = new ProduitCRUD();
                    try {
                        PROD.ajouterProduit(pp);
                    } catch (SQLException ex) {
                        Logger.getLogger(AjouterProduitBackController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
                    alert0.setTitle("information Dialog");
                    alert0.setHeaderText(null);
                    alert0.setContentText("Ajout avec succes ");
                    alert0.show();
                    ((Node) event.getSource()).getScene().getWindow().hide();
                                 
                    
               
//// les ajouter à la liste phoneNumbers

List<String> phoneNumbers = PROD.getPhoneNumbersByCategoryId(c.getId_categorie());
//pour tester que tous les numeros sont dans la liste  
//phoneNumbers.forEach(System.out::println);

SMSNotifier notifier = new SMSNotifier(); // correct one
 String mess = "Cher(e) client,\n"
                    + "J'espère que vous allez bien. Je voulais vous informer que nous avons ajouté un nouveau produit à votre catégorie préférée.\n"
                    + "Nous avons sélectionné ce produit en pensant à vos besoins et à vos préférences. Nous pensons que cela pourrait être une excellente option pour vous.\n"
                    + "Merci de votre confiance en notre entreprise. Nous espérons que vous apprécierez le nouveau produit autant que nous.\n"
                    + " L'équipe ElBaldi";
String num_test="+21697618378";
notifier.notifyClients(phoneNumbers, mess);    // correct one
    String[] phoneNumbersArray = {"+21697618378"};  ////
    List<String> phoneNumberss = Arrays.asList(phoneNumbersArray);///

     notifier.notifyClients(phoneNumberss, mess); ///
SmsServicee sms =new SmsServicee();

//sms.sendSms(num_test,mess);
List<String> emails = PROD.getEmailsByCategoryId(c.getId_categorie());
MailerService ms=new MailerService();
ms.sendAjoutProdCategnMail(emails);

                } catch (SQLException ex) {
                    Logger.getLogger(AjouterProduitBackController.class.getName()).log(Level.SEVERE, null, ex);
                }

               
               
                
            }
        });
        annulerfx.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                reffx.clear();
                libellefx.clear();
                descriptionfx.clear();
                prixfx.clear();
                categoriefx.getItems().clear();
                img.getItems().clear();

            }
        });
    }

    @FXML
    private void prefixe(MouseEvent event) {
        reffx.setText("TUN61900");
        //String referenceProduit = produit.getReferenceProduit();
        //reffx.setText("TUN61900" + referenceProduit);
    }

    private void ListerCategorie() {

        CategorieCRUD categoriecrud = new CategorieCRUD();
        ObservableList<categorie> list = FXCollections.observableArrayList();
        try {
            String req = " select id_categorie,`nom_categorie`,`description` from `categorie`  ";

            Connection conn = MyConnection.getInstance().getConn();

            PreparedStatement pst = conn.prepareStatement(req);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                categorie c = new categorie(rs.getInt(1), rs.getString(2), rs.getString(3));
                list.add(c);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        categoriefx.setItems(null);
        categoriefx.setItems(list);
    }

    public void resizeFile(String imagePathToRead,
            String imagePathToWrite, int resizeWidth, int resizeHeight)
            throws IOException {

        File fileToRead = new File(imagePathToRead);
        BufferedImage bufferedImageInput = ImageIO.read(fileToRead);

        BufferedImage bufferedImageOutput = new BufferedImage(resizeWidth,
                resizeHeight, bufferedImageInput.getType());

        Graphics2D g2d = bufferedImageOutput.createGraphics();
        g2d.drawImage(bufferedImageInput, 0, 0, resizeWidth, resizeHeight, null);
        g2d.dispose();

        String formatName = imagePathToWrite.substring(imagePathToWrite
                .lastIndexOf(".") + 1);

        ImageIO.write(bufferedImageOutput, formatName, new File(imagePathToWrite));
    }

}
