/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.commande;
import elbaldi.models.livraison;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author houss
 */
class LivraisonListCell extends ListCell<livraison> {

    private final Image image = new Image("/Ressources/livraison-rapide.png");

    @Override
    protected void updateItem(livraison item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            // create a VBox to hold the cell content
            VBox vbox = new VBox();
            vbox.setAlignment(Pos.CENTER_RIGHT);
            vbox.setSpacing(8);
            VBox vbox2 = new VBox();
            vbox2.setAlignment(Pos.CENTER);
            vbox2.setSpacing(8);
            VBox vbox0 = new VBox();
            vbox0.setAlignment(Pos.CENTER_LEFT);
            vbox0.setSpacing(8);
            
            ImageView imageprod = new ImageView(image);
            imageprod.setFitWidth(30);
            imageprod.setFitHeight(30);
            // create labels to display the Livraison properties
            Text idText = new Text("Ref livraison: ");
            idText.setStyle("-fx-font-weight: bold");
            Text idValue = new Text(item.getId_livraison() + "");
            HBox idbox = new HBox(idText, idValue);

            Text etatText = new Text("Etat:");
            etatText.setStyle("-fx-font-weight: bold");
            Text etatValue = new Text(item.getStatus_livraison());
            HBox etatbox = new HBox(etatText, etatValue);

            Text dateText = new Text("Date livraison: ");
            dateText.setStyle("-fx-font-weight: bold");
            Text dateValue = new Text(item.getDate_livraison() + "");
            HBox datebox = new HBox(dateText, dateValue);

            Text clientText = new Text("Client: ");
            clientText.setStyle("-fx-font-weight: bold");
            Text clientValue = new Text(item.getC1().getPan().getU1().getNom() + " " + item.getC1().getPan().getU1().getPrenom());
            HBox clientbox = new HBox(clientText, clientValue);

            Text adresseText = new Text("Adresse: ");
            adresseText.setStyle("-fx-font-weight: bold");
            Text adresseValue = new Text(item.getAdresse_livraison());
            HBox adressebox = new HBox(adresseText, adresseValue);

            Text telText = new Text("Telephone: ");
            telText.setStyle("-fx-font-weight: bold");
            Text telValue = new Text(item.getC1().getPan().getU1().getNumTel() + "");
            HBox telbox = new HBox(telText, telValue);

            Text emailText = new Text("Email: ");
            emailText.setStyle("-fx-font-weight: bold");
            Text emailValue = new Text(item.getC1().getPan().getU1().getEmail());
            HBox emailbox = new HBox(emailText, emailValue);

            Text dateccmdText = new Text("Date commande: ");
            dateccmdText.setStyle("-fx-font-weight: bold");
            Text dateccmdValue = new Text(item.getC1().getDate_cmd() + "");
            HBox dateccmdbox = new HBox(dateccmdText, dateccmdValue);

            Text articleText = new Text("Nombre d'articles: ");
            articleText.setStyle("-fx-font-weight: bold");
            Text articleValue = new Text(item.getC1().getPan().getNombre_article() + "");
            HBox articlebox = new HBox(articleText, articleValue);

            Text totalText = new Text("Total: ");
            totalText.setStyle("-fx-font-weight: bold");
            Text totalValue = new Text(item.getC1().getTotal() + "");
            HBox totalbox = new HBox(totalText, totalValue);

            // add the labels to the VBox
            vbox.getChildren().addAll(idbox, etatbox, datebox, clientbox, adressebox);
            vbox2.getChildren().addAll(telbox, emailbox, dateccmdbox, articlebox, totalbox);
            vbox0.getChildren().add(imageprod);
            Pane leftPane = new Pane(vbox);

            HBox.setHgrow(leftPane, Priority.ALWAYS);
            Pane rightPane = new Pane(vbox2);
            leftPane.setMaxWidth(450);
            leftPane.setMinWidth(450);
            HBox.setHgrow(rightPane, Priority.ALWAYS);
            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);
            Pane sPane = new Pane(vbox0);

            HBox.setHgrow(sPane, Priority.ALWAYS);
            sPane.setMaxWidth(5);
            sPane.setMinWidth(5);
            HBox graphbox = new HBox(imageprod, leftPane, rightPane);

            // set the VBox as the cell content
            setText(null);
            setGraphic(graphbox);

        }
    }
}
