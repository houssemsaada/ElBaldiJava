/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.services;

import elbaldi.interfaces.InterfaceCRUD2;
import elbaldi.models.Reclamation;
import elbaldi.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mEtrOpOliS
 */
public class ReclamationCRUD implements InterfaceCRUD2{
    Statement ste;
    Connection conn = MyConnection.getInstance().getConn();

    @Override
    public void ajouterReclamation(Reclamation r){
        try {
            String req ="INSERT INTO `reclamation`(`Date`, `description`, `id_userClient`) VALUES ('"+r.getDate()+"','"+r.getDescription()+"','"+r.getid_userClient()+"')";
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Reclamation ajouté!!!");
        } catch (SQLException ex) {
            System.out.println("Reclamation non ajouté");
                      }
 }

   
    


    @Override
    public void modifierReclamation(Reclamation r ,int idReclamation) {{
        try {
            String req = "UPDATE `reclamation` SET `Date` = '" + r.getDate() + "',`description` = '" + r.getDescription()+  "' , `id_userClient` = '" + r.getid_userClient() +"' WHERE `idReclamation` = " + idReclamation;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Reclamation updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
  
    }

   

    
    @Override
    public List<Reclamation> afficherReclamation() {
       List<Reclamation> list = new ArrayList<>();
        try {
            String req = "Select * from reclamation";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Reclamation r = new Reclamation();
            
         
                   r.setidReclamation(RS.getInt(1));
              r.setDate(RS.getString(2));
             r.setDescription(RS.getString(3));
     r.setid_userClient(RS.getInt(4));
                  
             list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public void supprimerReclamation(int idReclamation) {
        try {
            String req = "DELETE FROM `reclamation` WHERE idReclamation = " + idReclamation;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Reclamation deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public Reclamation getReclamationByID(int idReclamation ) throws SQLException {
       String querry="SELECT *  FROM reclamation WHERE `idReclamation`="+idReclamation;
       Statement stm=conn.createStatement();
       ResultSet RS=stm.executeQuery(querry);
       
       Reclamation r=new Reclamation ();
        while (RS.next()) {            
            
           
                  r.setidReclamation(RS.getInt(1));
                  r.setDate(RS.getString(2));
                  r.setDescription(RS.getString(3));
                  r.setid_userClient(RS.getInt(4));
            
        }
    return r;
    }

    
    
    
    @Override
    public List<Reclamation> Filter_Reclamation(String S, String SS) {
        List<Reclamation> list = new ArrayList<>();
        try {
            if (S.equals("idReclamation") ) {
                int temp = Integer.parseInt(SS);
                String req = "SELECT * FROM reclamation WHERE " + S + " =" + temp;
                Statement st = conn.createStatement();
                ResultSet RS = st.executeQuery(req);
                while (RS.next()) {
                    Reclamation r= new Reclamation();
                    
                   r.setidReclamation(RS.getInt(1));
              r.setDate(RS.getString(2));
             r.setDescription(RS.getString(3));
                  r.setid_userClient(RS.getInt(4));  
             
                

                    list.add(r);
                }
            } else {
                String req = "SELECT * FROM reclamation WHERE " + S + " =" + "\"" + SS + "\"";
                Statement st = conn.createStatement();
                ResultSet RS = st.executeQuery(req);
                while (RS.next()) {
                   Reclamation r  = new Reclamation();
                 r.setidReclamation(RS.getInt(1));
                 r.setDate(RS.getString(2));
                 r.setDescription(RS.getString(3));
                 r.setid_userClient(RS.getInt(4));
                    list.add(r);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
/*
    public boolean afficherReclamation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean getReclamationByID(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean Filter_Reclamation(String nom, String test) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  */  

    

  
} 