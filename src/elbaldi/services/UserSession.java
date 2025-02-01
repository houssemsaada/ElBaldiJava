package elbaldi.services;

import elbaldi.models.Utilisateur;

import java.sql.Date;
import java.util.Objects;

public class UserSession {
  

    public static String userMail;
    public static String userString;
    public static Date userDate;
    private final UtilisateurCRUD userService = new UtilisateurCRUD();

    
    public void setUserId(String email,String mail)
    {
        if(Objects.equals(email, "")) return ;
        UserSession.userMail = email ;
        UserSession.userString=mail;
    }
    
    public String getUserEmail() {
        return userMail;
    }

    public void setUserEmail(String userIntEmail) {
        UserSession.userMail = userIntEmail;
    }

    public static Date getUserDate() {
        return userDate;
    }

    public static void setUserDate(Date userDate) {
        UserSession.userDate = userDate;
    }

    public static String getUserString() {
        return userString;
    }

    public static void setUserString(String userString) {
        UserSession.userString = userString;
    }
    public static String getUserId()
    {
        return UserSession.userMail;
    }
    
    public Utilisateur getUser()
    {
        return userService.GetUserByMailSession(userMail);
    }

    
}