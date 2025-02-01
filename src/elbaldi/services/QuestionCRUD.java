/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.services;

import elbaldi.interfaces.InterfaceCRUDquestion;
import elbaldi.models.question;
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
public class QuestionCRUD implements InterfaceCRUDquestion{
     Connection conn = MyConnection.getInstance().getConn();
   
//      @Override
//     public void ajouterQuestion(question qq) {
//    try { 
//        String req = "INSERT INTO `question`(`difficulte`, `questionn`, `reponse1`, `reponse2`, `reponse3`, `solution`, `id_quiz`) VALUES (?,?,?,?,?,?,?)" ;
//        PreparedStatement ps = conn.prepareStatement(req); 
//        
//        ps.setString(1, qq.getDifficulte());
//        ps.setString(2, qq.getQuestionn());
//        ps.setString(3, qq.getReponse1());
//        ps.setString(4, qq.getReponse2());
//        ps.setString(5, qq.getReponse3());
//        ps.setString(6, qq.getSolution());
//        ps.setInt(7, qq.getquiz().getId_quiz());
//            
//        ps.executeUpdate();
//            
//       
//        System.out.println("Question ajoutée!!!");
//    } catch (SQLException ex) {
//        System.out.println("Question non ajoutée");                  
//    }   
//}
     
     public void ajouterQuestion(question qq, int selectedQuizId) {
    try {
        String req = "INSERT INTO `question`(`difficulte`, `questionn`, `reponse1`, `reponse2`, `reponse3`, `solution`, `id_quiz`) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(req);
        
        ps.setString(1, qq.getDifficulte());
        ps.setString(2, qq.getQuestionn());
        ps.setString(3, qq.getReponse1());
        ps.setString(4, qq.getReponse2());
        ps.setString(5, qq.getReponse3());
        ps.setString(6, qq.getSolution());
        ps.setInt(7, selectedQuizId);
            
        ps.executeUpdate();
            
        System.out.println("Question ajoutée!!!");
    } catch (SQLException ex) {
        System.out.println("Question non ajoutée");
        ex.printStackTrace();
    }   
}

     
     
     

     public void modifierquestion(question qqn, int id_question) {
    try {
        String req = "UPDATE `question` SET `difficulte` = ?, `questionn` = ?, `reponse1` = ?, `reponse2` = ?, `reponse3` = ?, `solution` = ?, `id_quiz` = ? WHERE `id_question` = ?";
        PreparedStatement ps = conn.prepareStatement(req);
        ps.setString(1, qqn.getDifficulte());
        ps.setString(2, qqn.getQuestionn());
        ps.setString(3, qqn.getReponse1());
        ps.setString(4, qqn.getReponse2());
        ps.setString(5, qqn.getReponse3());
        ps.setString(6, qqn.getSolution()); 
        ps.setInt(7, qqn.getquiz().getId_quiz());
        ps.setInt(8, id_question);
        ps.executeUpdate();
        System.out.println("Question modifiée !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}


     @Override
    public void supprimerquestion(question qq ) {
        try {
             String req = " DELETE FROM question where id_question  = ?   ";
            PreparedStatement ps  = conn.prepareStatement(req);
             ps.setInt(1, qq.getId_question());
            ps.executeUpdate();
            System.out.println("Question deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
    
     @Override
   public List<question> afficherQuestion() {
   List<question> listquestion = new ArrayList<>();
    try {
        String req = "Select * from question";
        Statement st = conn.createStatement();
       
        ResultSet RS= st.executeQuery(req);
        while(RS.next()){
         question qq = new question ();
         qq.setId_question(RS.getInt("id_question"));
         qq.setDifficulte(RS.getString(2));
         qq.setQuestionn(RS.getString(3));
         qq.setReponse1(RS.getString(4));
         qq.setReponse2(RS.getString(5));
         qq.setReponse3(RS.getString(6));
         qq.setSolution(RS.getString(7));
         QuizCRUD qc = new  QuizCRUD();
         int quiz_id = RS.getInt(8);
         quiz q = qc.getById(quiz_id);
         qq.setquiz(q);
             
         
         listquestion.add(qq);
        }
        
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

       return listquestion;
}
    
     @Override
   public question getById(int id) {
      question qq = new question();
    try {
        String req = "SELECT * FROM question WHERE id_question = ?";
        PreparedStatement ps = conn.prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            
            qq.setId_question(rs.getInt("id_question"));
            qq.setDifficulte(rs.getString("difficulte"));
            qq.setQuestionn(rs.getString("questionn"));
            qq.setReponse1(rs.getString("reponse1"));
            qq.setReponse2(rs.getString("reponse2"));
            qq.setReponse3(rs.getString("reponse3"));
            qq.setSolution(rs.getString("solution"));
            QuizCRUD qc = new  QuizCRUD();
             int quiz_id = rs.getInt(8);
            quiz q = qc.getById(quiz_id);
            qq.setquiz(q);
        }
       
        
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return qq ;
}

     @Override
   public List<question> filtreByDifficulte(String difficulte) {
    List<question> list = new ArrayList<>();
    try {
        String req = "Select * from question where difficulte = '" + difficulte + "'";
        Statement st = conn.createStatement();
       
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            question qq = new question();
            qq.setId_question(rs.getInt("id_question"));
            qq.setDifficulte(rs.getString("difficulte"));
            qq.setQuestionn(rs.getString("questionn"));
            qq.setReponse1(rs.getString("reponse1"));
            qq.setReponse2(rs.getString("reponse2"));
            qq.setReponse3(rs.getString("reponse3"));
            qq.setSolution(rs.getString("solution"));
            QuizCRUD qc = new  QuizCRUD();
         int quiz_id = rs.getInt(8);
         quiz q = qc.getById(quiz_id);
         qq.setquiz(q);
            list.add(qq);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return list;
   }
   
      
  @Override
   public List<question> filtreByidquiz(quiz quiz) {
    List<question> list = new ArrayList<>();
    try {
        String req = "SELECT * FROM question WHERE id_quiz = ?";
        PreparedStatement ps = conn.prepareStatement(req);
        ps.setInt(1, quiz.getId_quiz());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            question qq = new question();
            qq.setId_question(rs.getInt("id_question"));
            qq.setDifficulte(rs.getString("difficulte"));
            qq.setQuestionn(rs.getString("questionn"));
            qq.setReponse1(rs.getString("reponse1"));
            qq.setReponse2(rs.getString("reponse2"));
            qq.setReponse3(rs.getString("reponse3"));
            qq.setSolution(rs.getString("solution"));
            QuizCRUD qc = new  QuizCRUD();
         int quiz_id = rs.getInt(8);
         quiz q = qc.getById(quiz_id);
         qq.setquiz(q);
            list.add(qq);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return list;
   }
   
   
}
