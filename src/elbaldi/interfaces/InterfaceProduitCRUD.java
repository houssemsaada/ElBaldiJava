/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.interfaces;
import elbaldi.models.produit;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Yasmine
 */
public interface InterfaceProduitCRUD {
    public void ajouterProduit(produit p) throws SQLException;
    public void modifierProduit(produit p) throws SQLException;
    public void supprimerProduit(String ref) throws SQLException ;
    public List<produit> afficherProduit() throws SQLException;
    public produit getByRefProduit(String ref) throws SQLException;
    public List<produit> filtreByPrixVente(float min, float max) throws SQLException;
    public List<produit> filtrerProduitParQuantite(int quantite) throws SQLException;
    public List<produit> filtreByCategorie(int idCategorie) throws SQLException ;
}
