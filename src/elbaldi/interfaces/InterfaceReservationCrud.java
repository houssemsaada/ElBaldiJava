/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.interfaces;

import elbaldi.models.Reservation;
import elbaldi.models.bonplan;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author user
 */
public interface InterfaceReservationCrud {
    public void ajouterReservation(Reservation R);
    public void modifierReservation(Reservation R);
    public void supprimerReservation(int id);
    public List<Reservation> afficherReservation();
    public Reservation getReservationById(int id);
    public List<Reservation> filtreByDate(Date date_reservation);
}




