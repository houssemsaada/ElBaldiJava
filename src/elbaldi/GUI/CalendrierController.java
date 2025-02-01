/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
 import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * FXML Controller class
 *
 * @author user
 */
public class CalendrierController implements Initializable {



    @FXML
    private WebView webView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WebEngine engine = webView.getEngine();
        engine.load("https://calendar.google.com/calendar/embed?src=c_27049f60307e3e4ab783d31a9dc011f25a136d4abcd5b2e7330043a8b43cc33e%40group.calendar.google.com&ctz=Africa%2FTunis");
    }
}
