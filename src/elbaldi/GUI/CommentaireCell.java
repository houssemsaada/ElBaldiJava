/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.commentaire;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author USER
 */
public class CommentaireCell extends ListCell<commentaire> {
    private final Image imageParDefaut = new Image("/Ressources/user.png"); // L'image par défaut

    @Override
    protected void updateItem(commentaire item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
       } 
        else {
            // Créer les éléments graphiques pour afficher le commentaire
            ImageView imageView = new ImageView(imageParDefaut); // l'image du client
            imageView.setFitWidth(40);
            imageView.setFitHeight(40);
            String nomClient = item.getUtilisateur().getNom(); // Récupérer le nom du client
            String prenomClient = item.getUtilisateur().getPrenom(); // Récupérer le prénom du client
            Label nomPrenomLabel = new Label(nomClient + " " + prenomClient); // Créer un nouveau Label pour le nom et le prénom du client
            Label commentaireLabel = new Label(item.getContenu()); // le commentaire
            Date date = item.getDate();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = formatter.format(date);
            Label dateLabel = new Label(formattedDate);// la date du commentaire
           
            HBox hbox = new HBox(imageView, commentaireLabel, dateLabel);
            hbox.setAlignment(Pos.CENTER_LEFT); // aligner les éléments à gauche
            hbox.setSpacing(10); // ajouter de l'espace entre les éléments
              // Disposer les éléments graphiques verticalement
            VBox vbox = new VBox(nomPrenomLabel, hbox);
            vbox.setAlignment(Pos.CENTER_LEFT); // aligner les éléments à gauche
            vbox.setSpacing(8); // ajouter de l'espace entre les éléments

            // Afficher la disposition verticale dans la cellule
            setGraphic(vbox);
            setText(null);
//           

        }
    }
}
