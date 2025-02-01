/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.avis;
import elbaldi.models.bonplan;
import elbaldi.services.AvisCrud;
import elbaldi.services.UserSession;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author user
 */
public class BpDetailsFrontController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Label titrefx;
    @FXML
    private Label descfx;
    @FXML
    private Label typefx;
    @FXML
    private Button retourfx;
    @FXML
    private Button reserverfx;
    private bonplan bonplan1;
    @FXML
    private Rating rating;

    /**
     * Initializes the controller class.
     */
    public void setbonplan(bonplan bonplan11) {
        this.bonplan1 = bonplan11;
        titrefx.setText(bonplan11.getTitre_bonplan());
        typefx.setText(bonplan11.getType_bonplan());
        descfx.setText(bonplan11.getDescription_bonplan());

//        File f = new File("C:\\xampp\\htdocs\\images\\" + bonplan1.getImage_bonplan());
//
//          img.setImage(new Image(f.toURI().toString()));
//          
        String imagePath = "C:\\xampp\\htdocs\\images\\" + bonplan1.getImage_bonplan().toString();

        // Create an ImageView object
        ImageView imageView = new ImageView();
        // Create a File object with the path of your image
        File file = new File(imagePath);

        // Check if the file exists
        if (file.exists()) {
            // Create an Image object with the file path
            Image image = new Image(file.toURI().toString());
            // Set the image to the ImageView
            this.img.setImage(image);
        } else {
            System.out.println("Image not found.");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(bonplan1!=null){
            
        AvisCrud avis = new AvisCrud();
        avis a = new avis();
        UserSession user = new UserSession();
        List<avis> oldAvis = avis.getAvisByIdUser(user.getUser().getId_user());
        System.out.println(oldAvis);
     if (oldAvis.size()!= 0) {
         for(avis av : oldAvis) {
               if(av.getBonplan1().getId_bonplan() == bonplan1.getId_bonplan()){                 
                    rating.setRating(av.getNote_avis());
                }
                
            }
            
        }

        //TODO
        rating.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number old, Number newT) {
                a.setNote_avis(newT.floatValue());
                a.setBonplan1(bonplan1);
                LocalDateTime now = LocalDateTime.now();
                LocalDate localDate = now.toLocalDate();
                a.setDate_avis(java.sql.Date.valueOf(localDate));
                a.setUser1(user.getUser());
                System.out.println(user.getUser().getId_user());
                List<avis> testAvis = avis.getAvisByIdUser(user.getUser().getId_user());
                 System.out.println(testAvis);
                 System.out.println("111");
                if (testAvis.size() != 0) {
                    System.out.println("222");
                    System.out.println(bonplan1.getId_bonplan());
                    boolean t=false;
                    for (avis av : testAvis) {
                        
                        System.out.println(av.getBonplan1().getId_bonplan());
                        if (av.getBonplan1().getId_bonplan() == bonplan1.getId_bonplan()) {
                            a.setId_avis(av.getId_avis());
                            System.out.println(av.getId_avis());
                            avis.modifierAvis(a);
                            t=true;
                        } 

                    }
                    if(!t){
                        avis.ajouterAvis(a);
                    }

                } 
                else {
                    System.out.println("333");
                    avis.ajouterAvis(a);
                }
                

                System.out.println(newT);
            }
        });
        }
    }

    @FXML

    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("BpFront.fxml"));
        Parent root = loader.load();
        retourfx.getScene().setRoot(root);
    }

    @FXML
    private void reserver(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AjouReservationFront1.fxml"));
        Parent root = loader.load();

        AjouReservationFront1Controller cb = loader.getController();
        cb.setBonplan1(bonplan1);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.showAndWait();
    }

}
