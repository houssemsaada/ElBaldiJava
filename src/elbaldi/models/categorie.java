/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.models;

/**
 *
 * @author Yasmine
 */
public class categorie  {
   private int id_categorie ;
   private String nom_categorie;
   private String Description;
   
   
    public categorie(){
    
    }

    public categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }
    
    public categorie(int id_categorie, String nom_categorie,String Description) {
        this.id_categorie = id_categorie;
        this.nom_categorie = nom_categorie;
        this.Description=Description;
    }
     public categorie(String nom_categorie,String Description) {
      
        this.nom_categorie = nom_categorie;
        this.Description=Description;
    }
    


    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getNom_categorie() {
        return nom_categorie;
    }

    public void setNom_categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    @Override
    public String toString() {
        return nom_categorie ;
    }

    
    
   
}
