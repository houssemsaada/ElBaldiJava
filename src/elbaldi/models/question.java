/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.models;

/**
 *
 * @author selim
 */
public class question {
    private int id_question;
    private String difficulte;
    private String questionn;
    private String reponse1;
    private String reponse2;
    private String reponse3;
    private String solution;
    private quiz id_quiz;
    
    public question() {
    }

    public question(String difficulte, String questionn, String reponse1, String reponse2, String reponse3, String solution) {
        this.difficulte = difficulte;
        this.questionn = questionn;
        this.reponse1 = reponse1;
        this.reponse2 = reponse2;
        this.reponse3 = reponse3;
        this.solution = solution;
    }
    
    
    public question(String difficulte, String questionn, String reponse1, String reponse2, String reponse3, String solution, quiz id_quiz) {
        this.difficulte = difficulte;
        this.questionn = questionn;
        this.reponse1 = reponse1;
        this.reponse2 = reponse2;
        this.reponse3 = reponse3;
        this.solution = solution;
        this.id_quiz = id_quiz;
    }

    
   

    public question(int id_question, String difficulte, String questionn, String reponse1, String reponse2, String reponse3, String solution, quiz id_quiz) {
        this.id_question = id_question;
        this.difficulte = difficulte;
        this.questionn = questionn;
        this.reponse1 = reponse1;
        this.reponse2 = reponse2;
        this.reponse3 = reponse3;
        this.solution = solution;
        this.id_quiz = id_quiz;
    }
    
    
  
   


    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public String getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(String difficulte) {
        this.difficulte = difficulte;
    }

    
    
    public String getQuestionn() {
        return questionn;
    }

    public void setQuestionn(String questionn) {
        this.questionn = questionn;
    }

    public String getReponse1() {
        return reponse1;
    }

    public void setReponse1(String reponse1) {
        this.reponse1 = reponse1;
    }

    public String getReponse2() {
        return reponse2;
    }

    public void setReponse2(String reponse2) {
        this.reponse2 = reponse2;
    }

    public String getReponse3() {
        return reponse3;
    }

    public void setReponse3(String reponse3) {
        this.reponse3 = reponse3;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public quiz getquiz() {
        return id_quiz;
    }

    public void setquiz(quiz id_quiz) {
        this.id_quiz = id_quiz;
    }

    @Override
    public String toString() {
        return "question{" /*+ "id_question=" + id_question */+ ", difficulte=" + difficulte + ", questionn=" + questionn + ", reponse1=" + reponse1 + ", reponse2=" + reponse2 + ", reponse3=" + reponse3 + ", solution=" + solution + ", id_quiz=" + id_quiz + '}';
    }
    

    
   
}
   

