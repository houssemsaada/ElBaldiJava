/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.categorie;
import elbaldi.models.produit;
import elbaldi.services.CategorieCRUD;
import elbaldi.services.ProduitCRUD;
import elbaldi.services.Upload;
import elbaldi.utils.MyConnection;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Yasmine
 */
public class ModifierProduitBackController implements Initializable {

    @FXML
    private Pane pnlOverview;
    @FXML
    private ListView img;
    @FXML
    private Button insérer;
    @FXML
    private TextArea descriptionfx;
    @FXML
    private TextField prixfx;
    @FXML
    private ComboBox<categorie> categoriefx;
    @FXML
    private TextField reffx;
    @FXML
    private TextField libellefx;
    private TextField quantitefx;
    File selectedfile;

    String path_img;

    Upload u = new Upload();
    public static produit produitP;
    private String Refproduit;
    @FXML
    private Button Modifierfx;
    @FXML
    private ImageView imgg;

    public String getRefProduitP() {
        return Refproduit;
    }

    public void setRefProduitP(String refpro) {
        this.Refproduit = refpro;
    }

    public void setProduit(produit p) {
        produitP = p;
        this.img.setAccessibleText(produitP.getImage());
        //String imageURI = new File("C:\\xampp\\htdocs\\images"+p.getImage().toString()).toURI().toString();
        //System.out.println(imageURI);
        this.prixfx.setText(produitP.getPrix_vente() + "");
        this.reffx.setText(produitP.getRef_produit());
        this.descriptionfx.setText(produitP.getDescription());
        this.libellefx.setText(produitP.getLibelle());
        categoriefx.getSelectionModel().select(produitP.getCategoriee());
        
         String imagePath = "C:\\xampp\\htdocs\\images\\"+ produitP.getImage().toString();
       
        // Create an ImageView object
        ImageView imageView = new ImageView();
        // Create a File object with the path of your image
        File file = new File(imagePath);
       
        // Check if the file exists
        if (file.exists()) {
            // Create an Image object with the file path
            
            
            Image image = new Image(file.toURI().toString());
            // Set the image to the ImageView
            this.imgg.setImage(image);
        } else {
            System.out.println("Image not found.");
        }
 
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ListerCategorie();

        insérer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                FileChooser fc = new FileChooser();
                fc.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("image", "*.jpg", "*.png")
                );
                selectedfile = fc.showOpenDialog(null);
                if (selectedfile != null) {
                    try {
                        resizeFile(selectedfile.getAbsolutePath(), "C:\\xampp\\htdocs\\images\\" + selectedfile.getName(), 227, 207);

                        img.getItems().add(selectedfile.getName());

                        path_img = selectedfile.getAbsolutePath();
                        File file = new File(path_img);

                        // Check if the file exists
                        if (file.exists()) {

                            // Create an Image object with the file path
                            Image image = new Image(file.toURI().toString());

                            // Set the image to the ImageView
                            imgg.setImage(image);
                        } else {
                            System.out.println("Image not found.");
                        }

                    } catch (IOException ex) {
                        Logger.getLogger(AjouterProduitBackController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    System.out.println("Fichier erroné");
                }

            }

        });
        Modifierfx.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                ProduitCRUD produitcrud = new ProduitCRUD();

                float prixx = Float.parseFloat(prixfx.getText());

                categorie c = categoriefx.getSelectionModel().getSelectedItem();
                String imgN = img.getItems().toString();
                imgN = imgN.substring(1, imgN.length() - 1);
                produit p = new produit(produitP.getRef_produit(), libellefx.getText(), descriptionfx.getText(), imgN, prixx, c);
                System.out.println(img.getItems().toString());
                if (!libellefx.getText().equalsIgnoreCase("")
                        && !descriptionfx.getText().equalsIgnoreCase("")
                        && !reffx.getText().equalsIgnoreCase("")
                        && Float.parseFloat(prixfx.getText()) > 0
                        ) {

                    try {

                        produitcrud.modifierProduit(p);

                    } catch (SQLException ex) {
                        Logger.getLogger(ModifierProduitBackController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
                    alert0.setTitle("information Dialog");
                    alert0.setHeaderText(null);
                    alert0.setContentText("Votre modification est enregistrée avec succes ");
                    alert0.show();
                    ((Node) event.getSource()).getScene().getWindow().hide();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Echec de la modification");
                    alert.setHeaderText(null);
                    alert.setContentText("Attention ! Verifier les données saisie (Pas de champs vides)");
                    alert.showAndWait();
                }
            }

        });
    }

    @FXML
    private void prefixe(MouseEvent event) {
    }

    private void ListerCategorie() {

        CategorieCRUD categoriecrud = new CategorieCRUD();
        ObservableList<categorie> list = FXCollections.observableArrayList();
        try {
            String req = " select id_categorie,`nom_categorie`,`description` from `categorie`  ";

            Connection conn = MyConnection.getInstance().getConn();

            PreparedStatement pst = conn.prepareStatement(req);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                categorie c = new categorie(rs.getInt(1), rs.getString(2), rs.getString(3));
                list.add(c);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        categoriefx.setItems(null);
        categoriefx.setItems(list);
    }

    public void resizeFile(String imagePathToRead,
            String imagePathToWrite, int resizeWidth, int resizeHeight)
            throws IOException {

        File fileToRead = new File(imagePathToRead);
        BufferedImage bufferedImageInput = ImageIO.read(fileToRead);

        BufferedImage bufferedImageOutput = new BufferedImage(resizeWidth,
                resizeHeight, bufferedImageInput.getType());

        Graphics2D g2d = bufferedImageOutput.createGraphics();
        g2d.drawImage(bufferedImageInput, 0, 0, resizeWidth, resizeHeight, null);
        g2d.dispose();

        String formatName = imagePathToWrite.substring(imagePathToWrite
                .lastIndexOf(".") + 1);

        ImageIO.write(bufferedImageOutput, formatName, new File(imagePathToWrite));
    }

}
