/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.services;

import elbaldi.interfaces.commandeInterfaceCRUD;
import elbaldi.models.Role;
import elbaldi.models.Utilisateur;
import elbaldi.models.commande;
import elbaldi.models.panier;
import elbaldi.models.produit;
import elbaldi.utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
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
public class CommandeCRUD implements commandeInterfaceCRUD {

    Statement ste;
    Connection conn = MyConnection.getInstance().getConn();

    @Override
    public void ajouterCommande(commande c) {
        if (c.getId_cmd() != 0) {

            try {
                String req = "INSERT INTO `commande` (`id_cmd`,`id_panier`, `etat`, `date_cmd`, `total`,`adresse`) VALUES (?,?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(req);
                ps.setInt(1, c.getId_cmd());
                ps.setInt(2, c.getPan().getId_panier());
                ps.setString(3, c.getEtat());
                ps.setDate(4, c.getDate_cmd());
                ps.setFloat(5, c.getTotal());
                ps.setString(6, c.getAdresse());
                ps.executeUpdate();
                System.out.println("commande ajouté!!!");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            if (c.getEtat() == null && c.getDate_cmd() == null) {
                try {
                    String req = "INSERT INTO `commande` (`id_panier`, `total`,`adresse`) VALUES (?,?,?)";
                    PreparedStatement ps = conn.prepareStatement(req);
                    ps.setInt(1, c.getPan().getId_panier());
                    ps.setFloat(2, c.getTotal());
                    ps.setString(3, c.getAdresse());

                    ps.executeUpdate();
                    System.out.println("commande ajouté!!!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                try {
                    String req = "INSERT INTO `commande` (`id_panier`, `etat`, `date_cmd`, `total`,`adresse`) VALUES (?,?,?,?,?)";
                    PreparedStatement ps = conn.prepareStatement(req);
                    ps.setInt(1, c.getPan().getId_panier());
                    ps.setString(2, c.getEtat());
                    ps.setDate(3, c.getDate_cmd());
                    ps.setFloat(4, c.getTotal());
                    ps.setString(5, c.getAdresse());
                    ps.executeUpdate();
                    System.out.println("commande ajouté!!!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }
    }

    @Override
    public void modifierCommande(commande c, commande c2) {
        try {
            String req = "UPDATE `commande` SET `etat` = ?, `date_cmd` = ? , `id_panier` = ?, `id_cmd` = ? WHERE id_cmd  = ? ";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, c2.getEtat());
            ps.setDate(2, c2.getDate_cmd());
            ps.setInt(3, c2.getPan().getId_panier());
            ps.setInt(4, c2.getId_cmd());
            ps.setInt(5, c.getId_cmd());
            ps.executeUpdate();
            System.out.println("commande updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerCommande(commande c) {
        try {
            String req = "DELETE FROM `commande` WHERE id_cmd = " + c.getId_cmd();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("commande deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<commande> afficherCommande() {
        List<commande> list = new ArrayList<>();
        try {
            String req = "Select * from commande";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {

                commande c = new commande();
                c.setId_cmd(RS.getInt(1));
                c.setEtat(RS.getString(3));
                c.setDate_cmd(RS.getDate(4));

                panierCRUD pc = new panierCRUD();
                int pan_id = RS.getInt(2);
                panier pan = pc.filtreByidPanier(pan_id);
                c.setPan(pan);
                c.setTotal2(RS.getFloat(5));
                list.add(c);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public List<commande> filtreByDate(Date date_commande) {
        List<commande> commandes = new ArrayList<>();
        try {
            String fil = "SELECT * FROM commande WHERE date_cmd = ?";
            PreparedStatement ps = conn.prepareStatement(fil);
            ps.setDate(1, date_commande);
            ResultSet RS = ps.executeQuery();
            while (RS.next()) {
                commande c = new commande();

                c.setId_cmd(RS.getInt(1));
                c.setEtat(RS.getString(3));
                c.setDate_cmd(RS.getDate(4));

                panierCRUD pc = new panierCRUD();
                int pan_id = RS.getInt(2);
                panier pan = pc.filtreByidPanier(pan_id);
                c.setPan(pan);
                c.setTotal2(RS.getFloat(5));
                c.setAdresse(RS.getString(6));
                commandes.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return commandes;
    }

    @Override
    public commande filtreByid(int id_cmd) {
        commande c = new commande();
        try {
            String fil = "SELECT * FROM commande WHERE id_cmd = ?";
            PreparedStatement ps = conn.prepareStatement(fil);
            ps.setInt(1, id_cmd);
            ResultSet RS = ps.executeQuery();
            while (RS.next()) {

                c.setId_cmd(RS.getInt(1));
                c.setEtat(RS.getString(3));
                c.setDate_cmd(RS.getDate(4));

                panierCRUD pc = new panierCRUD();
                int pan_id = RS.getInt(2);
                panier pan = pc.filtreByidPanier(pan_id);
                c.setPan(pan);
                c.setTotal2(RS.getFloat(5));
                c.setAdresse(RS.getString(6));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return c;
    }

    @Override
    public List<commande> sortCommandesByDate() {
        List<commande> commandes = new ArrayList<>();
        try {
            String query = "SELECT * FROM commande ORDER BY date_cmd DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                commande c = new commande();

                c.setId_cmd(rs.getInt(1));
                c.setEtat(rs.getString(3));
                c.setDate_cmd(rs.getDate(4));
                panierCRUD pc = new panierCRUD();
                int pan_id = rs.getInt(2);
                panier pan = pc.filtreByidPanier(pan_id);
                c.setPan(pan);
                c.setTotal2(rs.getFloat(5));
                c.setAdresse(rs.getString(6));

                commandes.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return commandes;
    }

    @Override
    public List<commande> filtreByuser(panier p ) {
        List<commande> commandes = new ArrayList<>();
        try {
            String query = "SELECT * FROM commande WHERE id_panier  = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            
            ps.setInt(1, p.getId_panier());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                commande c = new commande();

                c.setId_cmd(rs.getInt(1));
                c.setEtat(rs.getString(3));
                c.setDate_cmd(rs.getDate(4));
                panierCRUD pc = new panierCRUD();
                int pan_id = rs.getInt(2);
                panier pan = pc.filtreByidPanier(pan_id);
                c.setPan(pan);
                c.setTotal2(rs.getFloat(5));
                c.setAdresse(rs.getString(6));

                commandes.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return commandes;
    }

    @Override
    public commande filtreBypanier(panier p1) {
        commande c = new commande();
        try {
            String query = "SELECT * FROM commande WHERE id_panier  = ? ORDER BY id_cmd ASC";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, p1.getId_panier());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                c.setId_cmd(rs.getInt(1));
                c.setEtat(rs.getString(3));
                c.setDate_cmd(rs.getDate(4));
                panierCRUD pc = new panierCRUD();
                int pan_id = rs.getInt(2);
                panier pan = pc.filtreByidPanier(pan_id);
                c.setPan(pan);
                c.setTotal2(rs.getFloat(5));
                c.setAdresse(rs.getString(6));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return c;
    }

    public float totalsales() {
        float total = 0;
        try {
            String req = "SELECT SUM(total) as total FROM commande";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                total = RS.getFloat("total");
                return total;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return total;

    }

    public int pendingorders() {
        int pend = 0;
        try {
            String req = "SELECT COUNT(*) as order_count FROM commande WHERE etat = 'En attente'";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                pend = RS.getInt("order_count");
                return pend;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return pend;
    }

    public void ajouterProdCommande(commande c, produit p) {
        try {
            String req = "INSERT INTO `command_produit` (`id_cmd`,`ref_produit`) VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, c.getId_cmd());
            ps.setString(2, p.getRef_produit());

            ps.executeUpdate();
            System.out.println("produit ajouté au command_produit!!!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<produit> top5prod() {
        List<produit> prod = new ArrayList<>();

        try {

            // Get the current month
            java.util.Date now = new java.util.Date();
            java.sql.Date currentMonth = new java.sql.Date(now.getTime());

            // Execute the SQL query
            String sql = "SELECT ref_produit, COUNT(*) as count FROM command_produit WHERE MONTH(date_cmd) = MONTH(?) AND YEAR(date_cmd) = YEAR(?) GROUP BY ref_produit ORDER BY count DESC LIMIT 5";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, currentMonth);
            stmt.setDate(2, currentMonth);
            ResultSet rs = stmt.executeQuery();

            // Print the results
            while (rs.next()) {
                produit p = new produit();
                p.setRef_produit(rs.getString("ref_produit"));
                p.setQuantite(rs.getInt("count"));
                prod.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prod;
    }

    public int orderCount(panier p ) {
        int count = 0;
        try {
            String req = "SELECT COUNT(*) as order_count FROM commande WHERE id_panier  ="+p.getId_panier();
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                count = RS.getInt("order_count");
                return count;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
    }
}
