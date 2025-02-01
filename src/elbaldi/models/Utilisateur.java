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
 * @author mEtrOpOliS
 */
public class Utilisateur {

    private int id_user;
    private String nom;
    private String prenom;
    private String email;

    private Date dateNaissance;
    private int numTel;

    private String ville;
    private String mdp;
    private Role role;

    private Etat etat;

    private int nombrejouer;

    public Utilisateur() {
    }

    public Utilisateur(String email) {
        this.email = email;
    }

    public Utilisateur(int id_user) {
        this.id_user = id_user;
    }

    public Utilisateur(int id_user, String nom, String prenom, String email, Date dateNaissance, int numTel, String ville, String mdp, Role role, Etat etat) {

        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.numTel = numTel;
        this.ville = ville;
        this.mdp = mdp;
        this.role = role;
        this.etat = etat;
    }

    public Utilisateur(String nom, String prenom, String email, Date dateNaissance, int numTel, String ville, String mdp, Role role, Etat etat) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.numTel = numTel;
        this.ville = ville;
        this.mdp = mdp;
        this.role = role;
        this.etat = etat;
    }

    public Utilisateur(String nom, String prenom, String email, Date dateNaissance, int numTel, String ville, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.numTel = numTel;
        this.ville = ville;
        this.mdp = mdp;
    }

    public Utilisateur(int id_user, String nom, String prenom, String email, Date dateNaissance, int numTel, String ville, String mdp) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.numTel = numTel;
        this.ville = ville;
        this.mdp = mdp;
    }

    public Utilisateur(int id_user, String nom, String prenom, String email, Date dateNaissance, int numTel, String ville) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.numTel = numTel;
        this.ville = ville;
    }

    public Utilisateur(int id_user, String nom, String prenom, String email, Date dateNaissance, int numTel, String ville, String mdp, Role role, Etat etat, int nombrejouer) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.numTel = numTel;
        this.ville = ville;
        this.mdp = mdp;
        this.role = role;
        this.etat = etat;
        this.nombrejouer = nombrejouer;
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

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
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

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Role reformRole(String role) {
        String extractedWord = role.replaceAll("[^a-zA-Z]+", "");
        Role Roles = Role.valueOf(extractedWord);
        System.out.println(Roles);
        return Roles;

    }
    
    public Etat reformEtat(String etat) {
        String extractedWord = etat.replaceAll("[^a-zA-Z]+", "");
        Etat etats = Etat.valueOf(extractedWord);
        System.out.println(etats);
        return etats;

    }

    public Role getRole() {
        return role;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getNombrejouer() {
        return nombrejouer;
    }

    public void setNombrejouer(int nombrejouer) {
        this.nombrejouer = nombrejouer;
    }

    @Override
    public String toString() {

        return nom;

    }

}
