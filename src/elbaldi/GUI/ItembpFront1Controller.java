/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.avis;
import elbaldi.models.bonplan;
import elbaldi.services.AvisCrud;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
public class ItembpFront1Controller implements Initializable {

    @FXML
    private AnchorPane dest;
    @FXML
    private ImageView imagefx;
    @FXML
    private Label titrebp;
     private bonplan bonplan1;
    private int id_bonplan;
    @FXML
    private Label note;
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
   public void setData(bonplan dest) {
        this.bonplan1 = dest;
        id_bonplan = dest.getId_bonplan();
        this.titrebp.setText(bonplan1.getTitre_bonplan());

        //Image f = new Image("C:\\xampp\\htdocs\\images\\" + bonplan1.getImage_bonplan());
        //imagefx.setImage(f);
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
            this.imagefx.setImage(image);
        } else {
            System.out.println("Image not found.");
        }
        //new code
        AvisCrud avis = new AvisCrud();
        List<avis> ratings = new ArrayList<avis>();
        ratings = avis.getAvisByIdBp(id_bonplan);
        System.out.println(ratings);
        if (ratings.isEmpty()) {
            note.setText("0.0");
        } else {
            int sum = 0;
            for (avis rating : ratings) {
                sum += rating.getNote_avis();
            }
            double average = (double) sum / ratings.size();
            String formattedAverage = String.format("%.1f", average);
            note.setText(formattedAverage);
        }

    }

    @FXML
    private void details(MouseEvent event) throws IOException {
        
   
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("BpDetailsFront1.fxml"));
        Parent root = loader.load();
        BpDetailsFront1Controller controller = loader.getController();
        controller.setbonplan(bonplan1);
        controller.initialize(null, null);
        imagefx.getScene().setRoot(root);
    }
    
}