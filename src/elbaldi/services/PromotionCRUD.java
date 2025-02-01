/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.services;

import elbaldi.interfaces.InterfaceCRUDpromotion;
import elbaldi.models.Utilisateur;
import elbaldi.models.promotion;
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
 * @author selim
 */
public class PromotionCRUD implements InterfaceCRUDpromotion {
    Connection conn = MyConnection.getInstance().getConn();
   
     @Override
     public void ajouterpromotion(promotion p) {
        
    try { 
        String req = "INSERT INTO `promotion`(`code_promo`, `taux`,`id_user`) VALUES (?,?,?)" ;
        PreparedStatement ps = conn.prepareStatement(req); 
        
        ps.setString(1, p.getCode_promo());
        ps.setFloat(2, p.getTaux());
        ps.setInt(3, p.getU().getid_user());
        
            
        ps.executeUpdate();
            
        System.out.println("Promotion ajoutée!!!");
    } catch (SQLException ex) {
        System.out.println("Promotion non ajoutée");  
    }
   }
          
  
     @Override
public void modifierpromotion(promotion p) {
    
    try {
      
       String req = "UPDATE `promotion` SET `code_promo` = ?, `taux` = ?, `date_debut` = ?, `date_fin` = ?  WHERE `id_promotion` = ?";
 
       PreparedStatement ps = conn.prepareStatement(req);
        ps.setString(1, p.getCode_promo());
        ps.setFloat(2, p.getTaux());
        ps.setInt(5, p.getId_promotion());
        ps.executeUpdate();
        System.out.println("promotion modifiée !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
 }



  @Override
    public void supprimerpromotion(int id) {
        try {
            String req = "DELETE FROM `promotion` WHERE `id_promotion` = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("promotion deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
  @Override
   public List<promotion> afficherpromotion() {
   List<promotion> listpromotion = new ArrayList<>();
    try {
        String req = "Select * from promotion";
        Statement st = conn.createStatement();
       
        ResultSet RS= st.executeQuery(req);
        while(RS.next()){
         promotion p = new promotion();
         p.setId_promotion(RS.getInt("id_promotion"));
         p.setCode_promo(RS.getString(2));
         p.setTaux(RS.getFloat("taux"));
      
         
         
         listpromotion.add(p);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

       return listpromotion;
}
    
      @Override
   public promotion getById(int id) {
      promotion p = new promotion();
    try {
        String req = "SELECT * FROM promotion WHERE id_promotion = ?";
        PreparedStatement ps = conn.prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            
            p.setId_promotion(rs.getInt("id_promotion"));
            p.setCode_promo(rs.getString("code_promo"));
            p.setTaux(rs.getFloat("taux"));
           
            
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return p ;
}
   
   
    @Override
   public List<promotion> filtreBytaux(float taux) {
    List<promotion> list = new ArrayList<>();
    try {
        String req = "Select * from promotion where taux = '" + taux + "'";
        Statement st = conn.createStatement();
       
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            promotion p = new promotion();
            p.setId_promotion(rs.getInt("id_promotion"));
           
            p.setCode_promo(rs.getString("code_promo"));
            p.setTaux(rs.getFloat("taux"));
     
            
            list.add(p);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return list;
}
    @Override
   public boolean promocodeExiste(String code_promo) {
    try {
       
        PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM promotion WHERE code_promo=?");
        ps.setString(1, code_promo);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int count = rs.getInt(1);
            return count > 0;
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return false;
}

      @Override
  public boolean promocodeExistePourUtilisateur(promotion p, Utilisateur user) {
    try {
        PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM promotion WHERE code_promo=? AND id_user=?");
        ps.setString(1, p.getCode_promo());
        ps.setInt(2, user.getId_user());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int count = rs.getInt(1);
            return count > 0;
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return false;
}
  public promotion getBypromo(String promo) {
      promotion p = new promotion();
    try {
        String req = "SELECT * FROM promotion WHERE code_promo = ?";
        PreparedStatement ps = conn.prepareStatement(req);
        ps.setString(1, promo);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            
            p.setId_promotion(rs.getInt("id_promotion"));
            p.setCode_promo(rs.getString("code_promo"));
            p.setTaux(rs.getFloat("taux"));
           
            
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return p ;
}
}
