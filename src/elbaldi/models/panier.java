/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.models;

/**
 *
 * @author houss
 */
public class panier {

    private int id_panier, nombre_article, quantite_produit;
    private String ref_produit ; 
    private float total_panier;
    //constructeur par defaut

    public panier() {
    }
    ///constructeur parametre 

    public panier(int id_panier, String ref_produit, int nombre_article, int quantite_produit, float total_panier) {
        this.id_panier = id_panier;
        this.ref_produit = ref_produit;
        this.nombre_article = nombre_article;
        this.quantite_produit = quantite_produit;
        this.total_panier = total_panier;
    }

    public panier(String ref_produit, int nombre_article, int quantite_produit, float total_panier) {
        this.ref_produit = ref_produit;
        this.nombre_article = nombre_article;
        this.quantite_produit = quantite_produit;
        this.total_panier = total_panier;
    }
    //getters

    public int getId_panier() {
        return id_panier;
    }

    public String getRef_produit() {
        return ref_produit;
    }



    public int getNombre_article() {
        return nombre_article;
    }

    public int getQuantite_produit() {
        return quantite_produit;
    }

    public float getTotal_panier() {
        return total_panier;
    }
    //setters

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public void setRef_produit(String ref_produit) {
        this.ref_produit = ref_produit;
    }

    

    public void setNombre_article(int nombre_article) {
        this.nombre_article = nombre_article;
    }

    public void setQuantite_produit(int quantite_produit) {
        this.quantite_produit = quantite_produit;
    }

    public void setTotal_panier(float total_panier) {
        this.total_panier = total_panier;
    }

    @Override
    public String toString() {
        return "panier{" + "id_panier=" + id_panier + ", nombre_article=" + nombre_article + ", quantite_produit=" + quantite_produit + ", ref_produit=" + ref_produit + ", total_panier=" + total_panier + '}';
    }

    

}
