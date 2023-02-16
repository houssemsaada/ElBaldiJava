/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.services;

import elbaldi.interfaces.livraisonInterfaceCRUD;
import elbaldi.models.commande;
import elbaldi.models.livraison;
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
public class livraisonCRUD implements livraisonInterfaceCRUD {

    Statement ste;
    Connection conn = MyConnection.getInstance().getConn();

    @Override
    public void ajouterLivraison(livraison l, commande c) {
        if (l.getId_livraison() != 0) {
            try {
                String req = "INSERT INTO `livraison` (`id_livraison`,`id_cmd`, `status_livraison`, `adresse_livraison`, `date_livraison`) VALUES (?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(req);
                ps.setInt(1, l.getId_livraison());
                ps.setInt(2, c.getId_cmd());
                ps.setString(3, l.getStatus_livraison());
                ps.setString(4, l.getAdresse_livraison());
                ps.setDate(5, l.getDate_livraison());
                ps.executeUpdate();
                System.out.println("livraison ajouté!!!");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                String req = "INSERT INTO `livraison` (`id_cmd`, `status_livraison`, `adresse_livraison`, `date_livraison`) VALUES (?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(req);
                ps.setInt(1, c.getId_cmd());
                ps.setString(2, l.getStatus_livraison());
                ps.setString(3, l.getAdresse_livraison());
                ps.setDate(4, l.getDate_livraison());
                ps.executeUpdate();
                System.out.println("livraison ajouté!!!");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    @Override
    public void modifierLivraison(livraison l, commande c) {
        try {
            String req = "UPDATE `livraison` SET `id_cmd` = ?,`status_livraison` = ?, `adresse_livraison` = ? , `date_livraison` = ? WHERE id_livraison   = ? ";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, c.getId_cmd());
            ps.setString(2, l.getStatus_livraison());
            ps.setString(3, l.getAdresse_livraison());
            ps.setDate(4, l.getDate_livraison());
            ps.setInt(5, l.getId_livraison());
            ps.executeUpdate();
            System.out.println("livraison updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerLivraison(int id_livraison) {
        try {
            String req = "DELETE FROM `livraison` WHERE id_livraison = " + id_livraison;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("livraison deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<livraison> afficherLivraison() {
        List<livraison> list = new ArrayList<>();
        try {
            String req = "Select * from livraison";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {

                livraison l = new livraison();
                l.setId_livraison(RS.getInt(1));
                l.setId_cmd(RS.getInt(2));
                l.setStatus_livraison(RS.getString(3));
                l.setAdresse_livraison(RS.getString(4));
                l.setDate_livraison(RS.getDate(5));
                list.add(l);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public List<livraison> filtreByDate(Date date_livraison) {
        List<livraison> livraisons = new ArrayList<>();
        try {
            String fil = "SELECT * FROM livraison WHERE date_livraison = ?";
            PreparedStatement ps = conn.prepareStatement(fil);
            ps.setDate(1, date_livraison);
            ResultSet RS = ps.executeQuery();
            while (RS.next()) {
                livraison l = new livraison();
                l.setId_livraison(RS.getInt(1));
                l.setId_cmd(RS.getInt(2));
                l.setStatus_livraison(RS.getString(3));
                l.setAdresse_livraison(RS.getString(4));
                l.setDate_livraison(RS.getDate(5));
                livraisons.add(l);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return livraisons;
    }

    @Override
    public List<livraison> sortlivraisonByDate() {
        List<livraison> livraisons = new ArrayList<>();
        try {
            String query = "SELECT * FROM livraison ORDER BY date_livraison DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet RS = ps.executeQuery();
            while (RS.next()) {
                livraison l = new livraison();
                l.setId_livraison(RS.getInt(1));
                l.setId_cmd(RS.getInt(2));
                l.setStatus_livraison(RS.getString(3));
                l.setAdresse_livraison(RS.getString(4));
                l.setDate_livraison(RS.getDate(5));
                livraisons.add(l);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return livraisons;
    }

    @Override
    public List<livraison> filtreBycommande(commande c) {
        List<livraison> livraisons = new ArrayList<>();
        try {
            String query = "SELECT * FROM livraison WHERE id_cmd = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, c.getId_cmd());
            ResultSet RS = ps.executeQuery();
            while (RS.next()) {
                livraison l = new livraison();
                l.setId_livraison(RS.getInt(1));
                l.setId_cmd(RS.getInt(2));
                l.setStatus_livraison(RS.getString(3));
                l.setAdresse_livraison(RS.getString(4));
                l.setDate_livraison(RS.getDate(5));
                livraisons.add(l);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return livraisons;
    }

}
