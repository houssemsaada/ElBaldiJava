/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.Upload2;
import elbaldi.models.bonplan;
import elbaldi.models.categorie;
import elbaldi.services.BonplanCrud;
import elbaldi.services.CategorieCRUD;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ModifierBonplanBackController implements Initializable {

    @FXML
    private ListView imageBP;
    @FXML
    private Button insérer;
    @FXML
    private TextArea descriptionBP;
    @FXML
    private Button annuler;
    @FXML
    private TextField titreBP;
    private TextField typeBP;
    @FXML
    private ImageView img;
    @FXML
    private Button modifier;
    
     public static bonplan bnp;
    
    File selectedfile;
    String path_img;

    Upload2 u = new Upload2();
    @FXML
    private ComboBox<String> typeFx;
    /**
     * Initializes the controller class.
     */
    public void setCategorie(bonplan c) throws IOException {
        bnp=c;
        this.titreBP.setText(c.getTitre_bonplan());
        this.descriptionBP.setText(c.getDescription_bonplan());
        this.typeFx.getSelectionModel().select(c.getType_bonplan());
        //this.imageBP.setAccessibleText();
        String imageURI = new File("C:\\xampp\\htdocs\\images"+c.getImage_bonplan().toString()).toURI().toString();
        System.out.println(imageURI);
  
        
        String imagePath = "C:\\xampp\\htdocs\\images\\"+c.getImage_bonplan().toString();
        
        // Create an ImageView object
        ImageView imageView = new ImageView();
        
        // Create a File object with the path of your image
        File file = new File(imagePath);
        
        // Check if the file exists
        if (file.exists()) {
            
            // Create an Image object with the file path
            Image image = new Image(file.toURI().toString());
            
            // Set the image to the ImageView
            imageView.setImage(image);
            this.img.setImage(image);
        } else {
            System.out.println("Image not found.");
        }



    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typeFx.getItems().add("restaurant");
        typeFx.getItems().add("Hotel");
        // TODO
        
    }    

 

    @FXML
    private void modifierBP(ActionEvent event) throws IOException {
        BonplanCrud bp= new BonplanCrud();
                bonplan c = new bonplan();
                c.setId_bonplan(bnp.getId_bonplan());
                c.setTitre_bonplan(titreBP.getText());
                c.setDescription_bonplan(descriptionBP.getText());
                c.setType_bonplan(typeFx.getSelectionModel().getSelectedItem().toString());
                    if (selectedfile != null) {
                c.setImage_bonplan(selectedfile.getName());
                    }
                    else{
                         c.setImage_bonplan(bnp.getImage_bonplan());
                    }
                
               bp.modifierBonplan(c);

             
                Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
                alert0.setTitle("information Dialog");
                alert0.setHeaderText(null);
                alert0.setContentText("Votre modification a été enregistrée avec succés ");
                alert0.show();
                ((Node) event.getSource()).getScene().getWindow().hide();
                

               

    }

    @FXML
    private void insererOnClick(ActionEvent event) {
        
                FileChooser fc = new FileChooser();
            fc.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("image", "*.jpg", "*.png")
            );
            selectedfile = fc.showOpenDialog(null);
            if (selectedfile != null) {

                Upload2 u = new Upload2();
                try {
                    u.upload(selectedfile);
                } catch (IOException ex) {
                    Logger.getLogger(AjoutBonplanBackController.class.getName()).log(Level.SEVERE, null, ex);
                }
                imageBP.getItems().add(selectedfile.getName());

                path_img = selectedfile.getAbsolutePath();
                
                //String imagePath = "C:\\xampp\\htdocs\\images\\"+c.getImage_bonplan().toString();
        
        // Create an ImageView object
        ImageView imageView = new ImageView();
        
        // Create a File object with the path of your image
        File file = new File(path_img);
        
        // Check if the file exists
        if (file.exists()) {
            
            // Create an Image object with the file path
            Image image = new Image(file.toURI().toString());
            
            // Set the image to the ImageView
            imageView.setImage(image);
            this.img.setImage(image);
        } else {
            System.out.println("Image not found.");
        }

            } else {
                System.out.println("Fichier erroné");
            }

    }

    void setbonplan(bonplan bonplan1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
