/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.produit;
import elbaldi.services.ProduitCRUD;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author mEtrOpOliS
 */
public class ItemprodliquidController implements Initializable {

    @FXML
    private AnchorPane dest;
    @FXML
    private ImageView imagefx;
    @FXML
    private Label libellefx;
    @FXML
    private Label prixfx;
    private produit produit1;
 private String Refproduit;
    @FXML
    private TextField redu;
    ProduitCRUD pc = new ProduitCRUD();
       public String getRefProduit() {
        return Refproduit;
    }

    public void setRefProduit(String Refproduit) {
        this.Refproduit = Refproduit;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 public void setData(produit dest) {
        this.produit1 = dest;
        Refproduit=dest.getRef_produit();
        this.libellefx.setText(produit1.getLibelle());
        this.prixfx.setText(produit1.getPrix_vente()+" "+" DT ");

        //Image f = new Image("C:\\xampp\\htdocs\\images\\" + produit1.getImage());

        //imagefx.setImage(f);
        
        String imagePath = "C:\\xampp\\htdocs\\images\\"+ produit1.getImage().toString();
       
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
    private void details(MouseEvent event) {
    }

    @FXML
    private void redonaction(ActionEvent event) throws IOException, SQLException {
        Float reduction = Float.parseFloat(redu.getText())/100 ; 
        Float price = produit1.getPrix_vente()*(1-reduction);
        produit1.setPrix_vente(price);
        pc.modifierProduit(produit1);
         Parent loader = FXMLLoader.load(getClass().getResource("minprods.fxml"));
     libellefx.getScene().setRoot(loader);
    }
    
}
