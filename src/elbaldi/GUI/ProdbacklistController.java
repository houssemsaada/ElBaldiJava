/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.categorie;
import elbaldi.models.produit;
import elbaldi.services.CategorieCRUD;
import elbaldi.services.ProduitCRUD;
import elbaldi.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yasmine
 */
public class ProdbacklistController implements Initializable {

    @FXML
    private GridPane grid;
    private List<produit> listeProduit = new ArrayList<>();
    ProduitCRUD ds = new ProduitCRUD();
    @FXML
    private Button categoriefx;
    @FXML
    private Button produitfx;
    private Button comm1;
    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<categorie> categoriesfx;
    @FXML
    private Button Accueilfx;
    @FXML
    private Button profilfx;
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
    @FXML
    private ScrollPane scroll;
    @FXML
    private Button ajoutfx;
    @FXML
    private Button showC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //afficher();
            ListerCategorie();
        categoriesfx.setOnAction(e -> {
            categorie selectedCategorie = categoriesfx.getSelectionModel().getSelectedItem();
            if (selectedCategorie != null && selectedCategorie.getId_categorie() != 0) {
                afficherProduitsParCategorie(selectedCategorie.getId_categorie());
            } else {
                afficher();
            }
        });

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Texte changé : " + oldValue + " -> " + newValue);
            if(newValue!="")
            {
                search(newValue);
            }
            else{
                afficher();
            }
            
        });

        afficher();
    }
    

    public void afficher() {
        try {
            grid.getChildren().remove(0, listeProduit.size());

            listeProduit = ds.afficherProduit();

            int column = 0;
            int row = 1;
            for (int i = 0; i < listeProduit.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/elbaldi/GUI/itemprodback.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
                ItemprodbackController itemController = fxmlLoader.getController();
                itemController.setData(listeProduit.get(i));
                if (column == 3) {
                    column = 0;
                    row++;
                }
                 grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorpane, new Insets(10));
              
                grid.add(anchorpane, column, row);
                column++;
                //GridPane.setMargin(anchorpane, new Insets(10));
            }
        } catch (SQLException ex) {

        } catch (IOException ex) {
        }
    }
 private void ListerCategorie() {

        CategorieCRUD categoriecrud = new CategorieCRUD();
        ObservableList<categorie> list = FXCollections.observableArrayList();
        try {
            String req = " select id_categorie,`nom_categorie`,`description` from `categorie`  ";

            Connection conn = MyConnection.getInstance().getConn();

            PreparedStatement pst = conn.prepareStatement(req);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                categorie c = new categorie(rs.getInt(1), rs.getString(2), rs.getString(3));
                list.add(c);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        categorie all = new categorie(0, "Tous", "");

        categoriesfx.setItems(null);
        categoriesfx.setItems(list);
        categoriesfx.getItems().add(all);

    }

    private void afficherProduitsParCategorie(int id_categorie) {
        try {
            listeProduit = ds.filtreByCategorie(id_categorie);

            // effacer le contenu précédent
            grid.getChildren().clear();

            int column = 0;
            int row = 1;
            for (int i = 0; i < listeProduit.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/elbaldi/GUI/itemprodback.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
               ItemprodbackController itemController = fxmlLoader.getController();
                itemController.setData(listeProduit.get(i));
                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorpane, column++, row);
                GridPane.setMargin(anchorpane, new Insets(10));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void search(String libelle) {
        try {
            grid.getChildren().remove(0, listeProduit.size());
            listeProduit = ds.rechercher(libelle);

//             String searchTerm = searchField.getText().toLowerCase();
//        if (!searchTerm.isEmpty()) {
//            listeProduit = listeProduit.stream()
//                .filter(p -> p.getLibelle().toLowerCase().contains(searchTerm))
//                .collect(Collectors.toList());
//        }
            int column = 0;
            int row = 1;
            for (int i = 0; i < listeProduit.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/elbaldi/GUI/itemprodback.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
                ItemprodbackController itemController = fxmlLoader.getController();
                itemController.setData(listeProduit.get(i));
                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorpane, column++, row);
                GridPane.setMargin(anchorpane, new Insets(10));
            }
        } catch (SQLException ex) {

        } catch (IOException ex) {
        }
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
    private void ajouter(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AjouterProduitBack.fxml"));
        Parent root = loader.load();

        AjouterProduitBackController cb = loader.getController();

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
        afficher();

    }
    @FXML
    private void refresh(ActionEvent event) {
        try {
            grid.getChildren().remove(0, listeProduit.size());
            listeProduit = ds.afficherProduit();

            int column = 0;
            int row = 1;
            for (int i = 0; i < listeProduit.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/elbaldi/GUI/itemprodback.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
                ItemprodbackController itemController = fxmlLoader.getController();
                itemController.setData(listeProduit.get(i));
                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorpane, column, row);
                column++;
                //GridPane.setMargin(anchorpane, new Insets(10));
            }
        } catch (SQLException ex) {

        } catch (IOException ex) {
        }
    }

    @FXML
    private void categ(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("catgeorielistBack.fxml"));
        Parent root = loader.load();
        categoriefx.getScene().setRoot(root);
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
        commandeGUI.changeScene(event, "AjouterEvenement.fxml", "evenements ");

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
