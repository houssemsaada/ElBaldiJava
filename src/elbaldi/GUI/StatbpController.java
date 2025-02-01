/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.bonplan;
import elbaldi.services.BonplanCrud;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class StatbpController implements Initializable {

    @FXML
    private PieChart piechart;
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
      BonplanCrud es = new BonplanCrud();
      bonplan e1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       pieChartData.clear();

        ResultSet rs = es.ReservationBpcount();
        try {
            while (rs.next()) {
                String category = rs.getString("titre_bonplan");
                int count = rs.getInt("reservation_count");
//                System.out.println(category + ": " + count);
                PieChart.Data data = new PieChart.Data(category, count);
                pieChartData.add(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(templatebackController.class.getName()).log(Level.SEVERE, null, ex);
        }
        piechart.setData(pieChartData);
      
        
    }    
    
}
