/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi;

import elbaldi.models.Utilisateur;
import elbaldi.models.promotion;
import elbaldi.models.quiz;
import elbaldi.services.PromotionCRUD;
import elbaldi.services.QuizCRUD;
import java.sql.SQLException;

/**
 *
 * @author selim
 */

public class selim {
    
 public static void main(String[] args) throws SQLException {
        
    
    //----------------------------Module QUIZ-----------------------------


        

    quiz q1 = new quiz("facile","aaaa","hhhh");

    QuizCRUD qu= new QuizCRUD();
        //Ajout d'un Quiz 
                  //qu.ajouterQuiz(q1);
        //Modification d'un Quiz          
                //  qu.modifierquiz(q1,22);
        //Suppression d'un Quiz 
                 //qu.supprimerquiz(4);
        //Affichage d'un Quiz 
                //System.out.println( qu.afficherQuiz());
        //Get By Id 
               //System.out.println(qu.getById(2));
        //Filtre By difficulte
               //System.out.println(qu.filtreByDifficulte("Moyenne"));
      /*  
        
//-----------------------------Module Question------------------------------------------------               
               
       question qq1= new question("Facile"," Quelle est la plus gande ville de la Tunisie?","Tunis","Sousse","Sfax","Tunis",8);
       QuestionCRUD qq= new QuestionCRUD();
   
       //Ajout d'une Question      
                  //qq.ajouterQuestion(qq1);
       //Modification d'une Question    
                  //qq.modifierquestion(qq1);   
       //Suppression d'une Question  
                 //qq.supprimerquestion(3);
       //Affichage d'une Question 
                 //System.out.println( qq.afficherQuestion());
       //Get By Id 
                //System.out.println(qq.getById(2));
       //Filtre By difficulte
                // System.out.println(qq.filtreByDifficulte("difficile"));
    


    
}
*/    
      Utilisateur po = new Utilisateur(2492);
      promotion p = new promotion("ELBALDI5893",0.15f,po);
      PromotionCRUD pc = new PromotionCRUD();
      System.out.println(pc.promocodeExistePourUtilisateur(p, po));
 }}