/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.services;
import elbaldi.interfaces.InterfaceReservationCrud;
import elbaldi.models.Reservation;
import elbaldi.models.bonplan;

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
public class ReservationCrud implements InterfaceReservationCrud {
    Statement ste;
    Connection connection= MyConnection.getInstance().getConn();
    @Override
    public void ajouterReservation(Reservation R) {
        try {
            String req = "INSERT INTO `reservation`( `nombre_personnes`, `date_reservation`, `statut_reservation`, `id_bonplan`, `id_user`) VALUES (?,?,?,?,?)";
           PreparedStatement ps=connection.prepareStatement(req);
          
            //ps.setInt(1, R.getId_reservation());
            ps.setInt(1, R.getNombre_personnes());
            ps.setDate(2, (Date) R.getDate_reservation());
            ps.setString(3,R.getStatut_reservation());
            ps.setInt(4, R.getBonplan2().getId_bonplan());
            ps.setInt(5, R.getUser2().getid_user());
          
             ps.executeUpdate();
            System.out.println("reservation ajouté");
        } catch (SQLException ex) {
           System.out.println("reservation non ajouté");   
           System.out.println(ex.getMessage());
        }   
    }

    @Override
    public void modifierReservation(Reservation R) {
        try {
            String req = "UPDATE `Reservation` SET `nombre_personnes` = '" + R.getNombre_personnes() + "', `date_reservation` = '" + R.getDate_reservation() +  "', `statut_reservation` = '" + R.getStatut_reservation() + "', `id_bonplan` = '" + R.getBonplan2().getId_bonplan() + "', `id_user` = '" + R.getUser2().getid_user() + "' WHERE `Reservation`.`id_reservation` = " + R.getId_reservation();
            Statement st = connection.createStatement();
            System.out.println(req);
            st.executeUpdate(req);
            System.out.println("Reservation updated !");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    
     @Override
    public List<Reservation> afficherReservation() {
       List<Reservation> list = new ArrayList<>();
       BonplanCrud bp=new BonplanCrud();
       UtilisateurCRUD user=new UtilisateurCRUD();
        try {
            String req = "Select * from Reservation";
            Statement st = connection.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Reservation R = new Reservation();
             R.setId_reservation(RS.getInt("id_reservation"));
             R.setNombre_personnes(RS.getInt("nombre_personnes"));
             R.setDate_reservation(RS.getDate("date_reservation"));
             R.setStatut_reservation(RS.getString("statut_reservation"));
             R.setBonplan2(bp.getByIdBonplan(RS.getInt("id_bonplan")));
             R.setUser2(user.getUserByID(RS.getInt("id_user")));
             
            
             list.add(R);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
    
    @Override
    public void supprimerReservation(int id) {
        try {
            String req = "DELETE FROM `Reservation` WHERE id_reservation = " + id;
            Statement st = connection.createStatement();
            st.executeUpdate(req);
            System.out.println("reservation supprimée");
        } catch (SQLException ex) {
            System.out.println("reservation non supprimée");
        }
    }
    
    
    
    
    @Override
    public Reservation getReservationById(int id) {
    Reservation RSV = null;
    try {
        String req = "SELECT * FROM Reservation WHERE id_reservation = '" + id +"'";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);
        if (rs.next()) {
             RSV= new Reservation();
             RSV.setId_reservation(rs.getInt("id_reservation"));
             RSV.setNombre_personnes(rs.getInt("nombre_personnes"));
             RSV.setDate_reservation(rs.getDate("date_reservation"));
             RSV.setStatut_reservation(rs.getString("statut_reservation"));
            
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return RSV;
    
    } 
    

    
    @Override
    public List<Reservation> filtreByDate(Date date_reservation) {
    List<Reservation> reservations = new ArrayList<>();
    try {
        String fil = "SELECT * FROM reservation WHERE date_reservation = ?";
        PreparedStatement ps = connection.prepareStatement(fil);
        ps.setDate(1, date_reservation);
        ResultSet result = ps.executeQuery();
        while (result.next()) {
            Reservation R = new Reservation();
            R.setId_reservation(result.getInt("id_reservation"));
            R.setNombre_personnes(result.getInt("nombre_personnes"));
            R.setDate_reservation(result.getDate("date_reservation"));
            R.setStatut_reservation(result.getString("statut_reservation"));
           
            reservations.add(R);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return reservations;
}
    
     public List<Reservation> triepardatedecr() {
       List<Reservation> list = new ArrayList<>();
       BonplanCrud bp=new BonplanCrud();
       UtilisateurCRUD user=new UtilisateurCRUD();
        try {
            String req = "Select * from Reservation order by `date_reservation` desc";
            Statement st = connection.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Reservation R = new Reservation();
             R.setId_reservation(RS.getInt("id_reservation"));
             R.setNombre_personnes(RS.getInt("nombre_personnes"));
             R.setDate_reservation(RS.getDate("date_reservation"));
             R.setStatut_reservation(RS.getString("statut_reservation"));
             R.setBonplan2(bp.getByIdBonplan(RS.getInt("id_bonplan")));
             R.setUser2(user.getUserByID(RS.getInt("id_user")));
             
            
             list.add(R);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
  public List<Reservation> triepardatecrois() {
       List<Reservation> list = new ArrayList<>();
       BonplanCrud bp=new BonplanCrud();
       UtilisateurCRUD user=new UtilisateurCRUD();
        try {
            String req = "Select * from Reservation order by `date_reservation` asc";
            Statement st = connection.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Reservation R = new Reservation();
             R.setId_reservation(RS.getInt("id_reservation"));
             R.setNombre_personnes(RS.getInt("nombre_personnes"));
             R.setDate_reservation(RS.getDate("date_reservation"));
             R.setStatut_reservation(RS.getString("statut_reservation"));
             R.setBonplan2(bp.getByIdBonplan(RS.getInt("id_bonplan")));
             R.setUser2(user.getUserByID(RS.getInt("id_user")));
             
            
             list.add(R);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
   public List<Reservation> afficherReservation(Date date) {
       List<Reservation> list = new ArrayList<>();
       BonplanCrud bp=new BonplanCrud();
       UtilisateurCRUD user=new UtilisateurCRUD();
        try {
            String req = "Select * from Reservation WHERE date_reservation = ?";
            System.out.println(req);
          PreparedStatement ps = connection.prepareStatement(req);
        ps.setDate(1, date);
        System.out.println(ps);
        ResultSet RS = ps.executeQuery();
            while(RS.next()){
             Reservation R = new Reservation();
             R.setId_reservation(RS.getInt("id_reservation"));
             R.setNombre_personnes(RS.getInt("nombre_personnes"));
             R.setDate_reservation(RS.getDate("date_reservation"));
             R.setStatut_reservation(RS.getString("statut_reservation"));
             R.setBonplan2(bp.getByIdBonplan(RS.getInt("id_bonplan")));
             R.setUser2(user.getUserByID(RS.getInt("id_user")));
             
            
             list.add(R);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
}