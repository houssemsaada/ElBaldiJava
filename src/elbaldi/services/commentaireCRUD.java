/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.services;

import elbaldi.interfaces.InterfaceCommentaireCRUD;
import elbaldi.models.Utilisateur;
import elbaldi.models.commentaire;
import elbaldi.models.produit;
import elbaldi.utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yasmine
 */
public class commentaireCRUD implements InterfaceCommentaireCRUD{
      Connection conn = MyConnection.getInstance().getConn();

    @Override
    public void ajouterCommentaire(commentaire c)  throws SQLException {
        // Créer un objet Timestamp pour la date et l'heure actuelles
            Timestamp date = new Timestamp(System.currentTimeMillis());
            try {
            String req = "INSERT INTO `commentaire`(`ref_produit`, `id_user`, `contenu`, `date_comm`) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, c.getProduit().getRef_produit());
            ps.setInt(2, c.getUtilisateur().getid_user());
            ps.setString(3, c.getContenu());
            ps.setTimestamp(4, date);
            ps.executeUpdate();
            System.out.println("Commentaire ajouté avec succès.");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'ajout du commentaire : " + ex.getMessage());
        }      
    
    }

    @Override
    public void modifierCommentaire(commentaire c)  throws SQLException {
        
        // Créer un objet Timestamp pour la date et l'heure actuelles
            Timestamp date = new Timestamp(System.currentTimeMillis());
         try {            
            String req = "UPDATE `commentaire` SET `contenu` = '" + c.getContenu() + "', `date_comm` = '" + date
            + "' WHERE `commentaire`.`id_commentaire` = " + c.getId_commentaire();
              
             Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Commentaire modifié avec succès.");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la modification du commentaire : " + ex.getMessage());
        }
     
    }
    
     

    @Override
    public void supprimerCommentaire(commentaire c)   throws SQLException {
        try {
            String req = "DELETE FROM `commentaire` WHERE `id_commentaire` = ?";
            PreparedStatement ps = conn.prepareStatement(req);

            ps.setInt(1, c.getId_commentaire());

            ps.executeUpdate();
            System.out.println("Commentaire supprimé avec succès.");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression du commentaire : " + ex.getMessage());
        }
    }

    @Override
    public List<commentaire> afficherCommentaire()  throws SQLException {
     List<commentaire> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `commentaire`";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                commentaire c = new commentaire();
                c.setId_commentaire(RS.getInt(1));
                c.setContenu(RS.getString(2));
                c.setDate(RS.getDate(3));
            UtilisateurCRUD utilisateurCRUD = new UtilisateurCRUD(); 
             int utilisateurId = RS.getInt(4);
             Utilisateur userrr = utilisateurCRUD.getUserByID(utilisateurId);
             c.setUtilisateur(userrr);
             ProduitCRUD produitCRUD = new ProduitCRUD(); 
            String refproduit = RS.getString(5);
            produit prod = produitCRUD.getByRefProduit(refproduit);
            c.setProduit(prod);
                
                list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'affichage des commentaires : " + ex.getMessage());
        }

        return list;
    }
    @Override
    public commentaire getCommentaireById(int id_commentaire)  throws SQLException {
    commentaire c = null;
    try {
        String req = "SELECT * FROM `commentaire` WHERE `id_commentaire` = ?";
        PreparedStatement ps = conn.prepareStatement(req);
        ps.setInt(1, id_commentaire);

        ResultSet RS = ps.executeQuery();
        if (RS.next()) {
                c = new commentaire();
                c.setId_commentaire(RS.getInt(1));
                c.setContenu(RS.getString(2));
                c.setDate(RS.getDate(3));
               
                 UtilisateurCRUD utilisateurCRUD = new UtilisateurCRUD(); 
             int utilisateurId = RS.getInt(4);
             Utilisateur userrr = utilisateurCRUD.getUserByID(utilisateurId);
             c.setUtilisateur(userrr);
                
                ProduitCRUD produitCRUD = new ProduitCRUD(); 
            String refproduit = RS.getString(5);
             produit prod = produitCRUD.getByRefProduit(refproduit);
             c.setProduit(prod);
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération du commentaire : " + ex.getMessage());
    }

    return c;
}

    
    @Override
    public List<commentaire> getCommentairesByArticle(produit p) throws SQLException {
    List<commentaire> list = new ArrayList<>();
    try {
        String req = "SELECT * FROM `commentaire` WHERE `ref_produit` = ?";
        PreparedStatement ps = conn.prepareStatement(req);
        ps.setString(1, p.getRef_produit());

        ResultSet RS = ps.executeQuery();
        while(RS.next()) {
            commentaire c = new commentaire();         
             c.setId_commentaire(RS.getInt(1));
             c.setContenu(RS.getString(2));
             c.setDate(RS.getDate(3));
            int utilisateurId = RS.getInt(4);
             UtilisateurCRUD utilisateurCRUD = new UtilisateurCRUD(); 
             Utilisateur userrr = utilisateurCRUD.getUserByID(utilisateurId);
             c.setUtilisateur(userrr);
                
                ProduitCRUD produitCRUD = new ProduitCRUD(); 
            String refproduit = RS.getString(5);
             produit prod = produitCRUD.getByRefProduit(refproduit);
             c.setProduit(prod);
            list.add(c);
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération des commentaires pour l'article " + p.getRef_produit() + ": " + ex.getMessage());
    }

    return list;
}
    @Override
    public List<commentaire> filterParDate(Date date)  throws SQLException{
    List<commentaire> list = new ArrayList<>();
    try {
        String req = "SELECT * FROM `commentaire` WHERE `date_comm` > ?";
        PreparedStatement ps = conn.prepareStatement(req);
        ps.setDate(1, new java.sql.Date(date.getTime()));

        ResultSet RS = ps.executeQuery();
        while(RS.next()) {
            commentaire c = new commentaire();
            c.setId_commentaire(RS.getInt(1));
             c.setContenu(RS.getString(2));
             c.setDate(RS.getDate(3));
             
             int utilisateurId = RS.getInt(4);
             UtilisateurCRUD utilisateurCRUD = new UtilisateurCRUD(); 
             Utilisateur userrr = utilisateurCRUD.getUserByID(utilisateurId);
             c.setUtilisateur(userrr);
                
                ProduitCRUD produitCRUD = new ProduitCRUD(); 
            String refproduit = RS.getString(5);
             produit prod = produitCRUD.getByRefProduit(refproduit);
             c.setProduit(prod);

            list.add(c);
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération des commentaires : " + ex.getMessage());
    }

    return list;
}

    
}
