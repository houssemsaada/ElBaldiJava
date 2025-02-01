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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class CatgeorielistBackController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane gridfx;
    @FXML
    private Button ajoutfx;
 private List<categorie> listecategorie = new ArrayList<>();
    CategorieCRUD ds = new CategorieCRUD();
    private Button comm;
    @FXML
    private Button showC;
    private Button categorie11;
    private Button produit11;
    private Button comm1;
    @FXML
    private Button Accueilfx;
    @FXML
    private Button profilfx;
    @FXML
    private Button categoriefx;
    @FXML
    private Button produitfx;
    @FXML
    private Button commandefx;
    @FXML
    private Button Livrfx;
    @FXML
    private Button Bonplanfx;
    @FXML
    private Button Quizfx;
    @FXML
    private Button Eventfx;
    @FXML
    private Button participationfx;
    @FXML
    private Button GestUser;
    @FXML
    private Button Decofx;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            afficher();
        } catch (SQLException ex) {
            Logger.getLogger(CatgeorielistBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
public void afficher() throws SQLException{
        try {
         
            listecategorie = ds.affichercategorie();
            
            int column = 0;
            int row = 1;
            for (int i = 0; i < listecategorie.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("itemcategback.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
               ItemcategbackController itemController = fxmlLoader.getController();
                itemController.setData(listecategorie.get(i));
                if (column == 3) {
                    column = 0;
                    row++;
                }

                gridfx.add(anchorpane, column++, row);
                gridfx.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridfx.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridfx.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridfx.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridfx.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridfx.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorpane, new Insets(10));
            }
      

        } catch (IOException ex) {
        }
    }
    
    @FXML
    private void se(MouseEvent event) {
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException, SQLException {
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CategorieBackAjout.fxml"));
        Parent root = loader.load();
        

        CategorieBackAjoutController cb = loader.getController();
 
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
        afficher();
         
    }


    @FXML
    private void prodd(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("prodbacklist.fxml"));
        Parent root = loader.load();
        produitfx.getScene().setRoot(root);
    }

    private void commen(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("CommentaireBack.fxml"));
        Parent root = loader.load();
        comm1.getScene().setRoot(root);
    }

   

    @FXML
    private void categ(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("catgeorielistBack.fxml"));
        Parent root = loader.load();
        categoriefx.getScene().setRoot(root);
    }

    @FXML
    private void refresh(ActionEvent event) throws SQLException {
         afficher();
         
    }


    @FXML
    private void accueilAction(ActionEvent event) {
        commandeGUI.changeScene(event, "templateBack.fxml", "Acceuil");

    }

    @FXML
    private void profilAction(ActionEvent event) {
        commandeGUI.changeScene(event, "ProfileAdmin.fxml", "Profile");

    }



    @FXML
    private void commandesAction(ActionEvent event) {

        commandeGUI.changeScene(event, "commandeinterface.fxml", "commande ");
    }

    @FXML
    private void LivraisonAction(ActionEvent event) {
        commandeGUI.changeScene(event, "livraisoninterface.fxml", "livraison ");

    }

    @FXML
    private void BonpalnsAction(ActionEvent event) {
        commandeGUI.changeScene(event, "bonplanbacklist.fxml", "bonplans ");

    }

    @FXML
    private void QuizAction(ActionEvent event) {
        commandeGUI.changeScene(event, "front.fxml", "quiz ");

    }

    @FXML
    private void eventaction(ActionEvent event) {
        commandeGUI.changeScene(event, "AjouterEvenement.fxml", "évènements ");

    }

    @FXML
    private void participationaction(ActionEvent event) {
        commandeGUI.changeScene(event, "afficher participation.fxml", "participation ");

    }

    @FXML
    private void GestuserAction(ActionEvent event) {
            commandeGUI.changeScene(event, "MenuAdmin.fxml", "gestion utilisateurs ");

    }

    @FXML
    private void decoAction(ActionEvent event) {
        commandeGUI.changeScene(event, "Front1.fxml", "Visiteur ");
    }
    }
    
    

