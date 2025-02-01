/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.services;

import elbaldi.interfaces.InterfaceAvisCrud;
import elbaldi.interfaces.InterfaceReservationCrud;
import elbaldi.models.Reservation;
import elbaldi.models.avis;
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
 * @author user
 */
public class AvisCrud implements InterfaceAvisCrud {

    Statement ste;
    Connection connection = MyConnection.getInstance().getConn();

    @Override

    public void ajouterAvis(avis A) {
        try {
            String req = "INSERT INTO `avis`( `note_avis`, `date_avis`, `id_user`, `id_bonplan`) VALUES (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);

            //ps.setInt(1, A.getId_avis());
            ps.setFloat(1, A.getNote_avis());
            ps.setDate(2, (Date) A.getDate_avis());
            ps.setInt(3, A.getUser1().getid_user());
            ps.setInt(4, A.getBonplan1().getId_bonplan());

            ps.executeUpdate();
            System.out.println("Avis ajouté");
        } catch (SQLException ex) {
            System.out.println("Avis non ajouté");
        }
    }

    @Override
    public void modifierAvis(avis A) {
        try {
            String req = "UPDATE `avis` SET  `note_avis` = '" + A.getNote_avis() + "', `date_avis` = '" + A.getDate_avis() + "', `id_user` = '" + A.getUser1().getId_user() + "', `id_bonplan` = '" + A.getBonplan1().getId_bonplan() + "' WHERE `avis`.`id_avis` = " + A.getId_avis();
            Statement st = connection.createStatement();
            System.out.println(req);
            st.executeUpdate(req);
            System.out.println("avis updated !");
        } catch (SQLException ex) {
            System.out.println("avis non updated !");
        }
    }

    @Override
    public List<avis> afficherAvis() {
        List<avis> list = new ArrayList<>();
        try {
            String req = "Select * from avis";
            Statement st = connection.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                avis A = new avis();
                A.setId_avis(RS.getInt("id_avis"));
                A.setNote_avis(RS.getFloat("note_avis"));
                A.setDate_avis(RS.getDate("date_avis"));

                list.add(A);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public void supprimerAvis(int id) {
        try {
            String req = "DELETE FROM `avis` WHERE id_avis = " + id;
            Statement st = connection.createStatement();
            st.executeUpdate(req);
            System.out.println("avis supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public avis getAvisById(int id) {
        avis A = null;
        try {
            String req = "SELECT * FROM avis WHERE id_avis = '" + id + "'";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            if (rs.next()) {
                A = new avis();
                A.setId_avis(rs.getInt("id_avis"));
                A.setNote_avis(rs.getFloat("note_avis"));
                A.setDate_avis(rs.getDate("date_avis"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return A;

    }

    @Override
    public List<avis> getAvisByIdUser(int id) {
        List<avis> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM avis WHERE id_user = '" + id + "'";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
             while (rs.next()) {
                BonplanCrud bp=new BonplanCrud();
                avis A = new avis();
                A.setId_avis(rs.getInt("id_avis"));
                A.setNote_avis(rs.getFloat("note_avis"));
                A.setDate_avis(rs.getDate("date_avis"));
                A.setBonplan1(bp.getByIdBonplan(rs.getInt("id_bonplan")));
                list.add(A);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;

    }

    @Override
    public List<avis> getAvisByIdBp(int id) {
        List<avis> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM avis WHERE id_bonplan = '" + id + "'";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                avis A = new avis();
                A.setId_avis(rs.getInt("id_avis"));
                A.setNote_avis(rs.getFloat("note_avis"));
                A.setDate_avis(rs.getDate("date_avis"));
                list.add(A);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;

    }

}
