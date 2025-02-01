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
public class Reclamation {
     private int idReclamation;
    private String date;
    private String description;
    private int id_userClient;

    public Reclamation(int id, String date, String description, int id_userClient) {
        this.idReclamation = idReclamation;
        this.date = date;
        this.description = description;
        this.id_userClient = id_userClient;
    }

    public Reclamation() {
    }

    public Reclamation(String date, String description, int idClient) {
        this.date = date;
        this.description = description;
        this.id_userClient = id_userClient;
    }

    public int getidReclamation() {
        return idReclamation;
    }

    public void setidReclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getid_userClient() {
        return id_userClient;
    }

    public void setid_userClient(int id_userClient) {
        this.id_userClient = id_userClient;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "idReclamation=" + idReclamation + ", date=" + date + ", description=" + description + ", id_userClient=" + id_userClient + '}';
    }

   

  
    
    
}
