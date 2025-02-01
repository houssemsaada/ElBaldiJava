/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.services;

import elbaldi.interfaces.InterfaceBonplanCrud;
import elbaldi.models.bonplan;
import elbaldi.models.produit;
import elbaldi.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author user
 */
public class BonplanCrud implements InterfaceBonplanCrud {

    Connection connection = MyConnection.getInstance().getConn();

    @Override
    public void ajouterBonplan(bonplan B) {
        try {
            String req = "INSERT INTO `bonplan`(`titre_bonplan`, `description_bonplan`, `type_bonplan` , `image_bonplan`) VALUES (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);

            ps.setString(1, B.getTitre_bonplan());
            ps.setString(2, B.getDescription_bonplan());
            ps.setString(3, B.getType_bonplan());
            ps.setString(4, B.getImage_bonplan());

            ps.executeUpdate();
            System.out.println("Bonplan ajouté avec succes ");
        } catch (SQLException ex) {
            System.out.println("Bonplan non ajoutee !!");
             System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierBonplan(bonplan B) {
        try {
            String req = "UPDATE `bonplan` SET `id_bonplan` = '" + B.getId_bonplan()
                    + "', `titre_bonplan` = '" + B.getTitre_bonplan()
                    + "', `description_bonplan` = '" + B.getDescription_bonplan()
                    + "', `type_bonplan` = '" + B.getType_bonplan()
                    + "', `image_bonplan` = '" + B.getImage_bonplan()
                    + "' WHERE `bonplan`.`id_bonplan` = " + B.getId_bonplan();
            Statement st = connection.createStatement();
            st.executeUpdate(req);
            System.out.println("Bonplan updated !");
        } catch (SQLException ex) {
            System.out.println("Bonplan non updated !");
        }
    }

    @Override
    public List<bonplan> afficherBonplan() {
        List<bonplan> list = new ArrayList<>();
        try {
            String req = "Select * from bonplan";
            Statement st = connection.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                bonplan B = new bonplan();
                B.setId_bonplan(RS.getInt("id_bonplan"));
                B.setTitre_bonplan(RS.getString("titre_bonplan"));
                B.setDescription_bonplan(RS.getString("description_bonplan"));
                B.setType_bonplan(RS.getString("type_bonplan"));
                B.setImage_bonplan(RS.getString("image_bonplan"));
                list.add(B);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public void supprimerbonplan(int id) {
        try {
            String req = "DELETE FROM `bonplan` WHERE id_bonplan = " + id;
            Statement st = connection.createStatement();
            st.executeUpdate(req);
            System.out.println("bonplan supprime");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public bonplan getByIdBonplan(int id) {
        bonplan Bpp = new bonplan();
        try {
            String req = "SELECT * FROM `bonplan` WHERE `id_bonplan` = '" + id + "'";
            Statement st = connection.createStatement();
            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {

                Bpp.setId_bonplan(RS.getInt("id_bonplan"));
                Bpp.setTitre_bonplan(RS.getString("titre_bonplan"));
                Bpp.setDescription_bonplan(RS.getString("description_bonplan"));
                Bpp.setType_bonplan(RS.getString("type_bonplan"));
                Bpp.setImage_bonplan(RS.getString("image_bonplan"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Bpp;
    }

    @Override
    public List<bonplan> filtreByType(String type_bonplan) {
        List<bonplan> bonPlans = new ArrayList<>();
        try {
            String fil = "SELECT * FROM bonplan WHERE type_bonplan = ?";
            PreparedStatement ps = connection.prepareStatement(fil);
            ps.setString(1, type_bonplan);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                bonplan B = new bonplan();
                B.setId_bonplan(result.getInt("id_bonplan"));
                B.setTitre_bonplan(result.getString("titre_bonplan"));
                B.setDescription_bonplan(result.getString("description_bonplan"));
                B.setType_bonplan(result.getString("type_bonplan"));
                B.setImage_bonplan(result.getString("image_bonplan"));

                bonPlans.add(B);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bonPlans;
    }
      public List<bonplan> rechercher(String titre) throws SQLException {
        List<bonplan> result = afficherBonplan().stream().
                filter(line -> line.getTitre_bonplan().toString().toLowerCase().contains(titre.toLowerCase()))
                .collect(Collectors.toList());
        //System.out.println("----------");
        //result.forEach(System.out::println);
        return result;
    }

//    public List<bonplan> rechercher(String titre) {
//        List<bonplan> bonPlans = new ArrayList<>();
//        try {
//            String fil = "SELECT * FROM bonplan WHERE titre_bonplan = ?";
//            PreparedStatement ps = connection.prepareStatement(fil);
//            ps.setString(1, titre);
//            ResultSet result = ps.executeQuery();
//            while (result.next()) {
//                bonplan B = new bonplan();
//                B.setId_bonplan(result.getInt("id_bonplan"));
//                B.setTitre_bonplan(result.getString("titre_bonplan"));
//                B.setDescription_bonplan(result.getString("description_bonplan"));
//                B.setType_bonplan(result.getString("type_bonplan"));
//                B.setImage_bonplan(result.getString("image_bonplan"));
//
//                bonPlans.add(B);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return bonPlans;
//    }

    /* @Override
    public void supprimerbonplan(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     */
      
        public  ResultSet ReservationBpcount(){
      try {
            String req = "SELECT bonplan.titre_bonplan, COUNT(reservation.id_reservation) as reservation_count FROM bonplan JOIN reservation ON bonplan.id_bonplan = reservation.id_bonplan GROUP BY bonplan.titre_bonplan LIMIT 3";
            Statement st = connection.createStatement();

            ResultSet RS = st.executeQuery(req);
            return RS ;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    
    
    return null;
    
}
}
