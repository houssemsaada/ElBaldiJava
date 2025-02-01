/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

/**
 *
 * @author USER
 */
import java.net.URL;
import java.util.Arrays;
import facebook4j.*;
import facebook4j.auth.AccessToken;

public class FacebookPublisher {

    public static void main(String[] args) {

        // Configure l'application Facebook
        String appId = "139023022421712";
        String appSecret = "VOTRE_APP_SECRET";
        String accessToken = "EAAakIZCni0c8BAOhEZCzbiEq2PvUBWvffoBWpoMDP7oj0w1oLLb4HYMzLAUaZAUHxhpSuCqf4KfF3pYEAE4PE3GW8mmdsepDWmrVYYy8KuT4UZBCQE0Jxb8MU2PzUuSHArtA8ZCoP9ZBXoUvkDjd29uuHs9sX2ZCjVNPNyucYSJw8slU5kp5rgzRDRC366TLCATTJFYQ55xzgZDZD";
        Facebook facebook = new FacebookFactory().getInstance();
        facebook.setOAuthAppId(appId, appSecret);
        facebook.setOAuthAccessToken(new AccessToken(accessToken, null));

        // Configure la publication de contenu sur la page Facebook
        String pageId = "224950153267944";
        String productName = "Nom de votre produit";
        double productPrice = 9.99;
        String message = "Découvrez notre nouveau produit : " + productName + " au prix de " + productPrice + " €.";

        // Télécharge l'image du produit
        try {
//            URL imageUrl = new URL("URL_DE_L_IMAGE_DU_PRODUIT");
//            Media media = new Media("");
//
//            // Publie le contenu sur la page Facebook
//            facebook.postPhoto(media, message, new Reading().id("139023022421712"));
//            System.out.println("La photo a été publiée avec succès sur votre page Facebook !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
