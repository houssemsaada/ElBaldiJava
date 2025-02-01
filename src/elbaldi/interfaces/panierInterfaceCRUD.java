/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.interfaces;

import elbaldi.models.Utilisateur;
import java.util.List;
import elbaldi.models.panier;
import elbaldi.models.produit;

/**
 *
 * @author houss
 */
public interface panierInterfaceCRUD {

    public void ajouterPanier(panier p);

    public void ajouterProdPanier(panier p, produit p1, int quantite);

    public void modifierPanier(panier p);

    public void modifierQteProdPanier(panier p, produit prod, int quantite);

    public void supprimerPanier(panier p);

    public List<panier> afficherPanier();

    public panier filtreByuser(Utilisateur u1);

    public panier filtreByidPanier(int pan);

    public void supprimerProdPanier(panier p, produit prod);

    public List<produit> afficherListProduitPanier(panier p);

    public List<produit> afficherListProduityasmine(panier p);

}
