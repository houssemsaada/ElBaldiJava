/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.interfaces;
import java.util.List;
import elbaldi.models.panier;
/**
 *
 * @author houss
 */
public interface panierInterfaceCRUD {
    public void ajouterPanier(panier p) ; 
    public void modifierPanier(panier p);
    public void supprimerPanier(int id_panier) ;
    public List<panier> afficherPanier();
}   
