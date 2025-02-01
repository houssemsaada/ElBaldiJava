/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.services;

import elbaldi.models.Evenement;
import elbaldi.models.Participation;
import elbaldi.interfaces.InterfaceParticipationCRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import elbaldi.utils.MyConnection;
import java.sql.Date;

/**
 *
 * @author Meddeb sofien
 */
public class ParticipationService implements  InterfaceParticipationCRUD<Participation,Evenement>{
    Connection cnx ;

    public ParticipationService() {
        cnx=MyConnection.getInstance().getConn();
    }
    
    @Override

    public void ajouter(Participation t,Evenement e) throws SQLException {
 try{ 
    // Date date = Date.valueOf(e.getDate_fin());
     String req = "insert into participation(id_client, id_event,date) VALUES ("
             + "'"+t.getId_user()+ "','"+t.getId_event()+"','"+e.getDate_debut()+ "')";
     
     Statement st = cnx.createStatement();
     st.executeUpdate(req);
     System.out.println("AJOUT de participation se fait avec succes");
 } catch(SQLException ex ){
     System.out.println("Erreur " + ex.getMessage());
 }
    }

    @Override
    public void modifier(Participation t) throws SQLException {
         try{ 
      String req = "UPDATE  participation SET id_client = ? , id_event=? ";
             PreparedStatement ps =cnx.prepareStatement(req);
             ps.setInt(1, t.getId_user());
             ps.setInt(2, t.getId_event());
             ps.executeUpdate();
             System.out.println("Modification de participation se fait");
 } catch(SQLException ex ){
     System.out.println("Erreur " + ex.getMessage());
 }
    }

    @Override
    public void supprimer(Participation t) throws SQLException {
          try {
              String req = "DELETE FROM participation where id_participation = ? " ;
              PreparedStatement ps = cnx.prepareStatement(req);
              ps.setInt(1, t.getId_participation());
              ps.executeUpdate();
              System.out.println("Suppression participation se fait");
          }catch(SQLException ex ){
              System.out.println("Erreur" + ex.getMessage());
          }
      }

    @Override
    public List<Participation> recuperer() throws SQLException {
      List<Participation> lP = new ArrayList<Participation>();
        try{
          String req = "SELECT  * FROM participation ";
          Statement st = cnx.createStatement();
          ResultSet rs = st.executeQuery(req);
          while(rs.next()){
              Participation P = new Participation();
              P.setId_participation(rs.getInt(1));
              P.setId_user(rs.getInt(3));
              P.setId_event(rs.getInt(4));
              
              lP.add(P);
          }
          
      }catch(SQLException ex){
          System.out.println("Erreur" + ex.getMessage());
          
      }
        return  lP;
    }
    
     public ObservableList<Integer> getidclient() {
       
        String Req = "select id_user from utilisateur ";
                  
      ObservableList<Integer> l = FXCollections.observableArrayList();
        try {
            
           Statement ste = cnx.createStatement();
           ResultSet res =  ste.executeQuery(Req); //recherche
            while (res.next()) {
                l.add(res.getInt(1));
                
                
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }
     
      public int getidclientt() {
       
        String Req = "select id_user from utilisateur ";
                  
      //ObservableList<Integer> l = FXCollections.observableArrayList();
      int i = 0;
        try {
            
           Statement ste = cnx.createStatement();
           ResultSet res =  ste.executeQuery(Req); //recherche
            while (res.next()) {
              //  l.add(res.getInt(1));
               i=res.getInt(1);
                
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return i;
    }
      public String getNomParticpant(int id) {
       
        String Req = "select name from utilisateur where id_user = " + id +"";
                  
      //ObservableList<Integer> l = FXCollections.observableArrayList();
      String i = null;
        try {
             
             
           Statement ste = cnx.createStatement();
           ResultSet res =  ste.executeQuery(Req);//recherche
            while (res.next()) {
              //  l.add(res.getInt(1));
               i=res.getString(1);
                
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return i;
    }
      
      public int getidev() {
       
        String Req = "select id_event from evenement ";
                  
      //ObservableList<Integer> l = FXCollections.observableArrayList();
      int i = 0;
        try {
             
             
           Statement ste = cnx.createStatement();
           ResultSet res =  ste.executeQuery(Req);//recherche
            while (res.next()) {
              //  l.add(res.getInt(1));
               i=res.getInt(1);
                
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return i;
    }
       public int getidus() {
       
        String Req = "select id_user from utilisateur ";
                  
      //ObservableList<Integer> l = FXCollections.observableArrayList();
      int i = 0;
        try {
             
             
           Statement ste = cnx.createStatement();
           ResultSet res =  ste.executeQuery(Req);//recherche
            while (res.next()) {
              //  l.add(res.getInt(1));
               i=res.getInt(1);
                
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return i;
    }
       public List verif_existance(int idus ) {
       
        String Req = "select id_event from participation where id_client = " + idus + " ";
                  
      //ObservableList<Integer> l = FXCollections.observableArrayList();
     // int i = 0;
     List list = new ArrayList();
        try {
             
             
           Statement ste = cnx.createStatement();
           ResultSet res =  ste.executeQuery(Req);//recherche
            while (res.next()) {
              //  l.add(res.getInt(1));
               list.add(res.getInt("id_event"));
               
                
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
     
    
}
