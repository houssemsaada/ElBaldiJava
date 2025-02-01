/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.models;

import java.util.Date;
/**
 *
 * @author user
 */
public class Reservation {
    private int id_reservation;
    private int nombre_personnes;
    private Date date_reservation;
    private String statut_reservation;
    private bonplan bonplan2;
    private Utilisateur  user2;

    public Reservation() {
    }

    public Reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }
    public Reservation(int nombre_personnes, Date date_reservation,bonplan bonplan2, Utilisateur user2) {
        this.nombre_personnes = nombre_personnes;
        this.date_reservation = date_reservation;
        this.bonplan2 = bonplan2;
        this.user2 = user2;
    }
    

    public Reservation(int nombre_personnes, Date date_reservation, String statut_reservation, bonplan bonplan2, Utilisateur user2) {
        this.nombre_personnes = nombre_personnes;
        this.date_reservation = date_reservation;
        this.statut_reservation = statut_reservation;
        this.bonplan2 = bonplan2;
        this.user2 = user2;
    }

    public Reservation(int nombre_personnes, java.sql.Date date_reservation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public int getNombre_personnes() {
        return nombre_personnes;
    }

    public void setNombre_personnes(int nombre_personnes) {
        this.nombre_personnes = nombre_personnes;
    }

    public Date getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(Date date_reservation) {
        this.date_reservation = date_reservation;
    }

    public String getStatut_reservation() {
        return statut_reservation;
    }

    public void setStatut_reservation(String statut_reservation) {
        this.statut_reservation = statut_reservation;
    }

    public bonplan getBonplan2() {
        return bonplan2;
    }

    public void setBonplan2(bonplan bonplan2) {
        this.bonplan2 = bonplan2;
    }

    public Utilisateur getUser2() {
        return user2;
    }

    public void setUser2(Utilisateur user2) {
        this.user2 = user2;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id_reservation=" + id_reservation + ", nombre_personnes=" + nombre_personnes + ", date_reservation=" + date_reservation + ", statut_reservation=" + statut_reservation + ", bonplan2=" + bonplan2 + ", user2=" + user2 + '}';
    }

    
    
    
    
    

}