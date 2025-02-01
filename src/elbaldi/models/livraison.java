/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.models;

import java.sql.Date ; 

/**
 *
 * @author houss
 */
public class livraison {
    private int id_livraison ;
    private commande c1 ; 
    private String status_livraison,adresse_livraison;
    private Date  date_livraison ; 
    //constructeur par defaut

    public livraison() {
    }
    ///constructeur parametre 

    public livraison(int id_livraison, String status_livraison, String adresse_livraison, Date  date_livraison,commande c1) {
        this.id_livraison = id_livraison;
        this.c1 = c1;
        this.status_livraison = status_livraison;
        this.adresse_livraison = adresse_livraison;
        this.date_livraison = date_livraison;
    }

    public livraison( String status_livraison, String adresse_livraison, Date  date_livraison,commande c1) {
        this.c1 = c1;
        this.status_livraison = status_livraison;
        this.adresse_livraison = adresse_livraison;
        this.date_livraison = date_livraison;
    }
       //getters
    public int getId_livraison() {
        return id_livraison;
    }

    public commande getC1() {
        return c1;
    }

    public String getStatus_livraison() {
        return status_livraison;
    }

    public String getAdresse_livraison() {
        return adresse_livraison;
    }

    public Date  getDate_livraison() {
        return date_livraison;
    }
    //setters
    public void setId_livraison(int id_livraison) {
        this.id_livraison = id_livraison;
    }

    public void setC1(commande c1) {
        this.c1 = c1;
    }


    public void setStatus_livraison(String status_livraison) {
        this.status_livraison = status_livraison;
    }

    public void setAdresse_livraison(String adresse_livraison) {
        this.adresse_livraison = adresse_livraison;
    }

    public void setDate_livraison(Date  date_livraison) {
        this.date_livraison = date_livraison;
    }

    @Override
    public String toString() {
        return "livraison{" + "id_livraison=" + id_livraison + ", commande=" + c1 + ", status_livraison=" + status_livraison + ", adresse_livraison=" + adresse_livraison + ", date_livraison=" + date_livraison + '}';
    }

    
    
   
}
