/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.Reservation;
import elbaldi.models.Utilisateur;
import elbaldi.models.avis;
import elbaldi.models.bonplan;
import elbaldi.models.produit;
import elbaldi.services.AvisCrud;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AvisbackController implements Initializable {

    @FXML
    private Pane pnlOverview;
    @FXML
    private TableView<avis> table;
    @FXML
    private TableColumn<avis, Float> Note;
    @FXML
    private TableColumn<avis, Date> date;
    @FXML
    private TableColumn<avis, Utilisateur> client;
    @FXML
    private TableColumn<avis, bonplan> bonplan;
    @FXML
    private Button Home1;
    @FXML
    private Button btnSignout1;
    @FXML
    private Button btnSignout1111;
    @FXML
    private Button btnSignout11111;
    @FXML
    private Button btnSignout1111111;
    @FXML
    private Button btnSignout11111111;
    @FXML
    private Button btnSignout111111111;
    @FXML
    private Button btnSignout11112;
    @FXML
    private Button btnSignout111121;
    @FXML
    private Button btnSignout11112111;
    @FXML
    private Button reservation1;
    @FXML
    private Button bonplan1;
    @FXML
    private Button avis1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            // PrixT.setCellValueFactory(new PropertyValueFactory<>("PrixTotal"));
            tableInit();
            Afficher();
        } catch (SQLException ex) {
            Logger.getLogger(AvisbackController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
    }   
    public void tableInit() {

        Note.setCellValueFactory(new PropertyValueFactory<>("note_avis"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_avis"));
        client.setCellValueFactory(new PropertyValueFactory<>("user1"));
        bonplan.setCellValueFactory(new PropertyValueFactory<>("bonplan1"));
        ObservableList<String> data = FXCollections.observableArrayList("note_avis", "date_avis", "user1","bonplan1");
       //triBox.setItems(data);

    }
     public void Afficher() throws SQLException {

        AvisCrud commCRUD = new  AvisCrud();

        ObservableList<avis> liste = FXCollections.
                observableArrayList((commCRUD.afficherAvis()));

        table.setItems(liste);

        /* add column to the tableview and set its items */
        Note.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<avis, Float>, ObservableValue<Float>>() {
           @Override
           public ObservableValue<Float> call(TableColumn.CellDataFeatures<avis, Float> param) {
           return new ReadOnlyObjectWrapper<>(param.getValue().getNote_avis());
    }
});

        
        date.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<avis, java.sql.Date>, ObservableValue<java.sql.Date>>() {
            @Override
            public ObservableValue<java.sql.Date> call(TableColumn.CellDataFeatures<avis, java.sql.Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDate_avis());
            }
        });
        

        client.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<avis, Utilisateur>, ObservableValue<Utilisateur>>() {
        @Override
         public ObservableValue<Utilisateur> call(TableColumn.CellDataFeatures<avis, Utilisateur> param) {
        return new ReadOnlyObjectWrapper(param.getValue().getUser1());
    }
});


          bonplan.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<avis, bonplan>, ObservableValue<bonplan>>() {
         @Override
          public ObservableValue<bonplan> call(TableColumn.CellDataFeatures<avis, bonplan> param) {
         return new ReadOnlyObjectWrapper(param.getValue().getBonplan1());
    }
});


       

        
    }


    

    @FXML
    private void reservationBp(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("AfficherReservationBack.fxml"));
        Parent root = loader.load();
        reservation1.getScene().setRoot(root);
    }

    @FXML
    private void Bonplans(ActionEvent event) throws IOException {
      
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("bonplanbacklist.fxml"));
        Parent root = loader.load();
        bonplan1.getScene().setRoot(root);
    }

    @FXML
    private void avisBp(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("avisback.fxml"));
        Parent root = loader.load();
        avis1.getScene().setRoot(root);
    }

   
}
