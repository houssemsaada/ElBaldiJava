/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;



import elbaldi.models.Evenement;
import elbaldi.models.Participation;
import elbaldi.services.EvenementService;
import elbaldi.services.ParticipationService;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox; 
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import elbaldi.services.EvenementService;
import java.awt.Desktop;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.GridPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import javax.swing.DefaultRowSorter.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * FXML Controller class
 *
 * 
 */
public class AfficherEvenementController implements Initializable {

    @FXML
    private GridPane gridpane;
    EvenementService es = new EvenementService();
      Evenement e1;
    @FXML
    private Button retour_btn;
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
    private PieChart piechart;
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
             
            List<Evenement> events = es.recuperer();
            int row = 0;
            int column = 0;
            for (int i = 0; i < events.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Eventcontroleur.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
                EventcontroleurController controller = loader.getController();
                controller.setEvenement(events.get(i));
                

                gridpane.add(pane, column, row);
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }
            }
            pieChartData.clear();

        ResultSet rs = es.participationeventcount();
        try {
            while (rs.next()) {
                String category = rs.getString("nom");
                int count = rs.getInt("participation_count");
//                System.out.println(category + ": " + count);
                PieChart.Data data = new PieChart.Data(category, count);
                pieChartData.add(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(templatebackController.class.getName()).log(Level.SEVERE, null, ex);
        }
        piechart.setData(pieChartData);
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }    

    @FXML
    private void retournerHome(ActionEvent event) {
        
        try {
           

            Parent loader = FXMLLoader.load(getClass().getResource("AjouterEvenement.fxml"));
           gridpane.getScene().setRoot(loader);
       
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
    }

    private void afficherparticipation(ActionEvent event) {
        
        try {
           

            Parent loader = FXMLLoader.load(getClass().getResource("AfficherParticipation.fxml"));
           gridpane.getScene().setRoot(loader);
       
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
    }



   
    @FXML
    private void accueilAction(ActionEvent event) {
        commandeGUI.changeScene(event, "templateBack.fxml", "Acceuil");

    }

    @FXML
    private void profilAction(ActionEvent event) {
        commandeGUI.changeScene(event, "ProfileAdmin.fxml", "Profile");

    }

    @FXML
    private void categ(ActionEvent event) {
        commandeGUI.changeScene(event, "catgeorielistback.fxml", "Catégories");

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

    @FXML
    private void GestuserAction(ActionEvent event) {
            commandeGUI.changeScene(event, "MenuAdmin.fxml", "gestion utilisateurs ");

    }

    @FXML
    private void decoAction(ActionEvent event) {
        commandeGUI.changeScene(event, "Front1.fxml", "Visiteur ");
    }
    
     @FXML
    private void excel(ActionEvent event) throws SQLException {
        List<Evenement> list =  es.recuperer();

        // Create a new workbook and sheet
        // Create a new workbook and sheet
XSSFWorkbook workbook = new XSSFWorkbook();
String sheetName = "Evenement";
workbook.createSheet(sheetName);

// Create the header row
String[] headers = {"ID", "NOM", "DESCRIPTION", "AWARDS ", "Date de début", "Date de FIN"};
Row headerRow = workbook.getSheet(sheetName).createRow(0);
for (int i = 0; i < headers.length; i++) {
    Cell cell = headerRow.createCell(i);
    cell.setCellValue(headers[i]);
}

// Create the data rows
int rowNum = 1;

for (int i=0 ; i<list.size() ; i++) {
    Row row = workbook.getSheet(sheetName).createRow(rowNum++);
    row.createCell(0).setCellValue(list.get(i).getId_event());
    row.createCell(1).setCellValue(list.get(i).getNom());
    row.createCell(2).setCellValue(list.get(i).getDescription());
    row.createCell(3).setCellValue(list.get(i).getAwards());
    row.createCell(4).setCellValue(list.get(i).getDate_debut());
    row.createCell(5).setCellValue(list.get(i).getDate_fin());
}

// Show the file chooser dialog
Stage stage = (Stage) gridpane.getScene().getWindow();
FileChooser fileChooser = new FileChooser();
fileChooser.setTitle("Save Events");
fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx"));
File file = fileChooser.showSaveDialog(stage);
if (file != null) {
    // Save the workbook to the selected file
    try {
        FileOutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
        
        // Open the saved file using Desktop API
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(file);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    }
    
}
