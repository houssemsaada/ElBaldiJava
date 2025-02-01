/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.produit;
import elbaldi.services.ProduitCRUD;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Post;
import facebook4j.PostUpdate;
import facebook4j.auth.AccessToken;
import facebook4j.auth.OAuthAuthorization;
import facebook4j.conf.ConfigurationBuilder;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author USER
 */
public class ItemprodbackController implements Initializable {

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
    private void details(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("proddetailsback.fxml"));
    Parent root = loader.load();
    ProddetailsbackController controller = loader.getController();
    controller.setProduit(produit1); // pass the selected product to the details view
    imagefx.getScene().setRoot(root);

    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException, IOException {
           ProduitCRUD produitcrud = new ProduitCRUD();
     // produitcrud.supprimerProduit(Refproduit);
  
   
        Alert alert2=new Alert(Alert.AlertType.CONFIRMATION);
       alert2.setTitle("Confirmation de suppression");
       alert2.setHeaderText(null);
       alert2.setContentText("Êtes-vous sûr de vouloir supprimer ce produit ?");

      ButtonType confirmerButton = new ButtonType("Confirmer", ButtonBar.ButtonData.OK_DONE);
ButtonType cancelButton = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
alert2.getButtonTypes().setAll(confirmerButton, cancelButton);

Optional<ButtonType> result = alert2.showAndWait();
if (result.get() == confirmerButton){
       produitcrud.supprimerProduit(Refproduit);         
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Information Dialog");
      alert.setHeaderText(null);
      alert.setContentText("produit a été supprimé avec succés!");
      alert.show();
    

   Parent loader = FXMLLoader.load(getClass().getResource("prodbacklist.fxml"));
     libellefx.getScene().setRoot(loader);
}

    }


}






