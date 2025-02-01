/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;


import elbaldi.models.*;
import static elbaldi.models.Role.admin;
import static elbaldi.models.Role.client;
import elbaldi.services.*;
import elbaldi.utils.MyConnection;
import java.io.IOException;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author USER
 */
public class elbaldi extends Application{

      @Override
    public void start(Stage primaryStage) throws IOException {
       
        try {
            MyConnection.getInstance();
            Parent root =FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
            Scene scene = new Scene(root);
            //scene.getStylesheets().add(getClass().getResource("../gui/Styles/controls.css").toExternalForm());
           primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(elbaldi.class.getName()).log(Level.SEVERE, null, ex);
        }  

}

    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
