/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi;

import elbaldi.models.commande;
import elbaldi.models.livraison;
import elbaldi.models.panier;
import elbaldi.services.CommandeCRUD;
import elbaldi.services.livraisonCRUD;
import elbaldi.services.panierCRUD;
import java.sql.Date;

/**
 *
 * @author houss
 */
public class houssem {
    String date_comm = "2023-02-10";
        Date date_com = Date.valueOf(date_comm);
        String date_liv = "2023-02-18";
        Date date_livr = Date.valueOf(date_liv);
//---------------------- module commande--------------------------------------------------------------------        
        commande com1 = new commande(1,2, "delic", date_com);
        CommandeCRUD comm = new CommandeCRUD();
         //comm.ajouterCommande(com1);
        //comm.modifierCommande(com1);
        //comm.supprimerCommande(1);
        // System.out.println( comm.affichercommande());
        //System.out.println(comm.filtreByDate(date_com));
        //System.out.println(comm.sortCommandesByDate());
        //System.out.println(comm.filtreByuser(2));
//----------------------module livraison--------------------------------------------------------------------        
        livraison l1 = new livraison(18, "cbon", "tozeuur", date_livr, com1);
        livraisonCRUD liv = new livraisonCRUD();
                //liv.ajouterLivraison(l1, com1);
        //liv.modifierLivraison(l1, com1);
        //liv.supprimerLivraison(6);
        //System.out.println(liv.afficherLivraison());
        //System.out.println(liv.filtreByDate(date_livr));
        //System.out.println(liv.sortlivraisonByDate());
        //System.out.println(liv.filtreBycommande(c1));
//----------------------module panier------------------------------------------------------------------------
        panier pan1 = new panier("aaa123",5,5,22);
        panierCRUD pan = new panierCRUD();
        //pan.ajouterPanier(pan1);
        //pan.modifierPanier(pan1);
        //pan.supprimerPanier(3);
        //System.out.println(pan.afficherPanier());
}
