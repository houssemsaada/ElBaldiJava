/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.produit;
import elbaldi.services.CommandeCRUD;
import elbaldi.services.ProduitCRUD;
import elbaldi.services.livraisonCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class templatebackController implements Initializable {

    @FXML
    private Button Accueilfx;
    @FXML
    private Button profilfx;
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
    @FXML
    private Button GestUser;
    @FXML
    private Button Decofx;
    @FXML
    private Label pendorderlabel;
    @FXML
    private Label livraisonpendlabel;
    @FXML
    private Label totalsaleslabel;
    CommandeCRUD cc = new CommandeCRUD();
    livraisonCRUD lc = new livraisonCRUD();
    ProduitCRUD pc=new ProduitCRUD();
    @FXML
    private PieChart piechart;
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    ProduitCRUD prod = new ProduitCRUD();
    @FXML
    private Label totalproduits;
    @FXML
    private BarChart<String, Integer> barchar;

    List<produit> prodlist = new ArrayList<produit>();
    @FXML
    private Button prodmin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pendorderlabel.setText("  " + cc.pendingorders());
        livraisonpendlabel.setText("  " + lc.pendingliv());
        totalsaleslabel.setText(cc.totalsales() + " TND");
        totalproduits.setText(pc.prodCount()+" ");
        
        pieChartData.clear();

        ResultSet rs = prod.categorieprodcount();
        try {
            while (rs.next()) {
                String category = rs.getString("nom_categorie");
                int count = rs.getInt("product_count");
//                System.out.println(category + ": " + count);
                PieChart.Data data = new PieChart.Data(category, count);
                pieChartData.add(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(templatebackController.class.getName()).log(Level.SEVERE, null, ex);
        }
        piechart.setData(pieChartData);
        XYChart.Series series1 = new XYChart.Series();
        LocalDate localDate = LocalDate.now(); // Get current local date
        Month month = localDate.getMonth(); // Extract month from local date
        System.out.println("Month: " + month);
        series1.setName("Month: " + month);
        prodlist = cc.top5prod();
        for (int j = 0; j < prodlist.size(); j++) {
            series1.getData().add(new XYChart.Data<>(prodlist.get(j).getRef_produit(), prodlist.get(j).getQuantite()));
        }
        barchar.getData().addAll(series1);
    }

    @FXML
    private void accueilAction(ActionEvent event
    ) {
        commandeGUI.changeScene(event, "templateBack.fxml", "Acceuil");

    }

    @FXML
    private void profilAction(ActionEvent event
    ) {
        commandeGUI.changeScene(event, "ProfileAdmin.fxml", "Profile");

    }

    @FXML
    private void categ(ActionEvent event
    ) {
        commandeGUI.changeScene(event, "catgeorielistback.fxml", "Categories");

    }

    @FXML
    private void prodd(ActionEvent event
    ) {
        commandeGUI.changeScene(event, "prodbacklist.fxml", "Produits");

    }

    @FXML
    private void commandesAction(ActionEvent event
    ) {

        commandeGUI.changeScene(event, "commandeinterface.fxml", "commande ");
    }

    @FXML
    private void LivraisonAction(ActionEvent event
    ) {
        commandeGUI.changeScene(event, "livraisoninterface.fxml", "livraison ");

    }

    @FXML
    private void BonpalnsAction(ActionEvent event
    ) {
        commandeGUI.changeScene(event, "bonplanbacklist.fxml", "bonplans ");

    }

    @FXML
    private void QuizAction(ActionEvent event
    ) {
        commandeGUI.changeScene(event, "front.fxml", "quiz ");

    }

    @FXML
    private void eventaction(ActionEvent event
    ) {
        commandeGUI.changeScene(event, "AjouterEvenement.fxml", "évènements ");

    }

    @FXML
    private void participationaction(ActionEvent event
    ) {
        commandeGUI.changeScene(event, "afficher participation.fxml", "participation ");

    }

    @FXML
    private void GestuserAction(ActionEvent event
    ) {
        commandeGUI.changeScene(event, "MenuAdmin.fxml", "gestion utilisateurs ");

    }

    @FXML
    private void decoAction(ActionEvent event
    ) {
        commandeGUI.changeScene(event, "Front1.fxml", "Visiteur ");
    }

    @FXML
    private void prodmin(ActionEvent event) throws IOException {
             FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("minprods.fxml"));
        Parent root = loader.load();

        MinprodsController cb = loader.getController();

        Stage stage = new Stage();
        stage.setTitle("Les produits les moins vendus");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
    }

}
