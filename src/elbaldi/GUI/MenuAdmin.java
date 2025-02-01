package elbaldi.GUI;

import elbaldi.models.Utilisateur;
import elbaldi.services.UtilisateurCRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuAdmin implements Initializable{


    @FXML
    private Button btnMenus;

    @FXML
    private Button btnOrders;

    @FXML
    private Button btnSignout;

    @FXML
    private GridPane grid;

    @FXML
    private Button Accueilfx;
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
      ObservableList<Utilisateur> listUsers = FXCollections.observableArrayList();

    private SupprimerCard sup;

    private Approve app;

    Utilisateur utilisateur;
    UtilisateurCRUD utilisateurCRUD = new UtilisateurCRUD();
    private int i = 0;



    @FXML
    void ApprouverOffres(ActionEvent event) {
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


                if (column == 2) {
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


        dialogVbox.getChildren().add(new Text("Désactivé"));
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


        dialogVbox.getChildren().add(new Text("Approuvé avec succés"));
        Scene dialogScene = new Scene(dialogVbox, 200, 200);
        dialog.setScene(dialogScene);
        dialog.show();
        grid.getChildren().clear();
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
                    row=row+1;

                }
                grid.add(anchorPane, column++, row); //(child,column,row)
//                //set grid width
//                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
//                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
//                grid.setMaxWidth(Region.USE_PREF_SIZE);
//
//                //set grid height
//                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
//                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
//                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));

////
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
    
    
    void DashboardAdmin(ActionEvent event) {

    }

    @FXML
    void ProfileAdmin(ActionEvent event) {
        grid.getChildren().clear();
        try {
            // TODO
            FXMLLoader cards = new FXMLLoader();
            cards.setLocation(getClass().getResource("ProfileAdmin.fxml"));

            AnchorPane anchorPane = cards.load();

            grid.add(anchorPane, 1, 1);

            GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @FXML
    private void accueilAction(ActionEvent event) {
        commandeGUI.changeScene(event, "templateBack.fxml", "Acceuil");

    }

    private void profilAction(ActionEvent event) {
        commandeGUI.changeScene(event, "ProfileAdmin.fxml", "Profile");

    }

    @FXML
    private void categ(ActionEvent event) {
        commandeGUI.changeScene(event, "catgeorielistback.fxml", "Categories");

    }

    @FXML
    private void prodd(ActionEvent event) {
        commandeGUI.changeScene(event, "prodbacklist.fxml", "Produits");

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

    private void GestuserAction(ActionEvent event) {
            commandeGUI.changeScene(event, "MenuAdmin.fxml", "gestion utilisateurs ");

    }

    private void decoAction(ActionEvent event) {
        commandeGUI.changeScene(event, "Front1.fxml", "Visiteur ");
    }

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
                    row=row+1;

                }
                grid.add(anchorPane, column++, row); //(child,column,row)
//                //set grid width
//                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
//                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
//                grid.setMaxWidth(Region.USE_PREF_SIZE);
//
//                //set grid height
//                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
//                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
//                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));

////
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }    }

}
