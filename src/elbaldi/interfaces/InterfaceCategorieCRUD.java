/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.interfaces;
import elbaldi.models.categorie;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Yasmine
 */
public interface InterfaceCategorieCRUD {
    public void ajouterCategorie(categorie c) throws SQLException;
    public void modifierCategorie(categorie c,int id) throws SQLException;
    public void supprimerCategorie(categorie c) throws SQLException ;
    public List<categorie> affichercategorie()throws SQLException;
    public categorie getCategorieById(int id) throws SQLException;
    public List<categorie> filtrerCategorie(String nomCategorie) throws SQLException;
    
}
