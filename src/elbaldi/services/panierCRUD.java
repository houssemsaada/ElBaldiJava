/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.services;

import elbaldi.interfaces.panierInterfaceCRUD;
import elbaldi.models.Role;
import elbaldi.models.Utilisateur;
import elbaldi.models.categorie;
import elbaldi.models.commande;
import elbaldi.models.panier;
import elbaldi.models.produit;
import elbaldi.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author houss
 */
public class panierCRUD implements panierInterfaceCRUD {

    Statement ste;
    Connection conn = MyConnection.getInstance().getConn();

    @Override
    public void ajouterPanier(panier p) {

        try {
            String req = "INSERT INTO `panier` (`id_user`) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, p.getU1().getid_user());

            ps.executeUpdate();
            System.out.println("panier ajouté!!!");
        } catch (SQLException ex) {
            System.out.println("panier non ajouté!!!");
        }

    }

    @Override
    public void supprimerProdPanier(panier p, produit prod) {
        try {
            String req = "DELETE FROM `panier_produit` WHERE `id_panier` = ? AND `ref_produit` = ?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, p.getId_panier());
            ps.setString(2, prod.getRef_produit());
            ps.executeUpdate();
            System.out.println("produit supprimé de panier !");

        } catch (SQLException ex) {
            System.out.println("produit non supprimé de panier !");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierQteProdPanier(panier p, produit prod, int quantite) {
        try {
            String req = "UPDATE `panier_produit` SET `Quantite` = ? WHERE id_panier  = ? AND ref_produit = ?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, quantite);
            ps.setInt(2, p.getId_panier());
            ps.setString(3, prod.getRef_produit());
            ps.executeUpdate();
            System.out.println("quantite produit modifie !");
        } catch (SQLException ex) {
            Logger.getLogger(panierCRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("quantite produit non modifie !");

        }

    }
    public boolean verifyExistance(panier p, produit p1){
         try {
            PreparedStatement stmt = conn.prepareStatement("SELECT *  FROM panier_produit WHERE id_panier = ? AND ref_produit = ?");
            stmt.setInt(1, p.getId_panier());
            stmt.setString(2, p1.getRef_produit());
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                System.out.println("produit non existant");
                return false ; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return true;
        
    }
    @Override
    public void ajouterProdPanier(panier p, produit p1, int quantite) {
        try {

            String req = "INSERT INTO `panier_produit`(`id_panier`, `ref_produit`,`Quantite`) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, p.getId_panier());
            ps.setString(2, p1.getRef_produit());
            ps.setInt(3, quantite);
            ps.executeUpdate();
            System.out.println("produit ajouté au panier!!!");

        } catch (SQLException ex) {
            System.out.println("produit non ajouté au panier!!!");
            System.out.println(ex.getMessage());
        }
    }
//m=update nbr elements et total

    @Override
    public void modifierPanier(panier p) {
        try {
            String req = "UPDATE `panier` SET  `nombre_article` = ? , `total_panier` = ? WHERE id_panier  = ? ";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(3, p.getId_panier());
            // list des produits pour calculer le nombre d'article et le somme 
            List<produit> list_p = new ArrayList<>();
            String produitfilter = "SELECT *  FROM panier_produit WHERE `id_panier`= ?";
            PreparedStatement psp = conn.prepareStatement(produitfilter);
            psp.setInt(1, p.getId_panier());
            ResultSet rp = psp.executeQuery();
            while (rp.next()) {
                int quantite = rp.getInt(3);
                String filter = "SELECT *  FROM produit WHERE `ref_produit`= ?";
                PreparedStatement pss = conn.prepareStatement(filter);
                pss.setString(1, rp.getString(2));
                ResultSet rr = pss.executeQuery();
                while (rr.next()) {
                    produit p1 = new produit();
                    p1.setRef_produit(rr.getString(1));
                    p1.setPrix_vente(rr.getFloat(5));
                    p1.setQuantite(quantite);
                    list_p.add(p1);
                }
                p.setList(list_p);
            }
            int numarticle = p.numArticle(list_p);
            float somme = p.sommePanier(list_p);

            ps.setInt(1, numarticle);
            ps.setFloat(2, somme);

            ps.executeUpdate();
            System.out.println("panier updated !");
        } catch (SQLException ex) {
            System.out.println("panier non updated !");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerPanier(panier p) {
        try {
            String req = "DELETE FROM `panier` WHERE id_panier = " + p.getId_panier();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("panier deleted !");
        } catch (SQLException ex) {
            System.out.println("panier non deleted !");
        }
    }

    @Override
    public List<panier> afficherPanier() {
        List<panier> list = new ArrayList<>();

        try {
            String req = "Select * from panier";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                List<produit> list_p = new ArrayList<>();
                panier p = new panier();

                p.setId_panier(RS.getInt(1));
                p.setList(afficherListProduitPanier(p));

                //utilisateur
                UtilisateurCRUD uti = new UtilisateurCRUD();
                int user_id = RS.getInt(2);
                Utilisateur u = uti.getUserByID(user_id);
                p.setU1(u);
                int numarticle = p.numArticle(p.getList());
                p.setNombre_article(numarticle);
                float somme = p.sommePanier(p.getList());
                p.setTotal_panier(somme);
                list.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public panier filtreByuser(Utilisateur u1) {
        List<panier> list = new ArrayList<>();
        panier p = new panier();
        try {
            String query = "SELECT * FROM panier WHERE id_user = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, u1.getid_user());
            ResultSet RS = ps.executeQuery();
            while (RS.next()) {
                List<produit> list_p = new ArrayList<>();

                p.setId_panier(RS.getInt(1));
                p.setList(afficherListProduitPanier(p));
                //utilisateur
                UtilisateurCRUD uti = new UtilisateurCRUD();
                int user_id = RS.getInt(2);
                Utilisateur u = uti.getUserByID(user_id);
                p.setU1(u);
                int numarticle = p.numArticle(p.getList());
                p.setNombre_article(numarticle);
                float somme = p.sommePanier(p.getList());
                p.setTotal_panier(somme);
                list.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }

    @Override
    public panier filtreByidPanier(int pan) {
        panier p = new panier();
        try {
            String query = "SELECT * FROM panier WHERE id_panier = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, pan);
            ResultSet RS = ps.executeQuery();
            while (RS.next()) {
                List<produit> list_p = new ArrayList<>();

                p.setId_panier(RS.getInt(1));
                p.setList(afficherListProduitPanier(p));
                //utilisateur
                UtilisateurCRUD uti = new UtilisateurCRUD();
                int user_id = RS.getInt(2);
                Utilisateur u = uti.getUserByID(user_id);
                p.setU1(u);
                int numarticle = p.numArticle(p.getList());
                p.setNombre_article(numarticle);
                float somme = p.sommePanier(p.getList());
                p.setTotal_panier(somme);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }

    @Override
    public List<produit> afficherListProduitPanier(panier p) {
        List<produit> list_p = new ArrayList<>();
        try {
            // list des produits

            String produitfilter = "SELECT *  FROM panier_produit WHERE `id_panier`= ?";
            PreparedStatement psp = conn.prepareStatement(produitfilter);
            psp.setInt(1, p.getId_panier());
            ResultSet rp = psp.executeQuery();

            while (rp.next()) {
                int quantite = rp.getInt(3);
                String filter = "SELECT *  FROM produit WHERE `ref_produit`= ?";
                PreparedStatement pss = conn.prepareStatement(filter);
                pss.setString(1, rp.getString(2));
                ResultSet rr = pss.executeQuery();
                while (rr.next()) {
                    produit p1 = new produit();
                    p1.setRef_produit(rr.getString(1));
                    p1.setLibelle(rr.getString(2));
                    p1.setDescription(rr.getString(3));
                    p1.setImage(rr.getString(4));
                    p1.setPrix_vente(rr.getFloat(5));
                    p1.setQuantite(quantite);
                    categorie cc = new categorie();
                    cc.setId_categorie(rr.getInt(7));
                    p1.setCategoriee(cc);
                    list_p.add(p1);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(panierCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list_p;
    }
@Override
    public List<produit> afficherListProduityasmine(panier p) {
        List<produit> list_p = new ArrayList<>();
        try {
            // list des produits

            String produitfilter = "SELECT *  FROM panier_produit WHERE `id_panier`= ?";
            PreparedStatement psp = conn.prepareStatement(produitfilter);
            psp.setInt(1, p.getId_panier());
            ResultSet rp = psp.executeQuery();

            while (rp.next()) {
                String filter = "SELECT *  FROM produit WHERE `ref_produit`= ?";
                PreparedStatement pss = conn.prepareStatement(filter);
                pss.setString(1, rp.getString(2));
                ResultSet rr = pss.executeQuery();
                while (rr.next()) {
                    produit p1 = new produit();
                    p1.setRef_produit(rr.getString(1));
                    p1.setLibelle(rr.getString(2));
                    p1.setDescription(rr.getString(3));
                    p1.setImage(rr.getString(4));
                    p1.setPrix_vente(rr.getFloat(5));
                    p1.setQuantite(rr.getInt(6));
                    categorie cc = new categorie();
                    cc.setId_categorie(rr.getInt(7));
                    p1.setCategoriee(cc);
                    list_p.add(p1);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(panierCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list_p;
    }

}
