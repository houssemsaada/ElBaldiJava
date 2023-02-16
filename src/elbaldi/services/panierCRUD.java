/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.services;

import elbaldi.interfaces.panierInterfaceCRUD;
import elbaldi.models.commande;
import elbaldi.models.panier;
import elbaldi.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author houss
 */
public class panierCRUD implements panierInterfaceCRUD {

    Statement ste;
    Connection conn = MyConnection.getInstance().getConn();

    @Override
    public void ajouterPanier(panier p) {
        if (p.getId_panier() != 0) {
            try {
                String req = "INSERT INTO `panier` (`id_panier`,`ref_produit`, `nombre_article`, `quantite_produit`, `total_panier`) VALUES (?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(req);
                ps.setInt(1, p.getId_panier());
                ps.setString(2, p.getRef_produit());
                ps.setInt(3, p.getNombre_article());
                ps.setInt(4, p.getQuantite_produit());
                ps.setFloat(5, p.getTotal_panier());
                ps.executeUpdate();
                System.out.println("panier ajouté!!!");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                String req = "INSERT INTO `panier` (`ref_produit`, `nombre_article`, `quantite_produit`, `total_panier`) VALUES (?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(req);
                ps.setString(1, p.getRef_produit());
                ps.setInt(2, p.getNombre_article());
                ps.setInt(3, p.getQuantite_produit());
                ps.setFloat(4, p.getTotal_panier());
                ps.executeUpdate();
                System.out.println("panier ajouté!!!");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void modifierPanier(panier p) {
        try {
            String req = "UPDATE `panier` SET `ref_produit` = ?, `nombre_article` = ? , `quantite_produit` = ?, `total_panier` = ? WHERE id_panier  = ? ";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, p.getRef_produit());
            ps.setInt(2, p.getNombre_article());
            ps.setInt(3, p.getQuantite_produit());
            ps.setFloat(4, p.getTotal_panier());
            ps.setInt(5, p.getId_panier());

            ps.executeUpdate();
            System.out.println("panier updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerPanier(int id_panier) {
        try {
            String req = "DELETE FROM `panier` WHERE id_panier = " + id_panier;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("panier deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
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

                panier p = new panier();
                p.setId_panier(RS.getInt(1));
                p.setRef_produit(RS.getString(2));
                p.setNombre_article(RS.getInt(3));
                p.setQuantite_produit(RS.getInt(4));
                p.setTotal_panier(RS.getFloat(5));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

}
