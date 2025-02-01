/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.bonplan;
import elbaldi.services.BonplanCrud;
import java.io.File;

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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ItembpBackController implements Initializable {

    @FXML
    private AnchorPane dest;
    @FXML
    private ImageView imagefx;
    @FXML
    private Label titrebp;
    private bonplan bonplan1;
    private int id_bonplan;
    
    
    
    
    
     public int getIdbonplan() {
        return id_bonplan;
    }

    public void setIdbonplan(int id_bonplan) {
        this.id_bonplan = id_bonplan;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void setData(bonplan dest) {
       this.bonplan1 = dest;
        id_bonplan=dest.getId_bonplan();
        this.titrebp.setText(bonplan1.getTitre_bonplan());
        
        //Image f = new Image("C:\\xampp\\htdocs\\images\\" + bonplan1.getImage_bonplan());

        //imagefx.setImage(f);
         String imagePath = "C:\\xampp\\htdocs\\images\\"+ bonplan1.getImage_bonplan().toString();
       
        // Create an ImageView object
        ImageView imageView = new ImageView();
        // Create a File object with the path of your image
        File file = new File(imagePath);
       
        // Check if the file exists
        if (file.exists()) {
            // Create an Image object with the file path
            Image image = new Image(file.toURI().toString());
            // Set the image to the ImageView
            this.imagefx.setImage(image);
        } else {
            System.out.println("Image not found.");
        }

    }

    @FXML
    private void details(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("BpDetailsBack.fxml"));
    Parent root = loader.load();
    BpDetailsBackController controller = loader.getController();
    controller.setbonplan(bonplan1); // pass the selected product to the details view
    imagefx.getScene().setRoot(root);

    }
@FXML

    private void supprimer(ActionEvent event) throws SQLException, IOException {
        BonplanCrud bonplancrud = new BonplanCrud();
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setContentText("Vous etes sure de supprimer ce bon plan ?");
        alert1.setHeaderText("s'il vous plait confirmer votre action");

        Optional<ButtonType> result = alert1.showAndWait();

        // if the user confirms the deletion
        if (result.isPresent() && result.get() == ButtonType.OK) {
            bonplancrud.supprimerbonplan(id_bonplan);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("bonplan a été supprimé avec succés!");
        alert.show();
        Parent loader = FXMLLoader.load(getClass().getResource("bonplanbacklist.fxml"));
        titrebp.getScene().setRoot(loader);
        }

       
    }
}
