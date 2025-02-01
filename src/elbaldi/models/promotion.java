/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.models;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author selim
 */
public class promotion {
    
    private int id_promotion;
    private String code_promo;
    private float taux;
private Utilisateur u ; 
    
    public promotion() {
    }

    public promotion(String code_promo, float taux,Utilisateur u) {
        this.code_promo = code_promo;
        this.taux = taux;
        this.u=u;
    }

    
    
    public promotion(int id_promotion) {
        this.id_promotion = id_promotion;
    }

    public Utilisateur getU() {
        return u;
    }

    public void setU(Utilisateur u) {
        this.u = u;
    }

    


    public int getId_promotion() {
        return id_promotion;
    }

    public void setId_promotion(int id_promotion) {
        this.id_promotion = id_promotion;
    }

    public String getCode_promo() {
        return code_promo;
    }

    public void setCode_promo(String code_promo) {
        this.code_promo = code_promo;
    }

    public float getTaux() {
        return taux;
    }

    public void setTaux(float taux) {
        this.taux = taux;
    }

    @Override
    public String toString() {
        return "promotion{" + "id_promotion=" + id_promotion + ", code_promo=" + code_promo + ", taux=" + taux + ", u=" + u + '}';
    }



    
    
}