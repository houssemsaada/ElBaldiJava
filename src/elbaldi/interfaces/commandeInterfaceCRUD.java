/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.interfaces;
import elbaldi.models.Utilisateur;
import java.util.List;
import elbaldi.models.commande;
import elbaldi.models.panier;
import java.sql.Date;

/**
 *
 * @author houss
 */
public interface commandeInterfaceCRUD {
    public void ajouterCommande(commande c) ; 
    public void modifierCommande(commande c,commande c2);
    public void supprimerCommande(commande c) ;
    public List<commande> afficherCommande();
    public List<commande> filtreByDate(Date date_commande);
    public List<commande> sortCommandesByDate();
    public List<commande> filtreByuser(panier p);
    public commande filtreByid(int id_cmd);
        public commande filtreBypanier(panier p1) ;


}
