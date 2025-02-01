/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.services;

import elbaldi.interfaces.InterfaceProduitCRUD;
import elbaldi.models.categorie;
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
 * @author Yasmine
 */
public class ProduitCRUD implements InterfaceProduitCRUD {

    Connection conn = MyConnection.getInstance().getConn();

    @Override
    public void ajouterProduit(produit p) throws SQLException {
        try {
            String req = "INSERT INTO `produit`(`ref_produit`, `libelle`, `description`, `image`, `prix_vente`, `quantite`, `id_categorie`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(req);

            ps.setString(1, p.getRef_produit());
            ps.setString(2, p.getLibelle());
            ps.setString(3, p.getDescription());
            ps.setString(4, p.getImage());
            ps.setFloat(5, p.getPrix_vente());
            ps.setInt(6, p.getQuantite());
            ps.setInt(7, p.getCategoriee().getId_categorie());
            ps.executeUpdate();
            System.out.println("Produit ajoutee avec succes ");
        } catch (SQLException ex) {
            System.out.println("Produit non ajoutee !!");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierProduit(produit p) throws SQLException {
        try {
            String req = "UPDATE `produit` SET `libelle` = '" + p.getLibelle()
                    + "', `description` = '" + p.getDescription() + "', `image` = '" + p.getImage()
                    + "', `prix_vente` = '" + p.getPrix_vente() + "', `quantite` = '" + p.getQuantite()
                    + "', `id_categorie` = '" + p.getCategoriee().getId_categorie()
                    + "' WHERE `produit`.`ref_produit` = '" + p.getRef_produit() + "'";
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("produit updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerProduit(String ref) throws SQLException {
        try {
            String req = "DELETE FROM `produit` WHERE `ref_produit` = '" + ref + "'";
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Produit deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<produit> afficherProduit() throws SQLException {
        List<produit> list = new ArrayList<>();
        try {
            String req = "Select * from produit";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                produit p = new produit();
                p.setRef_produit(RS.getString(1));
                p.setLibelle(RS.getString(2));
                p.setDescription(RS.getString(3));
                p.setImage(RS.getString(4));
                p.setPrix_vente(RS.getFloat(5));
                p.setQuantite(RS.getInt(6));
                CategorieCRUD categorieCRUD = new CategorieCRUD(); // instancier un objet de la classe CategorieCRUD
                int categorieId = RS.getInt(7);
                categorie categoriee = categorieCRUD.getCategorieById(categorieId);
                p.setCategoriee(categoriee);
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public produit getByRefProduit(String ref) throws SQLException {
        //produit p = new produit();
        produit p = null;
        //String reff="TUN61900"+ref;
        try {
            String req = "SELECT * FROM `produit` WHERE `ref_produit` = '" + ref + "'";
            Statement st = conn.createStatement();
            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                p = new produit();
                p.setRef_produit(RS.getString(1));
                p.setLibelle(RS.getString(2));
                p.setDescription(RS.getString(3));
                p.setImage(RS.getString(4));
                p.setPrix_vente(RS.getFloat(5));
                p.setQuantite(RS.getInt(6));
                CategorieCRUD categorieCRUD = new CategorieCRUD(); // instancier un objet de la classe CategorieCRUD
                int categorieId = RS.getInt(7);
                categorie categoriee = categorieCRUD.getCategorieById(categorieId);
                p.setCategoriee(categoriee);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }

    @Override
    public List<produit> filtreByPrixVente(float min, float max) throws SQLException {
        List<produit> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM produit WHERE prix_vente BETWEEN " + min + " AND " + max;
            Statement st = conn.createStatement();
            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                produit p = new produit();
                p.setRef_produit(RS.getString(1));
                p.setLibelle(RS.getString(2));
                p.setDescription(RS.getString(3));
                p.setImage(RS.getString(4));
                p.setPrix_vente(RS.getFloat(5));
                p.setQuantite(RS.getInt(6));
                CategorieCRUD categorieCRUD = new CategorieCRUD(); // instancier un objet de la classe CategorieCRUD
                int categorieId = RS.getInt(7);
                categorie categoriee = categorieCRUD.getCategorieById(categorieId);
                p.setCategoriee(categoriee);
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public List<produit> filtrerProduitParQuantite(int quantite) throws SQLException {
        List<produit> list = new ArrayList<>();
        try {
            String req = "Select * from produit where quantite >= ?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, quantite);
            ResultSet RS = ps.executeQuery();
            while (RS.next()) {
                produit p = new produit();
                p.setRef_produit(RS.getString(1));
                p.setLibelle(RS.getString(2));
                p.setDescription(RS.getString(3));
                p.setImage(RS.getString(4));
                p.setPrix_vente(RS.getFloat(5));
                p.setQuantite(RS.getInt(6));
                CategorieCRUD categorieCRUD = new CategorieCRUD(); // instancier un objet de la classe CategorieCRUD
                int categorieId = RS.getInt(7);
                categorie categoriee = categorieCRUD.getCategorieById(categorieId);
                p.setCategoriee(categoriee);
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public List<produit> filtreByCategorie(int idCategorie) throws SQLException {
        List<produit> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM produit WHERE id_categorie = ?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, idCategorie);
            ResultSet RS = ps.executeQuery();
            while (RS.next()) {
                produit p = new produit();
                p.setRef_produit(RS.getString(1));
                p.setLibelle(RS.getString(2));
                p.setDescription(RS.getString(3));
                p.setImage(RS.getString(4));
                p.setPrix_vente(RS.getFloat(5));
                p.setQuantite(RS.getInt(6));
                CategorieCRUD categorieCRUD = new CategorieCRUD();
                categorie categoriee = categorieCRUD.getCategorieById(idCategorie);
                p.setCategoriee(categoriee);
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public List<produit> rechercher(String libelle) throws SQLException {
        List<produit> result = afficherProduit().stream().
                filter(line -> line.getLibelle().toString().toLowerCase().contains(libelle.toLowerCase()))
                .collect(Collectors.toList());
        //System.out.println("----------");
        //result.forEach(System.out::println);
        return result;
    }

    public List<produit> triecroissant() throws SQLException {
        List<produit> list = new ArrayList<>();
        try {
            String req = "Select * from produit order by `prix_vente` asc";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                produit p = new produit();
                p.setRef_produit(RS.getString(1));
                p.setLibelle(RS.getString(2));
                p.setDescription(RS.getString(3));
                p.setImage(RS.getString(4));
                p.setPrix_vente(RS.getFloat(5));
                p.setQuantite(RS.getInt(6));
                CategorieCRUD categorieCRUD = new CategorieCRUD(); // instancier un objet de la classe CategorieCRUD
                int categorieId = RS.getInt(7);
                categorie categoriee = categorieCRUD.getCategorieById(categorieId);
                p.setCategoriee(categoriee);
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;

    }

    public List<produit> triedec() throws SQLException {
        List<produit> list = new ArrayList<>();
        try {
            String req = "Select * from produit order by `prix_vente` desc";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                produit p = new produit();
                p.setRef_produit(RS.getString(1));
                p.setLibelle(RS.getString(2));
                p.setDescription(RS.getString(3));
                p.setImage(RS.getString(4));
                p.setPrix_vente(RS.getFloat(5));
                p.setQuantite(RS.getInt(6));
                CategorieCRUD categorieCRUD = new CategorieCRUD(); // instancier un objet de la classe CategorieCRUD
                int categorieId = RS.getInt(7);
                categorie categoriee = categorieCRUD.getCategorieById(categorieId);
                p.setCategoriee(categoriee);
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;

    }

    public List<String> listesnumTel() {
        List<Integer> listInt = new ArrayList<>();
        try {
            String req = "Select numTel from utilisateur";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                int numero = RS.getInt("num_tel");
                listInt.add(numero);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        List<String> listString = new ArrayList<>();
        for (Integer numeroInt : listInt) {
            String numString = String.valueOf(numeroInt);
            listString.add(numString);
        }
        return listString;

    }
    public int prodCount() {
        int count = 0;
        try {
            String req = "SELECT COUNT(*) as produit_count FROM produit ";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                count = RS.getInt("produit_count");
                return count;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
    }
public  ResultSet categorieprodcount(){
      try {
            String req = "SELECT categorie.nom_categorie, COUNT(produit.ref_produit) as product_count FROM categorie JOIN produit ON categorie.id_categorie = produit.id_categorie GROUP BY categorie.nom_categorie";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            return RS ;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    
    
    return null;
    
}
public List<String> getPhoneNumbersByCategoryId(int categoryId) throws SQLException {
    String sql = "SELECT DISTINCT u.numTel " +
                 "FROM utilisateur u " +
                 "JOIN panier p ON u.id_user = p.id_user " +
                 "JOIN commande c ON p.id_panier = c.id_panier " +
                 "JOIN command_produit cp ON c.id_cmd = cp.id_cmd " +
                 "JOIN produit pr ON cp.ref_produit = pr.ref_produit " +
                 "WHERE pr.id_categorie = ? AND u.numTel IS NOT NULL";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, categoryId);
    ResultSet rs = pstmt.executeQuery();
    List<String> phoneNumbers = new ArrayList<>();
    while (rs.next()) {
       String phoneNumber = String.valueOf(rs.getInt("numTel"));
       phoneNumbers.add(phoneNumber);
    }
    rs.close();
   
    return phoneNumbers;
}
public List<String> getEmailsByCategoryId(int categoryId) throws SQLException {
    String sql = "SELECT DISTINCT u.email " +
                 "FROM utilisateur u " +
                 "JOIN panier p ON u.id_user = p.id_user " +
                 "JOIN commande c ON p.id_panier = c.id_panier " +
                 "JOIN command_produit cp ON c.id_cmd = cp.id_cmd " +
                 "JOIN produit pr ON cp.ref_produit = pr.ref_produit " +
                 "WHERE pr.id_categorie = ? AND u.email IS NOT NULL";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, categoryId);
    ResultSet rs = pstmt.executeQuery();
    List<String> emails = new ArrayList<>();
    while (rs.next()) {
       String email = rs.getString("email");
       emails.add(email);
    }
    rs.close();
   
    return emails;
}
 public List<produit> min5prod() {
        List<produit> prod = new ArrayList<>();

        try {

            // Get the current month
            java.util.Date now = new java.util.Date();
            java.sql.Date currentMonth = new java.sql.Date(now.getTime());

            // Execute the SQL query
            String sql = "SELECT ref_produit, COUNT(*) as count FROM command_produit WHERE MONTH(date_cmd) = MONTH(?) AND YEAR(date_cmd) = YEAR(?) GROUP BY ref_produit ORDER BY count ASC LIMIT 5";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, currentMonth);
            stmt.setDate(2, currentMonth);
            ResultSet rs = stmt.executeQuery();

            // Print the results
            while (rs.next()) {
                
                ProduitCRUD pc = new ProduitCRUD();
                produit p2 = pc.getByRefProduit(rs.getString("ref_produit"));
                p2.setQuantite(rs.getInt("count"));
                prod.add(p2);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prod;
    }
}
