/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.bonplan;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author user
 */
public class BpDetailsFront1Controller implements Initializable {

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
       private bonplan bonplan1;

    /**
     * Initializes the controller class.
     */
     public void setbonplan(bonplan bonplan11) throws IOException {
        this.bonplan1 = bonplan11;
        titrefx.setText(bonplan11.getTitre_bonplan());
         typefx.setText(bonplan11.getType_bonplan());
          descfx.setText(bonplan11.getDescription_bonplan());
             
//        File f = new File("C:\\xampp\\htdocs\\images\\" + bonplan1.getImage_bonplan());
//
//          img.setImage(new Image(f.toURI().toString()));
//          
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
            this.img.setImage(image);
        } else {
            System.out.println("Image not found.");
        }
    
    
     }

    @FXML
    private void back(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("BpFront1.fxml"));
        Parent root = loader.load();
        retourfx.getScene().setRoot(root);
    }
    
    
//}
//     @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}