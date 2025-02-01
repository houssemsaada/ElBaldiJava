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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Front1Controller implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    private List<produit> listeProduit = new ArrayList<>();
    ProduitCRUD ds = new ProduitCRUD();
    @FXML
    private ComboBox<categorie> categoriefx;
    @FXML
    private TextField prixmin;
    @FXML
    private TextField prixmax;
    @FXML
    private Button filtrefx;
    @FXML
    private TextField searchField;
    ObservableList<produit> produitObservableList = FXCollections.observableArrayList();
    @FXML
    private Button prodfx;
    @FXML
    private Button cnx;
    @FXML
    private Button insc;
    @FXML
    private ImageView decfx;
    @FXML
    private ImageView croifx;
    @FXML
    private Button bonplanfx;
    @FXML
    private Button eventfx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListerCategorie();
        categoriefx.setOnAction(e -> {
            categorie selectedCategorie = categoriefx.getSelectionModel().getSelectedItem();
            if (selectedCategorie != null && selectedCategorie.getId_categorie() != 0) {
                afficherProduitsParCategorie(selectedCategorie.getId_categorie());
            } else {
                afficher();
            }
        });

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Texte changé : " + oldValue + " -> " + newValue);
            if (newValue != "") {
                search(newValue);
            } else {
                afficher();
            }

        });

        afficher();

        searchField.setPromptText("Rechercher...");

// Création d'un filtre pour la liste de données
//FilteredList<produit> filteredData = new FilteredList<>(produitObservableList, b -> true);
//
//// Ajout d'un écouteur sur le changement de valeur dans le TextField
//searchField.textProperty().addListener((observable, oldValue, newValue) -> {
//    filteredData.setPredicate(produit -> {
//        if (newValue == null || newValue.isEmpty()) {
//            return true;
//        }
//
//        String lowerCaseFilter = newValue.toLowerCase();
//
//        if (produit.getLibelle().toLowerCase().contains(lowerCaseFilter)) {
//            return true;
//        } 
//        return false;
//    });
//});
    }

    public void afficher() {
        try {
            grid.getChildren().remove(0, listeProduit.size());
            listeProduit = ds.afficherProduit();

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
                fxmlLoader.setLocation(getClass().getResource("/elbaldi/GUI/ProduitItemfront1.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
                ProduitItemfront1Controller itemController = fxmlLoader.getController();
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

        categoriefx.setItems(null);
        categoriefx.setItems(list);
        categoriefx.getItems().add(all);

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
                fxmlLoader.setLocation(getClass().getResource("/elbaldi/GUI/ProduitItemfront1.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
                ProduitItemfront1Controller itemController = fxmlLoader.getController();
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

    @FXML
    private void filtrerparprix(ActionEvent event) throws IOException {
        float min = Float.parseFloat(prixmin.getText());
        float max = Float.parseFloat(prixmax.getText());
        try {
            ProduitCRUD produitCRUD = new ProduitCRUD(); // instancier un objet de la classe ProduitCRUD
            listeProduit = ds.filtreByPrixVente(min, max); // appeler la méthode filtreByPrixVente avec les paramètres min et max
            // afficher les produits récupérés
            // effacer le contenu précédent
            grid.getChildren().clear();

            int column = 0;
            int row = 1;
            for (int i = 0; i < listeProduit.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/elbaldi/GUI/ProduitItemfront1.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
                ProduitItemfront1Controller itemController = fxmlLoader.getController();
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

        }

    }

    @FXML
    private void produitsf(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("Front1.fxml"));
        Parent root = loader.load();
        prodfx.getScene().setRoot(root);
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
                fxmlLoader.setLocation(getClass().getResource("/elbaldi/GUI/ProduitItemfront1.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
                ProduitItemfront1Controller itemController = fxmlLoader.getController();
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
    private void decroissant(MouseEvent event) {
        try {
            grid.getChildren().remove(0, listeProduit.size());
            listeProduit = ds.triedec();

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
                fxmlLoader.setLocation(getClass().getResource("/elbaldi/GUI/ProduitItemfront1.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
                ProduitItemfront1Controller itemController = fxmlLoader.getController();
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
    private void croissant(MouseEvent event) {
        try {
            grid.getChildren().remove(0, listeProduit.size());
            listeProduit = ds.triecroissant();

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
                fxmlLoader.setLocation(getClass().getResource("/elbaldi/GUI/ProduitItemfront1.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
                ProduitItemfront1Controller itemController = fxmlLoader.getController();
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
    private void bonplanAction(ActionEvent event) {
        commandeGUI.changeScene(event, "BpFront1.fxml", "Login");

    }

    @FXML
    private void eventAction(ActionEvent event) {
                commandeGUI.changeScene(event, "afficherevenFront.fxml", "Login");

    }

    @FXML
    private void connecter(ActionEvent event) {
        commandeGUI.changeScene(event, "Login.fxml", "Login");

    }

    @FXML
    private void inscr(ActionEvent event) {
        commandeGUI.changeScene(event, "inscription.fxml", "Login");

    }
}
