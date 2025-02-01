/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.categorie;
import elbaldi.services.CategorieCRUD;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ItemcategbackController implements Initializable {

    @FXML
    private Button modifierfix;
    @FXML
    private Label nomLabell;
    @FXML
    private Label di;
    @FXML
    private Label idLabel;
    @FXML
    private Label nom_categfx;
    @FXML
    private Label descfx;
    @FXML
    private Button supprimerfx;
private categorie categorie1;

private int id_categorie;
    @FXML
    private AnchorPane item;
    
       public int getid_categorie() {
        return id_categorie;
    }

    public void setid_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
       public void setData(categorie categ) {
       this.categorie1 = categ;
        id_categorie=categ.getId_categorie();
        this.nom_categfx.setText(categ.getNom_categorie());
        this.descfx.setText(categ.getDescription());
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//    loader.setLocation(getClass().getResource("CategorieBackModifier.fxml"));
//    Parent root = loader.load();
//    CategorieBackModifierController controller = loader.getController();
//    controller.setCategorie(categorie1); // pass the selected product to the details view

  FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CategorieBackModifier.fxml"));
        Parent root = loader.load();
        

        CategorieBackModifierController cb = loader.getController();
        cb.setCategorie(categorie1);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
        
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException, IOException {
       
      CategorieCRUD categoriecrud = new CategorieCRUD();
      //categoriecrud.supprimerCategorie(categorie1);
   Alert alert2=new Alert(Alert.AlertType.CONFIRMATION);
       alert2.setTitle("Confirmation de suppression");
       alert2.setHeaderText(null);
       alert2.setContentText("Êtes-vous sûr de vouloir supprimer cette catégorie et les produits associes à cette categories ?");

      ButtonType confirmerButton = new ButtonType("Confirmer", ButtonBar.ButtonData.OK_DONE);
ButtonType cancelButton = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
alert2.getButtonTypes().setAll(confirmerButton, cancelButton);

Optional<ButtonType> result = alert2.showAndWait();
if (result.get() == confirmerButton){
      categoriecrud.supprimerCategorie(categorie1);       
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Boîte de dialogue d'information");
      alert.setHeaderText(null);
      alert.setContentText("Catégorie a été supprimée avec succés!");
      alert.show();
     
//      Alert alert = new Alert(Alert.AlertType.INFORMATION);
//      alert.setTitle("Information Dialog");
//      alert.setHeaderText(null);
//      alert.setContentText("categorie a été supprimé avec succés!");
//      alert.show();
   Parent loader = FXMLLoader.load(getClass().getResource("catgeorielistBack.fxml"));
      nom_categfx.getScene().setRoot(loader);
      
//                 categorie categ=new categorie();
//                 categ= this.categorie1 ;
//                CategorieCRUD categoriecrud = new CategorieCRUD();
//                categoriecrud.supprimerCategorie(categ);
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("information Dialog");
//                alert.setHeaderText(null);
//                alert.setContentText("Supression avec succes");
//                alert.show();
//               
            
                
        
}
    }
}
