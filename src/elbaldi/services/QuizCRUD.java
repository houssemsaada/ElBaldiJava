/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.services;

import elbaldi.interfaces.InterfaceCRUDQ;
import elbaldi.models.Evenement;
import elbaldi.models.Utilisateur;
import elbaldi.models.promotion;
import elbaldi.models.quiz;
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
public class QuizCRUD implements InterfaceCRUDQ{
    
    Connection conn = MyConnection.getInstance().getConn();
    
    @Override
    public void ajouterQuiz(quiz q) {
        try { 
            String req = "INSERT INTO `quiz`(`nom`,`difficulte`,`score`) VALUES (?,?,?)" ;
            PreparedStatement ps=conn.prepareStatement(req); 
           
            ps.setString(1,q.getNom());
            ps.setString(2,q.getDifficulte());
            ps.setInt(3,q.getScore());
          
            ps.executeUpdate();
            
            System.out.println("Quiz ajouté!!!");
        } catch (SQLException ex) {
            System.out.println("Quiz non ajouté");                  
        }   
    }

   
    @Override
    public void modifierquiz(quiz q,int id_quiz) {
  
    
    try {
            String req = "UPDATE `quiz` SET `id_quiz` = '" + q.getId_quiz()+ "', `difficulte` = '" + q.getDifficulte()+  "', `score` = '" + q.getScore()+ "', `nom` = '" + q.getNom()+ "', `image` = '" + q.getImgview()+      "' WHERE `quiz`.`id_quiz` = " + id_quiz;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Quiz updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


   
      @Override
    public void supprimerquiz(quiz q) {
        try {
  
         String req = " DELETE FROM quiz where nom  = ?   ";
       
             PreparedStatement ps = conn.prepareStatement(req);
             ps.setString(1, q.getNom());
             ps.executeUpdate();
              System.out.println("Quiz deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
   @Override
   public List<quiz> afficherQuiz() {
   List<quiz> list = new ArrayList<>();
    try {
        String req = "Select * from quiz";
        Statement st = conn.createStatement();
       
        ResultSet RS= st.executeQuery(req);
        while(RS.next()){
         quiz q = new quiz ();
        q.setId_quiz(RS.getInt("id_quiz"));
        
         q.setDifficulte(RS.getString(2));
        q.setScore(RS.getInt(3));
         
         
//         
//       UtilisateurCRUD uc = new UtilisateurCRUD();
////         int user = RS.getInt(4);
//         Utilisateur uu = uc.getUserByID(user);
//         q.setuser(uu);
////         
          q.setNom(RS.getString(4));
          q.setImgview(RS.getString(5));
         
         list.add(q);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return list;
}

public quiz getById(int id) {
    quiz q = new quiz();
    try {
        String req = "Select * from quiz where id_quiz = " + id;
        Statement st = conn.createStatement();
       
        ResultSet RS = st.executeQuery(req);
        if (RS.next()) {
            
            
            q.setId_quiz(RS.getInt("id_quiz"));
           
            q.setDifficulte(RS.getString(2));
            q.setScore(RS.getInt(3));
           
         
         UtilisateurCRUD uc = new UtilisateurCRUD();
//         int user = RS.getInt(4);
//         Utilisateur uu = uc.getUserByID(user);
          q.setNom(RS.getString(4));
         q.setImgview(RS.getString(5));
         //q.setuser(uu);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return q;
}


    @Override
    public List<quiz> filtreByDifficulte(String difficulte) {
    List<quiz> list = new ArrayList<>();
    try {
        String req = "Select * from quiz where difficulte = '" + difficulte + "'";
        Statement st = conn.createStatement();
       
        ResultSet RS = st.executeQuery(req);
        while (RS.next()) {
            quiz q = new quiz();
            q.setId_quiz(RS.getInt("id_quiz"));
         
            q.setDifficulte(RS.getString(2));
            q.setScore(RS.getInt(3));
         
         
         UtilisateurCRUD uc = new UtilisateurCRUD();
         int user = RS.getInt(4);
         Utilisateur uu = uc.getUserByID(user);
         q.setNom(RS.getString(5));
         q.setImgview(RS.getString(6));
         q.setuser(uu);
         
      
            list.add(q);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return list;
}
   
  public boolean quizExiste(String nom) {
   
    String sql = "SELECT * FROM quiz WHERE nom = ?";
    try {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, nom);
        ResultSet rs = pstmt.executeQuery();
        return rs.next();
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        return false;
    }
}
  
    
    Utilisateur uu = new Utilisateur();
    
public int getNombreJouer(int id_user) {
    int nombreJouer = -1;
    String sql = "SELECT nombrejouer FROM Utilisateur WHERE id_user = ?";
    try {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id_user);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            nombreJouer = rs.getInt("nombrejouer");
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return nombreJouer;
}

public void setNombreJouer(int id_user, int nombrejouer) {
    String sql = "UPDATE Utilisateur SET nombrejouer = ? WHERE id_user = ?";
    try {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, nombrejouer);
        pstmt.setInt(2, id_user);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}


    
    
}
