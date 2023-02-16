/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.interfaces;
import elbaldi.models.commande;
import java.util.List;
import elbaldi.models.livraison;
import java.sql.Date;
/**
 *
 * @author houss
 */
public interface livraisonInterfaceCRUD {
    public void ajouterLivraison(livraison l,commande c) ; 
    public void modifierLivraison(livraison l,commande c);
    public void supprimerLivraison(int id_livraison) ;
    public List<livraison> afficherLivraison();
    public List<livraison> filtreByDate(Date date_livraison);
    public List<livraison> sortlivraisonByDate();
    public List<livraison> filtreBycommande(commande c);
}
