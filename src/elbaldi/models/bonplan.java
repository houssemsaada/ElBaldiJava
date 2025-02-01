/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.models;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author user
 */
public class bonplan {
    

    private int id_bonplan;
    private String titre_bonplan;
    private String description_bonplan;
    private String type_bonplan;
    private String image_bonplan;
    private ImageView imgview;

    public bonplan() {
    }

    public bonplan(int id_bonplan) {
        this.id_bonplan = id_bonplan;
    }

    public bonplan(int id_bonplan, String titre_bonplan, String description_bonplan, String type_bonplan, String image_bonplan) {
        this.id_bonplan = id_bonplan;
        this.titre_bonplan = titre_bonplan;
        this.description_bonplan = description_bonplan;
        this.type_bonplan = type_bonplan;
        this.image_bonplan = image_bonplan;
    }

    public bonplan(String titre_bonplan, String description_bonplan, String type_bonplan, String image_bonplan) {
        this.titre_bonplan = titre_bonplan;
        this.description_bonplan = description_bonplan;
        this.type_bonplan = type_bonplan;
        this.image_bonplan = image_bonplan;
    }

    public bonplan(int id_bonplan, String titre_bonplan, String description_bonplan, String type_bonplan, String image_bonplan, ImageView imgview) {
        this.id_bonplan = id_bonplan;
        this.titre_bonplan = titre_bonplan;
        this.description_bonplan = description_bonplan;
        this.type_bonplan = type_bonplan;
        this.image_bonplan = image_bonplan;
        this.imgview = imgview;
    }

    public bonplan(String titre_bonplan, String description_bonplan, String type_bonplan) {
        this.titre_bonplan = titre_bonplan;
        this.description_bonplan = description_bonplan;
        this.type_bonplan = type_bonplan;
    }


    

    public int getId_bonplan() {
        return id_bonplan;
    }

    public void setId_bonplan(int id_bonplan) {
        this.id_bonplan = id_bonplan;
    }

    public String getTitre_bonplan() {
        return titre_bonplan;
    }

    public void setTitre_bonplan(String titre_bonplan) {
        this.titre_bonplan = titre_bonplan;
    }

    public String getDescription_bonplan() {
        return description_bonplan;
    }

    public void setDescription_bonplan(String description_bonplan) {
        this.description_bonplan = description_bonplan;
    }

    public String getType_bonplan() {
        return type_bonplan;
    }

    public void setType_bonplan(String type_bonplan) {
        this.type_bonplan = type_bonplan;
    }

    public String getImage_bonplan() {
        return image_bonplan;
    }

    public void setImage_bonplan(String image_bonplan) {
        this.image_bonplan = image_bonplan;
    }

    @Override
    public String toString() {
        return "bonplan{" + "id_bonplan=" + id_bonplan + ", titre_bonplan=" + titre_bonplan + ", description_bonplan=" + description_bonplan + ", type_bonplan=" + type_bonplan + ", image_bonplan=" + image_bonplan + ", imgview=" + imgview + '}';
    }




    

    public ImageView getImgview() {
        return imgview;
    }

    public void setImgview(ImageView imgview) {
        this.imgview = imgview;
        imgview.setFitHeight(100);
        imgview.setFitWidth(100);
        imgview.setPreserveRatio(false);
    }
    
      public static List<bonplan> generateImageViews(List<bonplan> bonplans) {
        List<bonplan> liste = new ArrayList<bonplan>();
        for (bonplan bp : bonplans) {
            File f = new File("C:\\xampp\\htdocs\\images\\"+ bp.getImage_bonplan());
            bp.setImgview(new ImageView(new Image(f.toURI().toString())));
            liste.add(bp);
        }
        return liste;
    }
    public static  ArrayList<bonplan> generateImageViews( ArrayList<bonplan> bonplans) {
         ArrayList<bonplan> liste = new ArrayList<bonplan>();

        for (bonplan bp : bonplans) {
            File f = new File("C:\\xampp\\htdocs\\images\\"+bp.getImage_bonplan());
            bp.setImgview(new ImageView(new Image(f.toURI().toString())));
            liste.add(bp);
        }
        return liste;
    }
     public static bonplan generateImageViews(bonplan bonplan) {
        
         bonplan bp;
        
            File f = new File("C:\\xampp\\htdocs\\images\\"+bonplan.getImage_bonplan());
            bonplan.setImgview(new ImageView(new Image(f.toURI().toString())));
            bp=bonplan;
        
        return bp;
    }

   
        
    }
    
    
    
    
    
    

    
    
    