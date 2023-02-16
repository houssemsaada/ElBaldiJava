/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.interfaces;
import java.util.List;
import elbaldi.models.commande;
import java.sql.Date;

/**
 *
 * @author houss
 */
public interface commandeInterfaceCRUD {
    public void ajouterCommande(commande c) ; 
    public void modifierCommande(commande c);
    public void supprimerCommande(int id_cmd) ;
    public List<commande> afficherCommande();
    public List<commande> filtreByDate(Date date_commande);
    public List<commande> sortCommandesByDate();
    public List<commande> filtreByuser(int id_user);

}
