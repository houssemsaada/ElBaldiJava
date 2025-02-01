/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.Reservation;
import elbaldi.models.Utilisateur;
import elbaldi.models.bonplan;
import elbaldi.models.categorie;
import elbaldi.models.produit;
import elbaldi.services.BonplanCrud;
import elbaldi.services.CategorieCRUD;
import elbaldi.services.ReservationCrud;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AfficherReservationBackController implements Initializable {

////    @FXML
////    private TableView<Reservation> TableView;
////    @FXML
////    private TableColumn<Reservation, Integer> nombre;
////    @FXML
////    private TableColumn<Reservation, Date> date;
////    @FXML
////    private TableColumn<Reservation, String> statut;
////    ObservableList<Reservation> commandeObservableList = FXCollections.observableArrayList();
////    @FXML
////    private TextField searchTextField;
////    @FXML
////    private Button Home1;
////    @FXML
////    private Button categorie1;
////    @FXML
////    private Button produit1;
////    @FXML
////    private Button comm1;
////    @FXML
////    private Button btnSignout1;
////    @FXML
////    private Button btnSignout1111;
////    @FXML
////    private Button btnSignout11111;
////    @FXML
////    private Button btnSignout1111111;
////    @FXML
////    private Button btnSignout11111111;
////    @FXML
////    private Button btnSignout111111111;
////    @FXML
////    private Button btnSignout11112;
////    @FXML
////    private Button btnSignout111121;
////    @FXML
////    private Button btnSignout11112111;
////    @FXML
////    private Button res;
////    @FXML
////    private Button bpp;
////    @FXML
////    private Button aviss;
////    @FXML
////    private Button confirmer;
////    @FXML
////    private Button refuser;
////    @FXML
////    private TableColumn<Reservation,Utilisateur> nom;
////    @FXML
////    private TableColumn<Reservation,bonplan> lieu;
////    @FXML
////    private ImageView datetrie;
////
////    /**
////     * Initializes the controller class.
////     */
////    @Override
////    public void initialize(URL url, ResourceBundle rb) {
////        ReservationCrud cc = new ReservationCrud();
////        commandeObservableList = FXCollections.observableList(cc.afficherReservation());
////
////        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
////        date.setCellValueFactory(new PropertyValueFactory<>("date"));
////        statut.setCellValueFactory(new PropertyValueFactory<>("statut"));
////        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
////        lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
////        try {
////            Afficher();
////            
//////        nomCol.setCellValueFactory(cellData -> {
//////            System.out.println("aaaaa");
//////            Utilisateur utilisateur = cellData.getValue().getUser2();
//////            System.out.println(utilisateur);
//////            String fullName = utilisateur.getNom() + " " + utilisateur.getPrenom();
//////            System.out.println(fullName);
//////            return new SimpleStringProperty(fullName);
//////        }
//////        );
////        } catch (SQLException ex) {
////            Logger.getLogger(AfficherReservationBackController.class.getName()).log(Level.SEVERE, null, ex);
////        }
////     
////
////        //TableView.setItems(commandeObservableList);
////        //search bar 
////        FilteredList<Reservation> filteredData = new FilteredList<>(commandeObservableList, b -> true);
////        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
////            filteredData.setPredicate(Reservation -> {
////
////                if (newValue == null || newValue.isEmpty()) {
////                    return true;
////                }
////
////                String searchKeywords = newValue.toLowerCase();
////
////                if (Reservation.getStatut_reservation().toLowerCase().contains(searchKeywords)) {
////                    return true;
////                }
////                if ((Reservation.getDate_reservation() + "").toLowerCase().contains(searchKeywords)) {
////                    return true;
////                }
////                return (Reservation.getNombre_personnes()+ "").toLowerCase().contains(searchKeywords);
//////                if (Reservation.getUser2().getNom().toLowerCase().contains(searchKeywords)) {
//////                    return true;
//////                }
//////                 if (Reservation.getUser2().getPrenom().toLowerCase().contains(searchKeywords)) {
//////                    return true;
//////                }
////                //return (Reservation.getId_reservation()+ "").toLowerCase().contains(searchKeywords);
////            });
////        });
////        // wrap the FilteredList in a SortedList.
////        SortedList<Reservation> sortedList = new SortedList<>(filteredData);
////        sortedList.comparatorProperty().bind(TableView.comparatorProperty());
////        TableView.setItems(filteredData);
////        // TODO
////    }
////
////        public void Afficher() throws SQLException {
////
////        ReservationCrud resCRUD = new  ReservationCrud();
////
////        ObservableList<Reservation> liste = FXCollections.
////                observableArrayList((resCRUD.afficherReservation()));
////
////        TableView.setItems(liste);
////
////        /* add column to the tableview and set its items */
////        nombre.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, Integer>, ObservableValue<Integer>>() {
////
////            @Override
////            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Reservation, Integer> param) {
////                return new ReadOnlyObjectWrapper(param.getValue().getNombre_personnes());
////            }
////        });
////        
////        date.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, java.sql.Date>, ObservableValue<java.sql.Date>>() {
////            @Override
////            public ObservableValue<java.sql.Date> call(TableColumn.CellDataFeatures<Reservation, java.sql.Date> param) {
////                return new ReadOnlyObjectWrapper(param.getValue().getDate_reservation());
////            }
////        });
////        
////
////        statut.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
////            @Override
////            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reservation, String> param) {
////                return new ReadOnlyObjectWrapper(param.getValue().getStatut_reservation());
////            }
////        });
////         nom.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, Utilisateur>, ObservableValue<Utilisateur>>() {
////            @Override
////            public ObservableValue<Utilisateur> call(TableColumn.CellDataFeatures<Reservation, Utilisateur> param) {
////                return new ReadOnlyObjectWrapper(param.getValue().getUser2().getNom());
////            }
////        });
////          lieu.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, bonplan>, ObservableValue<bonplan>>() {
////            @Override
////            public ObservableValue<bonplan> call(TableColumn.CellDataFeatures<Reservation, bonplan> param) {
////                return new ReadOnlyObjectWrapper(param.getValue().getBonplan2().getTitre_bonplan());
////            }
////        });
////       
////
////        
////    }
////
////    @FXML
////    private void rs(ActionEvent event) throws IOException {
////         FXMLLoader loader = new FXMLLoader();
////
////        loader.setLocation(getClass().getResource("AfficherReservationBack.fxml"));
////        Parent root = loader.load();
////        res.getScene().setRoot(root);
////    }
////
////    @FXML
////    private void bp(ActionEvent event) throws IOException {
////         FXMLLoader loader = new FXMLLoader();
////
////        loader.setLocation(getClass().getResource("bonplanbacklist.fxml"));
////        Parent root = loader.load();
////        bpp.getScene().setRoot(root);
////    }
////
////    @FXML
////    private void avis(ActionEvent event) throws IOException {
////         FXMLLoader loader = new FXMLLoader();
////
////        loader.setLocation(getClass().getResource("avisback.fxml"));
////        Parent root = loader.load();
////        aviss.getScene().setRoot(root);
////    }
////
////    @FXML
////    private void boutonConfirmer(ActionEvent event) throws SQLException {
////        if (TableView.getSelectionModel().getSelectedItem() != null) {
////             
////            Reservation r=TableView.getSelectionModel().getSelectedItem();
////            ReservationCrud resvS = new ReservationCrud();
////            System.out.println(r.getId_reservation());
////            r.setStatut_reservation("confrimee");
////            resvS.modifierReservation(r);
////            Alert alert = new Alert(Alert.AlertType.ERROR);
////            alert.setTitle("information Dialog");
////            alert.setHeaderText(null);
////            alert.setContentText("confirmation de reservation");
////            alert.show();
////         
////              Afficher();   
////           
////        
////         } else {
////            Alert alert = new Alert(Alert.AlertType.ERROR);
////            alert.setTitle("information Dialog");
////            alert.setHeaderText(null);
////            alert.setContentText("Vous devez selectionner une categorie");
////            alert.show();
////        }
////    }
////
////    @FXML
////    private void boutonRefuser(ActionEvent event) throws SQLException {
////         if (TableView.getSelectionModel().getSelectedItem() != null) {
////             
////            Reservation r=TableView.getSelectionModel().getSelectedItem();
////            ReservationCrud resvS = new ReservationCrud();
////            r.setStatut_reservation("refusee");
////            resvS.modifierReservation(r);
////            Alert alert = new Alert(Alert.AlertType.ERROR);
////            alert.setTitle("information Dialog");
////            alert.setHeaderText(null);
////            alert.setContentText("refus de reservation");
////            alert.show();
////         
////              Afficher();   
////           
////        
////         } else {
////            Alert alert = new Alert(Alert.AlertType.ERROR);
////            alert.setTitle("information Dialog");
////            alert.setHeaderText(null);
////            alert.setContentText("Vous devez selectionner une categorie");
////            alert.show();
////        }
////        
////    }
////
////    @FXML
////    private void datedec(MouseEvent event) {
////        ReservationCrud resCRUD = new  ReservationCrud();
////
////        ObservableList<Reservation> liste = FXCollections.
////                observableArrayList((resCRUD.triepardatedecr()));
////
////        TableView.setItems(liste);
////
////        /* add column to the tableview and set its items */
////        nombre.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, Integer>, ObservableValue<Integer>>() {
////
////            @Override
////            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Reservation, Integer> param) {
////                return new ReadOnlyObjectWrapper(param.getValue().getNombre_personnes());
////            }
////        });
////        
////        date.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, java.sql.Date>, ObservableValue<java.sql.Date>>() {
////            @Override
////            public ObservableValue<java.sql.Date> call(TableColumn.CellDataFeatures<Reservation, java.sql.Date> param) {
////                return new ReadOnlyObjectWrapper(param.getValue().getDate_reservation());
////            }
////        });
////        
////
////        statut.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
////            @Override
////            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reservation, String> param) {
////                return new ReadOnlyObjectWrapper(param.getValue().getStatut_reservation());
////            }
////        });
////         nom.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, Utilisateur>, ObservableValue<Utilisateur>>() {
////            @Override
////            public ObservableValue<Utilisateur> call(TableColumn.CellDataFeatures<Reservation, Utilisateur> param) {
////                return new ReadOnlyObjectWrapper(param.getValue().getUser2().getNom());
////            }
////        });
////          lieu.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, bonplan>, ObservableValue<bonplan>>() {
////            @Override
////            public ObservableValue<bonplan> call(TableColumn.CellDataFeatures<Reservation, bonplan> param) {
////                return new ReadOnlyObjectWrapper(param.getValue().getBonplan2().getTitre_bonplan());
////            }
////        });
////       
////        
////    }
////
////    @FXML
////    private void datecroi(MouseEvent event) {
////ReservationCrud resCRUD = new  ReservationCrud();
////
////        ObservableList<Reservation> liste = FXCollections.
////                observableArrayList((resCRUD.triepardatecrois()));
////
////        TableView.setItems(liste);
////
////        /* add column to the tableview and set its items */
////        nombre.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, Integer>, ObservableValue<Integer>>() {
////
////            @Override
////            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Reservation, Integer> param) {
////                return new ReadOnlyObjectWrapper(param.getValue().getNombre_personnes());
////            }
////        });
////        
////        date.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, java.sql.Date>, ObservableValue<java.sql.Date>>() {
////            @Override
////            public ObservableValue<java.sql.Date> call(TableColumn.CellDataFeatures<Reservation, java.sql.Date> param) {
////                return new ReadOnlyObjectWrapper(param.getValue().getDate_reservation());
////            }
////        });
////        
////
////        statut.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
////            @Override
////            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reservation, String> param) {
////                return new ReadOnlyObjectWrapper(param.getValue().getStatut_reservation());
////            }
////        });
////         nom.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, Utilisateur>, ObservableValue<Utilisateur>>() {
////            @Override
////            public ObservableValue<Utilisateur> call(TableColumn.CellDataFeatures<Reservation, Utilisateur> param) {
////                return new ReadOnlyObjectWrapper(param.getValue().getUser2().getNom());
////            }
////        });
////          lieu.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, bonplan>, ObservableValue<bonplan>>() {
////            @Override
////            public ObservableValue<bonplan> call(TableColumn.CellDataFeatures<Reservation, bonplan> param) {
////                return new ReadOnlyObjectWrapper(param.getValue().getBonplan2().getTitre_bonplan());
////            }
////        });
////       
////
////        
////    }
////    }

@FXML
private ListView<Reservation> ListView;
@FXML
private TextField searchTextField;
@FXML
private Button Home1;
@FXML
private Button categorie1;
@FXML
private Button produit1;
@FXML
private Button comm1;
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
private Button res;
@FXML
private Button bpp;
@FXML
private Button aviss;
@FXML
private Button confirmer;
@FXML
private Button refuser;
ObservableList<Reservation> commandeObservableList = FXCollections.observableArrayList();
    @FXML
    private TableView<?> TableView;
    @FXML
    private TableColumn<?, ?> nombre;
    @FXML
    private TableColumn<?, ?> statut;
    @FXML
    private TableColumn<?, ?> date;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> lieu;
    @FXML
    private ImageView datetrie;

/**
 * Initializes the controller class.
 */
@Override
public void initialize(URL url, ResourceBundle rb) {
    ReservationCrud cc = new ReservationCrud();
    commandeObservableList = FXCollections.observableList(cc.afficherReservation());
    ListView.setItems(commandeObservableList);

    //search bar 
    FilteredList<Reservation> filteredData = new FilteredList<>(commandeObservableList, b -> true);
    searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(Reservation -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            String searchKeywords = newValue.toLowerCase();

            if (Reservation.getStatut_reservation().toLowerCase().contains(searchKeywords)) {
                return true;
            }
            if ((Reservation.getDate_reservation() + "").toLowerCase().contains(searchKeywords)) {
                return true;
            }
            return (Reservation.getNombre_personnes()+ "").toLowerCase().contains(searchKeywords);
        });
    });
    // wrap the FilteredList in a SortedList.
    SortedList<Reservation> sortedList = new SortedList<>(filteredData);
    ListView.setItems(sortedList);
}

    @FXML
    private void boutonConfirmer(ActionEvent event) {
    }

    @FXML
    private void boutonRefuser(ActionEvent event) {
    }

    @FXML
    private void rs(ActionEvent event) {
    }

    @FXML
    private void bp(ActionEvent event) {
    }

    @FXML
    private void avis(ActionEvent event) {
    }

    @FXML
    private void datedec(MouseEvent event) {
    }

    @FXML
    private void datecroi(MouseEvent event) {
    }
}
