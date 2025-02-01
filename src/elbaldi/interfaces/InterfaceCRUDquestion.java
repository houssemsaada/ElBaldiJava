/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.interfaces;

import elbaldi.models.question;
import elbaldi.models.quiz;
import java.util.List;

/**
 *
 * @author selim
 */
public interface InterfaceCRUDquestion {
    
     public void ajouterQuestion(question qq, int selectedQuizId);
     //public void ajouterQuestion(question qq) ;
     public void modifierquestion(question qq,int id_question);
     public void supprimerquestion(question qq);
     public List<question> afficherQuestion() ;
     public question getById(int id);
     public List<question> filtreByDifficulte(String difficulte);
   public List<question> filtreByidquiz(quiz quiz) ;
}