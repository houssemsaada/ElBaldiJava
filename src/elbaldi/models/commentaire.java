/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.models;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Yasmine
 */
public class commentaire {
     private int id_commentaire;
    private String contenu;
    private Date date;
    private Utilisateur utilisateur;
     private produit produit;

    public commentaire() {
    }

    public commentaire(int id_commentaire, String contenu, produit produit, Utilisateur utilisateur, Date date) {
        this.id_commentaire = id_commentaire;
        this.contenu = contenu;
        this.produit = produit;
        this.utilisateur = utilisateur;
        this.date = date;
    }

    public commentaire(String contenu, produit produit, Utilisateur utilisateur, Date date) {
        this.contenu = contenu;
        this.produit = produit;
        this.utilisateur = utilisateur;
        this.date = date;
    }
     public commentaire(String contenu, produit produit, Utilisateur utilisateur) {
        this.contenu = contenu;
        this.produit = produit;
        this.utilisateur = utilisateur;
      
    }
     public commentaire(String contenu){
         this.contenu=contenu;
     }

    public commentaire(produit produitt, Utilisateur utilisateur, String texte, Timestamp date) {
       
    }

    public int getId_commentaire() {
        return id_commentaire;
    }

    public void setId_commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public produit getProduit() {
        return produit;
    }

    public void setProduit(produit produit) {
        this.produit = produit;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return  contenu +"\n Le "+ date ;

    }
}

