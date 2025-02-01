/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.interfaces;

import elbaldi.models.Utilisateur;
import elbaldi.models.promotion;
import java.util.List;

/**
 *
 * @author selim
 */
public interface InterfaceCRUDpromotion {
     public void ajouterpromotion(promotion p);
     public void modifierpromotion(promotion p);
     public void supprimerpromotion(int id );
     public List<promotion> afficherpromotion(); 
     public promotion getById(int id);
     public List<promotion> filtreBytaux(float taux);
     public boolean promocodeExistePourUtilisateur(promotion p, Utilisateur user) ;
     public boolean promocodeExiste(String code_promo);
}
