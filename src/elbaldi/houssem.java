/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi;

import static elbaldi.models.Role.client;
import elbaldi.models.Utilisateur;
import elbaldi.models.categorie;
import elbaldi.models.commande;
import elbaldi.models.livraison;
import elbaldi.models.panier;
import elbaldi.models.produit;
import elbaldi.services.CategorieCRUD;
import elbaldi.services.CommandeCRUD;
import elbaldi.services.PdfOrder;
import elbaldi.services.ProduitCRUD;
import elbaldi.services.UtilisateurCRUD;
import elbaldi.services.livraisonCRUD;
import elbaldi.services.panierCRUD;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author houss
 */
public class houssem {

    public static void main(String[] args) throws SQLException {
        String date_comm = "2023-02-10";
        Date date_com = Date.valueOf(date_comm);
        String date_liv = "2023-02-18";
        Date date_livr = Date.valueOf(date_liv);
           Utilisateur u1 = new Utilisateur();
//        UtilisateurCRUD uti = new UtilisateurCRUD();
//        
//        // uti.ajouterUtlisateur(u1);
//        //   categorie c1 = new categorie(2, "art");
//        CategorieCRUD categ = new CategorieCRUD();
//        // categ.ajouterCategorie(c1);
//        produit p1 = new produit("f03");
//        ProduitCRUD prod = new ProduitCRUD();
//        System.out.println("aa");
//        System.out.println( prod.min5prod());
//        // prod.ajouterProduit(p1);
////---------------------- module commande--------------------------------------------------------------------        
////             commande com1 = new commande(128, pan1, "aaaaaa", date_com);
//        //     commande com2 = new commande(129, pan1, "aaaaaa", date_com);
//
//        CommandeCRUD comm = new CommandeCRUD();
//        //  System.out.println(comm.totalsales());
//        //  System.out.println(comm.pendingorders());
//        System.out.println(prod.prodCount());
//        // System.out.println());
//        ResultSet rs = prod.categorieprodcount();
//        while (rs.next()) {
//            String category = rs.getString("nom_categorie");
//            int count = rs.getInt("product_count");
//            System.out.println(category + ": " + count);
//        }
//        
//        
//        System.out.println(comm.top5prod());
        //comm.ajouterCommande(com2);
        //comm.modifierCommande(com1);
        //comm.supprimerCommande(1);
        //System.out.println( comm.afficherCommande());
        //System.out.println(comm.filtreByDate(date_com));
        //System.out.println(comm.sortCommandesByDate());
        // System.out.println(comm.filtreByuser(u2));
//----------------------module livraison--------------------------------------------------------------------        
        //   livraison l1 = new livraison("le", "nabeul", date_livr, com1);
        //  livraison l2 = new livraison("le", "nabeul", date_livr, com2);
//        livraisonCRUD liv = new livraisonCRUD();
       // System.out.println(liv.pendingliv());
        //liv.ajouterLivraison(l2);
        //liv.modifierLivraison(l1);
        //liv.supprimerLivraison(6);
        //System.out.println(liv.afficherLivraison());
        //System.out.println(liv.filtreByDate(date_livr));
        //System.out.println(liv.sortlivraisonByDate());
        //System.out.println(liv.filtreBycommande(com2));
//----------------------module panier------------------------------------------------------------------------
//        panier pan1 = new panier(21);
//        panierCRUD pan = new panierCRUD();
//        pan1=pan.filtreByidPanier(21);
//        List list = pan.afficherListProduitPanier(pan1);
//        pan1.setList(list);
//        Date date = Date.valueOf(LocalDate.now());
//        Utilisateur u2 = new Utilisateur(2498, "houssem", "saada", "houssem.saada@gmail.com",date, 90000000, "aaaaaaa");
//        
//      //  System.out.println(pan.afficherListProduitPanier(pan1));
//        // pan1.setU1(u1);
//        //pan.ajouterPanier(pan1);
//        //pan.ajouterProdPanier(pan1, p1,10);
//        //pan.supprimerProdPanier(pan1,p1) ; 
//        //  pan.modifierPanier(pan1);
//        //pan.supprimerPanier(3);
//        //System.out.println(pan.afficherPanier());
//        //System.out.println(pan.filtreByuser(u1));
//        //System.out.println(pan.afficherListProduitPanier(pan1));
//        // pan.modifierQteProdPanier(pan1, p1, 8);
//        commande com1 = new commande(124, pan1, "aaaaaa", date_com);
//        com1.setAdresse("menzel temime");
//        
//        PdfOrder pdf = new PdfOrder();
//         pdf.orderPdf(com1);
   u1.reformRole("[\"client\"]");
    }
}
