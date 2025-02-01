/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.Reservation;
import elbaldi.services.ReservationCrud;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ShowReservationBackController implements Initializable {

    @FXML
    private GridPane grid;
    private List<Reservation> listeReservation = new ArrayList<>();
    private Date date;

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(date);
        afficher();
        //this.date=d;
        //System.out.println(date);
        // TODO
    }    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public void afficher() {
        try {
            ReservationCrud rs = new ReservationCrud();
            System.out.println(date);
            listeReservation = rs.afficherReservation(date);
                        System.out.println(listeReservation.size());

            int column = 0;
            int row = 1;
            for (int i = 0; i < listeReservation.size(); i++) {
                                        System.out.println("322");
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/elbaldi/GUI/reservationItemBack.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
                ReservationItemBackController itemController = fxmlLoader.getController();
                itemController.setData(listeReservation.get(i));
                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid.add(anchorpane, column++, row);
                GridPane.setMargin(anchorpane, new Insets(10));
            }
        } catch (IOException ex) {
        }
    }

}
