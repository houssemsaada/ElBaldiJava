/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.models;

/**
 *
 * @author selim
 */
public class quiz {
   private int id_quiz;
   private String nom ;
   private String difficulte;
   private int score;

   private Utilisateur id_user;
   private String imgview;
   
    public quiz() {
    }

    public quiz(String nom, String difficulte) {
        this.nom = nom;
        this.difficulte = difficulte;
    }

    
    
    
    public quiz(String nom, String difficulte, Utilisateur id_user, String imgview) {
        this.nom = nom;
        this.difficulte = difficulte;
        this.id_user = id_user;
        this.imgview = imgview;
    }

    
    public quiz(int id_quiz, String nom, String difficulte, int score, Utilisateur id_user, String imgview) {
        this.id_quiz = id_quiz;
        this.nom = nom;
        this.difficulte = difficulte;
        this.score = score;
        this.id_user = id_user;
        this.imgview = imgview;
    }

    public quiz(String nom, String difficulte, int score, Utilisateur id_user, String imgview) {
        this.nom = nom;
        this.difficulte = difficulte;
        this.score = score;
        this.id_user = id_user;
        this.imgview = imgview;
    }

    public quiz(String nom, String difficulte, String imgview) {
        this.nom = nom;
        this.difficulte = difficulte;
        this.imgview = imgview;
    }

    
    
    public int getId_quiz() {
        return id_quiz;
    }

    public void setId_quiz(int id_quiz) {
        this.id_quiz = id_quiz;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(String difficulte) {
        this.difficulte = difficulte;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Utilisateur getuser() {
        return id_user;
    }

    public void setuser(Utilisateur id_user) {
        this.id_user = id_user;
    }

    public String getImgview() {
        return imgview;
    }

    public void setImgview(String imgview) {
        this.imgview = imgview;
    }

    @Override
    public String toString() {
        return   nom ;
    }


    
}