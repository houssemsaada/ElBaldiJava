/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;



import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author houss
 */
public class commandeGUI extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {
     //  Parent  root = FXMLLoader.load(getClass().getResource("consulterPanier.fxml"));
        // Parent root = FXMLLoader.load(getClass().getResource("livraisoninterface.fxml"));
      //  Parent root = FXMLLoader.load(getClass().getResource("commandeinterface.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("templateBack.fxml"));
            System.out.println("aa");
            System.out.println("bb");
            System.out.println("cc");
            Scene scene = new Scene(root);
            primaryStage.setTitle("commande");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void AlertShow(String text, String title, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(text);
        alert.setTitle(title);
        alert.show();
    }

    public static boolean isTextFieldEmpty(TextField... fields) {
        for (TextField f : fields) {
            if (f == null || f.getText().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static void clearTextFields(TextField... fields) {
        for (TextField f : fields) {
            f.clear();
        }
    }

    public static void changeScene(ActionEvent event, String fxml_file, String title) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(commandeGUI.class.getResource(fxml_file));
            Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene1 = new Scene(fxmlLoader.load());
            stage1.setTitle(title);
            stage1.setScene(scene1);
            stage1.setResizable(false);
            stage1.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
