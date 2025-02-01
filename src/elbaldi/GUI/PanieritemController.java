/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.panier;
import elbaldi.models.produit;
import elbaldi.services.panierCRUD;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author houss
 */
public class PanieritemController implements Initializable {

    @FXML
    private Label reflabel;
    @FXML
    private Label libellelabel;
    @FXML
    private Label desclabel;
    @FXML
    private Label prixlabel;
    @FXML
    private Label quantitelabel;
    @FXML
    private Label totallabel;
    @FXML
    private ImageView imageprod;
    
    private produit prod;
    private panier pan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setProduct(produit product) {
        
        this.prod = product;
        Image image = new Image("http://localhost/images/"+product.getImage());
        imageprod.setImage(image);
        reflabel.setText(product.getRef_produit());
        libellelabel.setText(product.getLibelle());
        desclabel.setText(product.getDescription());
        prixlabel.setText(String.valueOf(product.getPrix_vente()));
        quantitelabel.setText(String.valueOf(product.getQuantite()));
        Float totalprod = product.getPrix_vente() * product.getQuantite();
        totallabel.setText(String.valueOf(totalprod));
    }



}
