package elbaldi.services;

//import com.jfoenix.controls.JFXRadioButton;
import elbaldi.interfaces.InterfaceCRUD;
import elbaldi.models.Etat;
import elbaldi.models.Role;
import elbaldi.models.Utilisateur;
import elbaldi.utils.MyConnection;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import org.ini4j.Wini;
import org.mindrot.jbcrypt.BCrypt;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UtilisateurCRUD implements InterfaceCRUD<Utilisateur> {

    private Connection conn;
    private PreparedStatement pst;
    private Statement ste;

    PreparedStatement stee;
    public String n, m;
    public String passwordF;

    public static UserSession userSession;

    public UtilisateurCRUD() {
        conn = MyConnection.getInstance().getConn();

    }

    @Override
    public boolean ajouterUtlisateur(Utilisateur user) {
        Statement stmt;

        try {
            String requete = "INSERT INTO `utilisateur` (`nom`,`prenom`,`email`,`dateDeNaissance`,`numTel`,`ville`,`mdp`,`roles`,`etat`) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, user.getNom());
            pst.setString(2, user.getPrenom());
            pst.setString(3, user.getEmail());
            pst.setDate(4, user.getDateNaissance());
            pst.setInt(5, user.getNumTel());
            pst.setString(6, user.getVille());
            pst.setString(7, BCrypt.hashpw(user.getMdp(), BCrypt.gensalt()));
            pst.setString(8, user.getRole().toString());
            pst.setString(9, user.getEtat().toString());

            if (pst.executeUpdate() > 0) {
                System.out.println("You have registered successfully.");
                return true;
            } else {
                System.out.println("Something went wrong.");
                return false;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public void modifierUtilisateur(Utilisateur u, int id_user) {
        {
            try {
                String req = "UPDATE `utilisateur` SET `nom` = '" + u.getNom() + "', `prenom` = '" + u.getPrenom() + "',`email` = '" + u.getEmail() + "',`dateDeNaissance` = '" + u.getDateNaissance() + "',`numTel` = '" + u.getNumTel() + "',`ville` = '" + u.getVille() + "',`login` = '" + "',`mdp` = '" + u.getMdp() + "',`roles` = '" + u.getRole() + "' WHERE `id_user` = " + id_user;
                Statement st = conn.createStatement();
                st.executeUpdate(req);
                System.out.println("Utilisateur updated !");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    public boolean UpdateUserPassword(Utilisateur user) {
        try {

            //if password exist
            if (!user.getMdp().equals("")) {

                String requete = "UPDATE  utilisateur "
                        + "set "
                        + "mdp=?"
                        + " where email=?";
                PreparedStatement pst = conn.prepareStatement(requete);

                pst.setString(1, BCrypt.hashpw(user.getMdp(), BCrypt.gensalt()));
                pst.setString(2, user.getEmail());

                if (pst.executeUpdate() > 0) {
                    System.out.println("You have updated successfully.");
                    return true;
                } else {
                    System.out.println("Something went wrong.");
                    return false;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return false;
    }

    @Override
    public List<Utilisateur> afficherUtilisateur() {
        List<Utilisateur> list = new ArrayList<>();
        try {
            String req = "Select * from utilisateur";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Utilisateur u = new Utilisateur();

                u.setid_user(RS.getInt(1));
                u.setNom(RS.getString(2));
                u.setPrenom(RS.getString(3));
                u.setEmail(RS.getString(4));

                u.setDateNaissance(RS.getDate(5));
                u.setNumTel(RS.getInt(6));

                u.setVille(RS.getString(7));
                u.setMdp(RS.getString(8));
                u.setRole(Role.valueOf(RS.getString(9)));
                u.setEtat(Etat.valueOf(RS.getString(10)));
                list.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public Integer GetuserBytel(String email) {
        Utilisateur user = null;
        int number = 0;
        try {
            String requete = "Select numTel from utilisateur where email = ?";
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, email);
            ResultSet rs;
            rs = pst.executeQuery();
            if (rs.next()) {
                number = rs.getInt("numTel");
                user = new Utilisateur(
                        rs.getInt("numTel"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return number;
    }

    public String sendMail(String mail) throws SQLException {

        String requete = "SELECT email FROM utilisateur WHERE email=? ";
        PreparedStatement pst = conn.prepareStatement(requete);
        pst.setString(1, mail);
        ResultSet rs;
        rs = pst.executeQuery();
        while (rs.next()) {
            mail = rs.getString("email");
            //password = rs.getString("password");
        }
        return mail;

    }

    @Override
    public void supprimerUtilisateur(int i) {
        String req = "DELETE FROM `utilisateur` WHERE id_user =?";
        try {
            stee = conn.prepareStatement(req);
            stee.setInt(1, i);
            stee.executeUpdate();
            System.out.println("User Deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void upDateStatus(int i) {
        String req = "UPDATE `utilisateur` SET etat='accepted' where id_user=?";
        try {
            stee = conn.prepareStatement(req);
            stee.setInt(1, i);
            stee.executeUpdate();
            System.out.println("User Approuved");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean UpdateUser(Utilisateur user) {
        try {

            //if password exist
            String requete = "UPDATE  utilisateur "
                    + "set nom=?,prenom=?,"
                    + "datedenaissance=?,"
                    + "numTel=?,"
                    + "ville=?"
                    + " where id_user=?";
            PreparedStatement pst = conn.prepareStatement(requete);

            pst.setString(1, user.getNom());
            pst.setString(2, user.getPrenom());

            pst.setDate(3, user.getDateNaissance());
            pst.setInt(4, user.getNumTel());
            pst.setString(5, user.getVille());
            pst.setInt(6, user.getid_user());

            if (pst.executeUpdate() > 0) {
                System.out.println("You have updated successfully.");
                return true;
            } else {
                System.out.println("Something went wrong.");
                return false;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Utilisateur getUserByID(int id_user) throws SQLException {
        String querry = "SELECT *  FROM utilisateur WHERE `id_user`=" + id_user;
        Statement stm = conn.createStatement();
        ResultSet RS = stm.executeQuery(querry);

        Utilisateur u = new Utilisateur();
        while (RS.next()) {

            u.setid_user(RS.getInt(1));
            u.setNom(RS.getString(2));
            u.setPrenom(RS.getString(3));
            u.setEmail(RS.getString(4));

            u.setDateNaissance(RS.getDate(5));
            u.setNumTel(RS.getInt(6));

            u.setVille(RS.getString(7));
            u.setMdp(RS.getString(8));
            u.setRole(Role.valueOf(RS.getString(9)));

        }
        return u;
    }

    public Utilisateur GetUserByMail(String mail, String password) {
        Utilisateur user = null;
        Utilisateur u1 = new Utilisateur();
        int id = 0;
        String pass = "";
        Role role = null;
        try {
            String requete = "Select id_user,roles,mdp, email from utilisateur where email = ?";
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, mail);
            ResultSet rs;
            rs = pst.executeQuery();

            if (rs.next()) {
                pass = rs.getString("mdp");
                id = rs.getInt("id_user");
                System.out.println(rs.getString("roles") + "aaa");
                System.out.println(u1.reformRole(rs.getString("roles")));
                role = u1.reformRole(rs.getString("roles"));
            }
            System.out.println(role);

//            if (BCrypt.checkpw(password, pass)) {
            requete = "SELECT nom,prenom,email,dateDeNaissance,numTel,ville,etat FROM utilisateur WHERE email like ?";
            pst = conn.prepareStatement(requete);
            pst.setString(1, mail);
            rs = pst.executeQuery();
            while (rs.next()) {
                user = new Utilisateur(
                        id,
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        mail,
                        rs.getDate("dateDeNaissance"),
                        rs.getInt("numTel"),
                        rs.getString("ville"),
                        pass,
                        role,
                        u1.reformEtat(rs.getString("etat"))
                );

            }
            System.out.println(user);

//            } else {
//                System.out.println("aaazzzzzz");
//                return null;
//            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("problem in gettinbymail");
            return null;
        } catch (StringIndexOutOfBoundsException ex) {
            /*Login_pageController lc = new Login_pageController();
            new animatefx.animation.Shake(lc.getPasswordtxt()).play();
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            lc.getPasswordtxt().setEffect(in);
             */
            System.out.println("problem in gettinbymail2");

            return null;
        }
        return user;
    }

    public Utilisateur GetUserByMailE(String mail) {
        Utilisateur user = null;
        try {
            String requete = "Select email from utilisateur where email = ?";
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, mail);
            ResultSet rs;
            rs = pst.executeQuery();

            requete = "SELECT email FROM utilisateur WHERE email like ?";
            pst = conn.prepareStatement(requete);
            pst.setString(1, mail);
            rs = pst.executeQuery();
            while (rs.next()) {
                user = new Utilisateur(
                        mail
                );

                System.out.println(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        } catch (StringIndexOutOfBoundsException ex) {
            /*Login_pageController lc = new Login_pageController();
            new animatefx.animation.Shake(lc.getPasswordtxt()).play();
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            lc.getPasswordtxt().setEffect(in);
             */
            return null;
        }
        return user;
    }

    public Utilisateur GetUserByMailSession(String mail) {
        Utilisateur user = null;

        String pass = "";
        Role role = null;
        try {
            String requete = "Select id_user,roles,mdp, email from utilisateur where email = ?";
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, mail);
            ResultSet rs;
            rs = pst.executeQuery();

            requete = "SELECT id_user,nom,prenom,email,dateDeNaissance,numTel,ville,etat FROM utilisateur WHERE email like ?";
            pst = conn.prepareStatement(requete);
            pst.setString(1, mail);
            rs = pst.executeQuery();
            while (rs.next()) {
                user = new Utilisateur(
                        rs.getInt("id_user"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        mail,
                        rs.getDate("dateDeNaissance"),
                        rs.getInt("numTel"),
                        rs.getString("ville"),
                        pass,
                        role,
                        Etat.valueOf(rs.getString("etat"))
                );

                System.out.println(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        } catch (StringIndexOutOfBoundsException ex) {
            /*Login_pageController lc = new Login_pageController();
            new animatefx.animation.Shake(lc.getPasswordtxt()).play();
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            lc.getPasswordtxt().setEffect(in);
             */
            return null;
        }
        return user;
    }

    public void readinifile(String path, TextField userid, PasswordField passid, CheckBox remember_me) throws IOException {
        File file = new File(path);
        if (file.exists()) {
            Wini wini = new Wini(new File(path));
            String username = wini.get("Login data", "Email");
            String password = wini.get("Login data", "Password");
            if ((username != null && !username.equals("")) && (password != null && !password.equals(""))) {
                userid.setText(username);
                passid.setText(password);
                remember_me.setSelected(true);
            }
        }
    }

    public void createiniFile(String path, String user, String pass) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            Wini wini = new Wini(new File(path));
            wini.put("Login data", "Email", user);
            wini.put("Login data", "Password", pass);
            wini.store();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void Deleteinfo(String path, String user, String pass) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            Wini wini = new Wini(new File(path));
            wini.remove("Login data", "Email");
            wini.remove("Login data", "Password");
            wini.store();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Utilisateur> Filter_utilisateur(String S, String SS) {
        List<Utilisateur> list = new ArrayList<>();
        try {
            if (S.equals("id_user")) {
                int temp = Integer.parseInt(SS);
                String req = "SELECT * FROM utilisateur WHERE " + S + " =" + temp;
                Statement st = conn.createStatement();
                ResultSet RS = st.executeQuery(req);
                while (RS.next()) {
                    Utilisateur u = new Utilisateur();
                    u.setid_user(RS.getInt(1));
                    u.setNom(RS.getString(2));
                    u.setPrenom(RS.getString(3));
                    u.setEmail(RS.getString(4));

                    u.setDateNaissance(RS.getDate(5));
                    u.setNumTel(RS.getInt(6));

                    u.setVille(RS.getString(7));
                    u.setMdp(RS.getString(8));
                    u.setRole(Role.valueOf(RS.getString(9)));

                    list.add(u);
                }
            } else {
                String req = "SELECT * FROM utilisateur WHERE " + S + " =" + "\"" + SS + "\"";
                Statement st = conn.createStatement();
                ResultSet RS = st.executeQuery(req);
                while (RS.next()) {
                    Utilisateur u = new Utilisateur();
                    u.setid_user(RS.getInt(1));
                    u.setNom(RS.getString(2));
                    u.setPrenom(RS.getString(3));
                    u.setEmail(RS.getString(4));

                    u.setDateNaissance(RS.getDate(5));
                    u.setNumTel(RS.getInt(6));

                    u.setVille(RS.getString(7));
                    u.setMdp(RS.getString(8));
                    u.setRole(Role.valueOf(RS.getString(9)));
                    list.add(u);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public String sendInfo(String mail) throws SQLException {
        String requete = "SELECT email,mdp FROM utilisateur WHERE email=? ";
        PreparedStatement pst = conn.prepareStatement(requete);
        pst.setString(1, sendMail(mail));
        ResultSet rs;
        rs = pst.executeQuery();
        System.out.println(m + "" + n + "hehhe");
        while (rs.next()) {
            mail = rs.getString("email");
            passwordF = rs.getString("password");

        }
        BCrypt.checkpw(mail, passwordF);
        System.out.println(passwordF);
        System.out.println("qqqq");
        return passwordF;

    }

    public boolean afficherReclamation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean getReclamationByID(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean Filter_Reclamation(String nom, String iheb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
