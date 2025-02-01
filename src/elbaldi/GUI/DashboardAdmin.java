package elbaldi.GUI;

import elbaldi.models.Utilisateur;
import elbaldi.services.UtilisateurCRUD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class DashboardAdmin implements Initializable {

    @FXML
    private GridPane grid;
    ObservableList<Utilisateur> listUsers = FXCollections.observableArrayList();

    private SupprimerCard sup;

    private Approve app;

    Utilisateur utilisateur;
    UtilisateurCRUD utilisateurCRUD = new UtilisateurCRUD();
    private int i = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < utilisateurCRUD.afficherUtilisateur().size(); i++) {

            listUsers.addAll(utilisateurCRUD.afficherUtilisateur().get(i));
        }
        int row = 0;
        int column = 1;

        for (int i = 0; i < utilisateurCRUD.afficherUtilisateur().size(); i++) {

            try {

                sup = new SupprimerCard() {
                    @Override
                    public void supprimer(Utilisateur utilisateur) {
                        supprimerr(utilisateur);
                    }

                };
                app = new Approve() {
                    @Override
                    public void approve(Utilisateur utilisateur) {
                        approvee(utilisateur);
                    }

                };
                FXMLLoader cards = new FXMLLoader();
                cards.setLocation(getClass().getResource("UserCard.fxml"));

                AnchorPane anchorPane = cards.load();

                UserCard cardController = cards.getController();

                cardController.setData(utilisateurCRUD.afficherUtilisateur().get(i), sup, app);


                if (column == 3) {
                    column = 1;
                    row++;

                }
                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new javafx.geometry.Insets(20));

////
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    private void supprimerr(Utilisateur utilisateur) {


        UtilisateurCRUD UtilisateurCRUD = new UtilisateurCRUD();
        UtilisateurCRUD.supprimerUtilisateur(utilisateur.getid_user());
        VBox dialogVbox = new VBox(10);

        final Stage dialog = new Stage();
        dialog.initModality(Modality.WINDOW_MODAL);


        dialogVbox.getChildren().add(new Text("Deleted"));
        Scene dialogScene = new Scene(dialogVbox, 200, 200);
        dialog.setScene(dialogScene);
        dialog.show();


    }
    private void approvee(Utilisateur utilisateur) {

        UtilisateurCRUD UtilisateurCRUD = new UtilisateurCRUD();
        UtilisateurCRUD.upDateStatus(utilisateur.getid_user());
        VBox dialogVbox = new VBox(10);

        final Stage dialog = new Stage();
        dialog.initModality(Modality.WINDOW_MODAL);


        dialogVbox.getChildren().add(new Text("Approuved"));
        Scene dialogScene = new Scene(dialogVbox, 200, 200);
        dialog.setScene(dialogScene);
        dialog.show();

    }
}
