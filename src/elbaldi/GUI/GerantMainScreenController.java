/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.Reservation;
import elbaldi.models.Utilisateur;
import elbaldi.models.bonplan;
import elbaldi.services.ReservationCrud;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.YearMonth;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GerantMainScreenController implements Initializable {

    @FXML
    private Button deconnexionfx;
    @FXML
    private Button reservationfx;
   
    @FXML
    private AnchorPane anchor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ReservationCrud cc = new ReservationCrud();
            
            VBox f=  new FullCalendarView(YearMonth.now()).getView();
            anchor.getChildren().add(f);
        } catch (IOException ex) {
            Logger.getLogger(GerantMainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }


         
    }
   
 
       



    @FXML
    private void deconnexionAction(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("Front1.fxml"));
        Parent root = loader.load();
       deconnexionfx.getScene().setRoot(root);
    }

    @FXML
    private void reservationAction(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("GerantMainScreen.fxml"));
        Parent root = loader.load();
        reservationfx.getScene().setRoot(root);
    }
   
}
