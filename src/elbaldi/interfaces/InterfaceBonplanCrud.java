/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.interfaces;

import elbaldi.models.bonplan;
import elbaldi.models.produit;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author user
 */
public interface InterfaceBonplanCrud {
   public void ajouterBonplan(bonplan B) ;
   public void modifierBonplan(bonplan B);
   public List<bonplan> afficherBonplan();
   public void supprimerbonplan(int id);
    public bonplan getByIdBonplan(int id);
     public List<bonplan> filtreByType(String type_bonplan);
}
