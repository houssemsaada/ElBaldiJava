/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.*;
import elbaldi.services.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author houss
 */
public class ConsulterPanierController implements Initializable {

    @FXML
    private Button loginButton12;
    @FXML
    private TextField totalTF;
    @FXML
    private Button CommandeBtn;
    ObservableList<produit> produitObservableList = FXCollections.observableArrayList();
    @FXML
    private Spinner<Integer> qtescroll;
    @FXML
    private Button ud;
    @FXML
    private Button updateBTN;
    @FXML
    private ListView<produit> ListView;
    UserSession userSession = new UserSession();
    Utilisateur u = userSession.getUser();
    panier p = new panier();

    public void setTotalTF(TextField totalTF) {
        this.totalTF = totalTF;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        panierCRUD pc = new panierCRUD();
        p = pc.filtreByuser(u);

//        p.setId_panier(Integer.parseInt(panieridTF.getText()));
        PanierListCell pl = new PanierListCell();
        pl.setP(p);
        produitObservableList = FXCollections.observableList(pc.afficherListProduitPanier(p));
        ListView.setCellFactory(lv -> new PanierListCell());
        ListView.setItems(produitObservableList);
        DecimalFormat df = new DecimalFormat("#.##");

// format the number using the DecimalFormat object
           String formattedNum = df.format(p.sommePanier(produitObservableList));
            totalTF.setText(formattedNum);
        //totalTF.setText(p.sommePanier(produitObservableList) + "");
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        this.qtescroll.setValueFactory(valueFactory);
    }

    @FXML
    private void exitCommandeScene(ActionEvent event) throws IOException {
  FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("ProduitFront2.fxml"));
        Parent root = loader.load();
        updateBTN.getScene().setRoot(root);

    }

    @FXML
    private void commanderOnAction(ActionEvent event) {
//        panier p = new panier();
        panierCRUD pc = new panierCRUD();
        pc.modifierPanier(p);
        //p.setId_panier(Integer.parseInt(panieridTF.getText()));
//        p=pc.filtreByidPanier(Integer.parseInt(panieridTF.getText()));
        try {
            if (p.sommePanier(produitObservableList) == 0) {
                commandeGUI.AlertShow("Panier vide", "vide", Alert.AlertType.ERROR);
                return;
            }
        } catch (Exception ewww) {
            ewww.printStackTrace();
            ewww.getCause();
        }

        float somme = p.sommePanier(produitObservableList);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("commandeclient.fxml"));
        try {
            Parent root = loader.load();
            CommandeclientController dc = loader.getController();
            //System.out.println(p.getId_panier());
            int id_panier = p.getId_panier();
            //  dc.setId_panTF(String.valueOf(id_panier));
            // dc.setId_panTF(panieridTF);
            // dc.setTotalTF(somme + "");
            dc.setP(p);
            dc.setNomTF(p.getU1().getNom());
            dc.setPrenomTF(p.getU1().getPrenom());
            dc.setTotalTF(p.sommePanier(produitObservableList) + "");
            totalTF.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void DeleteOnAction(ActionEvent event) {
        try {
            if (ListView.getSelectionModel().getSelectedItem() == null) {
                commandeGUI.AlertShow("Veuillez sélectionner un produit à supprimer.", "Aucun produit sélectionné.", Alert.AlertType.ERROR);
                return;
            }

        } catch (Exception ewww) {
            ewww.printStackTrace();
            ewww.getCause();
        }
        try {
            panierCRUD pc = new panierCRUD();
//            panier p = new panier();
//            p.setId_panier(Integer.parseInt(panieridTF.getText()));

            produit prod = new produit();
            prod.setRef_produit(ListView.getSelectionModel().getSelectedItem().getRef_produit());

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Êtes-vous sûr(e) de vouloir supprimer la commande ?");
            alert.setHeaderText("Veuillez confirmer votre action.");

            Optional<ButtonType> result = alert.showAndWait();

            // if the user confirms the deletion
            if (result.isPresent() && result.get() == ButtonType.OK) {
                pc.supprimerProdPanier(p, prod);
                pc.modifierPanier(p);
                refreshTable();
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    void refreshTable() {
        panierCRUD pc = new panierCRUD();
//        panier p = new panier();
//        p.setId_panier(Integer.parseInt(panieridTF.getText()));
        produitObservableList = FXCollections.observableList(pc.afficherListProduitPanier(p));
        ListView.setCellFactory(lv -> new PanierListCell());
        ListView.setItems(produitObservableList);

        totalTF.setText(p.sommePanier(produitObservableList) + "");
    }

    @FXML
    private void changeQTE(MouseEvent event) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, ListView.getSelectionModel().getSelectedItem().getQuantite());
        this.qtescroll.setValueFactory(valueFactory);
    }

    @FXML
    private void updateOnAction(ActionEvent event) {
        try {
            if (ListView.getSelectionModel().getSelectedItem() == null) {
                commandeGUI.AlertShow("Veuillez sélectionner un produit à mettre à jour.", "Aucun produit sélectionné.", Alert.AlertType.ERROR);
                return;
            }

        } catch (Exception ewww) {
            ewww.printStackTrace();
            ewww.getCause();
        }
        try {
            panierCRUD pc = new panierCRUD();
//            panier p = new panier();
//            p.setId_panier(Integer.parseInt(panieridTF.getText()));

            produit prod = new produit();
            prod.setRef_produit(ListView.getSelectionModel().getSelectedItem().getRef_produit());
            int quantite = qtescroll.getValue();
            pc.modifierQteProdPanier(p, prod, quantite);
            pc.modifierPanier(p);
            refreshTable();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
