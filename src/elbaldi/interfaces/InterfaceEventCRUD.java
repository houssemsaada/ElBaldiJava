/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.interfaces;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author MSI
 */
public interface InterfaceEventCRUD <T>{
public void ajouter(T t) throws SQLException;
     public void modifier(T t) throws SQLException;
     public void supprimer(T t) throws SQLException;
     public List<T> recuperer(T t) throws SQLException;    
}
