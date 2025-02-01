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

public class avis {
    private int id_avis;
    private float note_avis;
    private Date date_avis;
    private  Utilisateur user1;
    private bonplan bonplan1;

    public avis() {
    }

    public avis(float note_avis, Date date_avis, Utilisateur user1, bonplan bonplan1) {
        this.note_avis = note_avis;
        this.date_avis = date_avis;
        this.user1 = user1;
        this.bonplan1 = bonplan1;
    }

    public avis(int id_avis) {
        this.id_avis = id_avis;
    }

    public int getId_avis() {
        return id_avis;
    }

    public void setId_avis(int id_avis) {
        this.id_avis = id_avis;
    }

    public float getNote_avis() {
        return note_avis;
    }

    public void setNote_avis(float note_avis) {
        this.note_avis = note_avis;
    }

    public Date getDate_avis() {
        return date_avis;
    }

    public void setDate_avis(Date date_avis) {
        this.date_avis = date_avis;
    }

    public Utilisateur getUser1() {
        return user1;
    }

    public void setUser1(Utilisateur user1) {
        this.user1 = user1;
    }

    public bonplan getBonplan1() {
        return bonplan1;
    }

    public void setBonplan1(bonplan bonplan1) {
        this.bonplan1 = bonplan1;
    }

    @Override
    public String toString() {
        return "avis{" + "id_avis=" + id_avis + ", note_avis=" + note_avis + ", date_avis=" + date_avis + ", user1=" + user1 + ", bonplan1=" + bonplan1 + '}';
    }
  
    
    

}



    