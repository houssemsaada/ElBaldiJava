/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.interfaces;

import elbaldi.models.Reclamation;
import java.sql.SQLException;
import java.util.List;



/**
 *
 * @author mEtrOpOliS
 * @param <T>
 */
public interface InterfaceCRUD2 <T>{
     public List<Reclamation> Filter_Reclamation(String S, String SS);
    public Reclamation getReclamationByID(int idReclamation ) throws SQLException;
    public void ajouterReclamation(Reclamation r);
    void supprimerReclamation(int idReclamation);
     void modifierReclamation(Reclamation r,int idReclamation);
    List<Reclamation> afficherReclamation();
    
}
