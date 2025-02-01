/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.interfaces;

import elbaldi.models.avis;
import java.util.List;

/**
 *
 * @author user
 */
public interface InterfaceAvisCrud {
    
  public void ajouterAvis(avis A);
  public void modifierAvis(avis A);
   public List<avis> afficherAvis();
   public void supprimerAvis(int id);
   public avis getAvisById(int id);
   public List<avis>  getAvisByIdUser(int id);
   public List<avis> getAvisByIdBp(int id);
}
