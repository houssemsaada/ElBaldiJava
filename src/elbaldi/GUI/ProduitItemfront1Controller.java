/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.produit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
 * @author Yasmine
 */
public class ProduitItemfront1Controller implements Initializable {

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

        File f = new File("C:\\xampp\\htdocs\\images\\" + produit1.getImage());

        imagefx.setImage(new Image(f.toURI().toString()));
}

    @FXML
    private void details(MouseEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("ProduitDetailsFront1.fxml"));
    Parent root = loader.load();
    ProduitDetailsFront1Controller controller = loader.getController();
    controller.setProduit(produit1); // pass the selected product to the details view
    imagefx.getScene().setRoot(root);
    }
    
}
