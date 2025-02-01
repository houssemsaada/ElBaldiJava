/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.models;

import java.time.LocalDate;

import java.sql.Date;

/**
 *
 * @author houss
 */
public class commande {

    private int id_cmd;
    private String etat;
    private Date date_cmd;
    private float total;
    private panier pan;
    private String adresse;

    //constructeur par defaut
    public commande() {
    }
    //constructeur parametre 

    public commande(panier pan, String etat, Date date_cmd) {
        this.pan = pan;
        this.etat = etat;
        this.date_cmd = date_cmd;
        this.total = pan.getTotal_panier();
    }

    public commande(panier pan) {
        this.pan = pan;
        this.total=pan.getTotal_panier();
    }
    

    public commande(int id_cmd, panier pan, String etat, Date date_cmd) {
        this.id_cmd = id_cmd;
        this.pan = pan;
        this.etat = etat;
        this.date_cmd = date_cmd;
        this.total = pan.getTotal_panier();

    }
    //getters

    public int getId_cmd() {
        return id_cmd;
    }

    public panier getPan() {
        return pan;
    }

    public String getEtat() {
        return etat;
    }

    public Date getDate_cmd() {
        return date_cmd;
    }
    //setters

    public void setId_cmd(int id_cmd) {
        this.id_cmd = id_cmd;
    }

    public void setPan(panier pan) {
        this.pan = pan;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(panier p) {
        this.total = pan.getTotal_panier();
    }
     public void setTotal2(float tot) {
        this.total = tot;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setDate_cmd(Date date_cmd) {
        this.date_cmd = date_cmd;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    @Override
    public String toString() {
        return "commande{" + "id_cmd=" + id_cmd + ", etat=" + etat + ", date_cmd=" + date_cmd + ", panier =" + pan +", total =" + total+ '}';
    }

}
