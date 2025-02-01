/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.interfaces;

import elbaldi.models.commentaire;
import elbaldi.models.produit;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Yasmine
 */
public interface InterfaceCommentaireCRUD {
    public void ajouterCommentaire(commentaire c) throws SQLException;
    public void modifierCommentaire(commentaire c) throws SQLException;
    public void supprimerCommentaire(commentaire c) throws SQLException ;
    public List<commentaire> afficherCommentaire() throws SQLException;
    public commentaire getCommentaireById(int id_commentaire) throws SQLException;
    public List<commentaire> getCommentairesByArticle(produit p) throws SQLException;
    public List<commentaire> filterParDate(Date date) throws SQLException;
}
