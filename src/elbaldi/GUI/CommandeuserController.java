/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.Utilisateur;
import elbaldi.models.commande;
import elbaldi.models.panier;
import elbaldi.services.CommandeCRUD;
import elbaldi.services.UserSession;
import elbaldi.services.panierCRUD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class CommandeuserController implements Initializable {
 ObservableList<commande> commandeObservableList = FXCollections.observableArrayList();
    @FXML
    private ListView<commande> ListView;
    @FXML
    private TextField searchTextField;
     UserSession userSession = new UserSession();
    Utilisateur u = userSession.getUser();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        CommandeCRUD cc = new CommandeCRUD();
        panierCRUD pc = new panierCRUD();
        panier p = pc .filtreByuser(u);

        commandeObservableList = FXCollections.observableList(cc.filtreByuser(p));

        ListView.setCellFactory(lv -> new CommandeClientListCell());
        ListView.setItems(commandeObservableList);

        //search bar 
        FilteredList<commande> filteredData = new FilteredList<>(commandeObservableList, b -> true);
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(commande -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKeywords = newValue.toLowerCase();

                if (commande.getEtat().toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                if ((commande.getDate_cmd() + "").toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                if (commande.getPan().getU1().getNom().toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                if (commande.getPan().getU1().getPrenom().toLowerCase().contains(searchKeywords)) {
                    return true;
                }
//                if (String.valueOf(commande.getPan().getU1().getid_user()).toLowerCase().contains(searchKeywords)) {
//                    return true;
//                }
                if (String.valueOf(commande.getPan().getU1().getNumTel()).toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                if (commande.getPan().getU1().getEmail().toLowerCase().contains(searchKeywords)) {
                    return true;
                }
                return (commande.getId_cmd() + "").toLowerCase().contains(searchKeywords);
            });
        });
        // wrap the FilteredList in a SortedList.
        SortedList<commande> sortedList = new SortedList<>(filteredData);
        //sortedList.comparatorProperty().bind(ListView.comparatorProperty());
        ListView.setItems(filteredData);// TODO
    }    
    
}
class CommandeClientListCell extends ListCell<commande> {
    private final Image image = new Image("/Ressources/colis.png");

    @Override

    protected void updateItem(commande item, boolean empty) {
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
                        
            ImageView imageprod = new ImageView(image);
            imageprod.setFitWidth(30);
            imageprod.setFitHeight(30);
            // create labels to display the commande properties
            //  Label idLabel = new Label("Ref commande " + item.getId_cmd());
            Text idText = new Text("Ref commande: ");
            idText.setStyle("-fx-font-weight: bold");
            Text idValue = new Text(item.getId_cmd() + "");
            HBox idbox = new HBox(idText, idValue);
            //Label etatLabel = new Label("Etat: " + item.getEtat());
            Text etatText = new Text("Etat:");
            etatText.setStyle("-fx-font-weight: bold");
            Text etatValue = new Text(item.getEtat());
            HBox etatbox = new HBox(etatText, etatValue);
            //Label dateLabel = new Label("Date commande: " + item.getDate_cmd().toString());
            Text dateText = new Text("Date commande: ");
            dateText.setStyle("-fx-font-weight: bold");
            Text dateValue = new Text(item.getDate_cmd().toString());
            HBox datebox = new HBox(dateText, dateValue);
            //Label userLabel = new Label("nom et prenom: " + (item.getPan().getU1().getNom() + " " + item.getPan().getU1().getPrenom()));
            Text userText = new Text("nom et prenom: ");
            userText.setStyle("-fx-font-weight: bold");
            Text userValue = new Text(item.getPan().getU1().getNom() + " " + item.getPan().getU1().getPrenom());
            HBox userbox = new HBox(userText, userValue);
            // Label emailLabel = new Label("email: " + item.getPan().getU1().getEmail());

            //Label articleLabel = new Label("nombre des articles: " + item.getPan().getNombre_article());
      
            // Label totalLabel = new Label("total: " + item.getPan().getTotal_panier());
            Text totalText = new Text("total: ");
            totalText.setStyle("-fx-font-weight: bold");
            Text totalValue = new Text(item.getTotal() + "");
            HBox totalbox = new HBox(totalText, totalValue);
            // add the labels to the VBox
            vbox.getChildren().addAll(idbox,  datebox, userbox);
            vbox2.getChildren().addAll(etatbox,totalbox);
            Pane leftPane = new Pane(vbox);
            // set the preferred width to half of the HBox

            HBox.setHgrow(leftPane, Priority.ALWAYS);
            Pane rightPane = new Pane(vbox2);
//            leftPane.setPrefWidth(300);
            leftPane.setMaxWidth(450);
            leftPane.setMinWidth(450);
            HBox.setHgrow(rightPane, Priority.ALWAYS);
            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);
            HBox graphbox = new HBox(imageprod,leftPane, rightPane);

            // set the VBox as the cell content
            setText(null);
            setGraphic(graphbox);

        }
    }
}
