/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.commande;
import elbaldi.models.livraison;
import elbaldi.models.panier;
import elbaldi.models.*;
import elbaldi.services.*;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author houss
 */
public class CommandeclientController implements Initializable {

    float total;
    int id_panier;
    panier p;

    @FXML
    private Button exit;
    @FXML
    private Button exit1;
    @FXML
    private Button addBtn;

    @FXML
    private TextField totalTF;
    ObservableList<produit> produitObservableList = FXCollections.observableArrayList();
    ObservableList<produit> yasObservableList = FXCollections.observableArrayList();

    @FXML
    private TextField nomTF;
    @FXML
    private TextField prenomTF;
    @FXML
    private TextField addrTF;
    @FXML
    private AnchorPane adresseTF;
    @FXML
    private TextField id_panTF;
    @FXML
    private ListView<produit> listView;
    UserSession userSession = new UserSession();
    Utilisateur u = userSession.getUser();
    CommandeCRUD cc = new CommandeCRUD();
    //ObservableList<produit> produitObservableList = FXCollections.observableArrayList();
    @FXML
    private Label promometier;
    @FXML
    private TextField codepromo;
    PromotionCRUD prc = new PromotionCRUD();
    @FXML
    private Button validerpromo;
    String formattedNum;

    /**
     * Initializes the controller class.
     *
     * @param p
     */
    public void setP(panier p) {
        this.p = p;
        panierCRUD pc = new panierCRUD();

        //  p.setId_panier(Integer.parseInt(id_panTF.getText()));
//        p.setId_panier(Integer.parseInt(panieridTF.getText()));
        yasObservableList = FXCollections.observableList(pc.afficherListProduityasmine(p));
        //    produitObservableList = FXCollections.observableList(pc.afficherListProduitPanier(p));
        produitObservableList = FXCollections.observableList(p.getList());
        System.out.println(produitObservableList);
        listView.setCellFactory(rr -> new MiniPanierListCell());
        listView.setItems(produitObservableList);
        // totalTF.setText(p.getTotal_panier() + "");

    }

    public void setTotalTF(String totalTF) {
        System.out.println(totalTF);
        total = Float.parseFloat(totalTF);
        int count = cc.orderCount(p);
        if (count >= 10) {
            promometier.setVisible(true);
            total = (float) (total * 0.9);
            DecimalFormat df = new DecimalFormat("#.##");

// format the number using the DecimalFormat object
            formattedNum = df.format(total);
            this.totalTF.setText(formattedNum);
        } else {
            DecimalFormat df = new DecimalFormat("#.##");

// format the number using the DecimalFormat object
            formattedNum = df.format(total);
            this.totalTF.setText(formattedNum);

        }

    }

    public void setId_panTF(TextField id_panTF) {
        this.id_panTF = id_panTF;
    }

    public void setNomTF(String nomTF) {
        this.nomTF.setText(nomTF);
    }

    public void setPrenomTF(String prenomTF) {
        this.prenomTF.setText(prenomTF);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void exitCommandeScene(ActionEvent event) {
    }

    @FXML
    private void backonAction(ActionEvent event) {
        commandeGUI.changeScene(event, "consulterPanier.fxml", "Panier");
    }

    @FXML
    private void confirmerOnAction(ActionEvent event) {
        try {
            if (commandeGUI.isTextFieldEmpty(nomTF, prenomTF, addrTF)) {
                commandeGUI.AlertShow("Veuillez remplir tous les champs!", "Champs vides!", Alert.AlertType.ERROR);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        CommandeCRUD cr = new CommandeCRUD();
        livraisonCRUD lr = new livraisonCRUD();
        panierCRUD pc = new panierCRUD();
        String adress = addrTF.getText();

        commande c = new commande(p);
        c.setAdresse(adress);
        //total = Float.parseFloat(totalTF.getText());
        c.setTotal(total);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Êtes-vous sûr(e) de vouloir confirmer la commande ?");
        alert.setHeaderText("Veuillez confirmer votre action");
        Optional<ButtonType> result = alert.showAndWait();
        // if the user confirms the update action
        if (result.isPresent() && result.get() == ButtonType.OK) {
            ProduitCRUD pcc = new ProduitCRUD();
            commandeGUI.AlertShow("Commande ajoutée! ", "commande", Alert.AlertType.INFORMATION);

            cr.ajouterCommande(c);
            commande c2 = cr.filtreBypanier(p);
            cr.modifierCommande(c, c2);
            System.out.println("l'id de la nouvelle commande" + c2.getId_cmd());
            for (int i = 0; i < yasObservableList.size(); i++) {
                produit element1 = yasObservableList.get(i);
                produit element2 = produitObservableList.get(i);
                for (int j = 0; j < element2.getQuantite(); j++) {
                    cr.ajouterProdCommande(c2, element2);
                }
                try {
                    int quantite = element2.getQuantite();  //qte panier
                    // quantite += element1.getQuantite();
                    // System.out.println(quantite);
                    element1.setQuantite(quantite + element1.getQuantite());
                    pc.supprimerProdPanier(p, element2);
                    panier p1 = p;
                    p1.setNombre_article(0);
                    p1.setTotal_panier(0);
                    pc.modifierPanier(p1);
                    pcc.modifierProduit(element1);
                } catch (SQLException ex) {
                    Logger.getLogger(CommandeclientController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            PdfOrder pdf = new PdfOrder();
            pdf.orderPdf(c2);
            MailerService ms = new MailerService();
            try {
                ms.sendCommandeMail(c2);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

    }

    @FXML
    private void codeOnAction(ActionEvent event) {
        String code = codepromo.getText();
        promotion promo = new promotion();
        promo.setCode_promo(code);
        if (prc.promocodeExistePourUtilisateur(promo, u)) {
            promo = prc.getBypromo(code);
            total = total * (1 - promo.getTaux());
            DecimalFormat df = new DecimalFormat("#.##");

// format the number using the DecimalFormat object
            formattedNum = df.format(total);
            this.totalTF.setText(formattedNum);
            validerpromo.setText((int) (promo.getTaux() * 100) + "%");
            validerpromo.setDisable(true);
        }
    }

}

class MiniPanierListCell extends ListCell<produit> {

    @Override
    protected void updateItem(produit item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            // create a VBox to hold the cell content
            VBox vbox = new VBox();
            vbox.setAlignment(Pos.CENTER_LEFT);
            vbox.setSpacing(8);
            VBox vbox2 = new VBox();
            vbox2.setAlignment(Pos.CENTER);
            vbox2.setSpacing(8);

            // create labels to display the Livraison properties
            Text idText = new Text("Ref produit: ");
            idText.setStyle("-fx-font-weight: bold");
            Text idValue = new Text(item.getRef_produit());
            HBox idbox = new HBox(idText, idValue);

            Text libelleText = new Text("Libelle:");
            libelleText.setStyle("-fx-font-weight: bold");
            Text libelleValue = new Text(item.getLibelle());
            HBox libellebox = new HBox(libelleText, libelleValue);

            Text quantiteText = new Text("Quantite: ");
            quantiteText.setStyle("-fx-font-weight: bold");
            Text quantiteValue = new Text(item.getQuantite() + "");
            HBox quantitebox = new HBox(quantiteText, quantiteValue);
            //for total produit 

            Float totalprod = item.getPrix_vente() * item.getQuantite();
            Text totText = new Text("Total: ");
            totText.setStyle("-fx-font-weight: bold");
            Text totValue = new Text(totalprod + "");
            HBox totbox = new HBox(totText, totValue);

            // add the labels to the VBox
            vbox.getChildren().addAll(idbox, libellebox);
            vbox2.getChildren().addAll(quantitebox, totbox);
            Pane leftPane = new Pane(vbox);

            HBox.setHgrow(leftPane, Priority.ALWAYS);
            Pane rightPane = new Pane(vbox2);
            leftPane.setMaxWidth(190);
            leftPane.setMinWidth(190);
            HBox.setHgrow(rightPane, Priority.ALWAYS);

            HBox graphbox = new HBox(leftPane, rightPane);

            // set the VBox as the cell content
            setText(null);
            setGraphic(graphbox);

        }
    }
}
