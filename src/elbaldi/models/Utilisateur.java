/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.models;

/**
 *
 * @author mEtrOpOliS
 */


public class Utilisateur {
    private int id_user;
    private String nom;
    private String prenom;
    private String email;
    private String dateNaissance;
    private int numTel;
    private String ville;
    private String login;
    private String mdp;
    private Role role;

    public Utilisateur() {
    }

    public Utilisateur(int id_user, String nom, String prenom, String email, String dateNaissance, int numTel, String ville, String login, String mdp, Role role) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.numTel = numTel;
        this.ville = ville;
        this.login = login;
        this.mdp = mdp;
        this.role = role;
    }

    public Utilisateur(String nom, String prenom, String email, String dateNaissance, int numTel, String ville, String login, String mdp, Role role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.numTel = numTel;
        this.ville = ville;
        this.login = login;
        this.mdp = mdp;
        this.role = role;
    }

    public int getid_user() {
        return id_user;
    }

    public void setid_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id_user=" + id_user + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", dateNaissance=" + dateNaissance + ", numTel=" + numTel + ", ville=" + ville + ", login=" + login + ", mdp=" + mdp + ", role=" + role + '}';
    }
    
    
}


