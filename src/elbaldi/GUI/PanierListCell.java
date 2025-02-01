/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.livraison;
import elbaldi.models.panier;
import elbaldi.models.produit;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
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
class PanierListCell extends ListCell<produit> {

    public panier p;

    public void setP(panier p) {
       
        this.p = p;
       
    }
    // int id_pan = p.getId_panier();

    @Override
    protected void updateItem(produit item, boolean empty) {

        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("panieritem.fxml"));
                AnchorPane hbox = loader.load();
                PanieritemController controller = loader.getController();

                controller.setProduct(item);
                setGraphic(hbox);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
