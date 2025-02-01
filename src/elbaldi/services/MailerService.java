/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.services;

import java.util.*;
import javax.mail.*;
import elbaldi.models.*;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.internet.*;

/**
 *
 * @author houss
 */
public class MailerService {

    Session session;
    String ourMail = "elbaldinotification@gmail.com";

    public MailerService() {

        String password = "eymmlmaxtvwotrzo";
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        properties.put("mail.smtp.port", "587");
        String host = "localhost";
        Session newsession = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ourMail, password);
            }
        });
        this.session = newsession;
    }

    public void sendCommandeMail(commande c) {
        // session.setDebug(true);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(ourMail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(c.getPan().getU1().getEmail()));
            message.setSubject("Confirmation de commande");
            // Create the message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message
            String emailBody = "Bonjour " + c.getPan().getU1().getPrenom() + "!,\n\n"
                    + "Merci pour votre commande n°:"
                    + c.getId_cmd() + "\n\n"
                    + "Vous recevrez rapidement un mail lorsque celle-ci sera envoyée.\n\n"
                    + "A bientôt !\n"
                    + "L'équipe Elbaldi";
            messageBodyPart.setText(emailBody);
            MimeBodyPart attachmentPart = new MimeBodyPart();

            // Set the file path as a DataSource
            FileDataSource fileDataSource = new FileDataSource("C:\\xampp\\htdocs\\images\\ordrePdfn" + c.getId_cmd() + ".pdf");
            attachmentPart.setDataHandler(new DataHandler(fileDataSource));
            attachmentPart.setFileName(fileDataSource.getName());
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);

            // Set the multipart message as the content of the email
            message.setContent(multipart);
            //message.setText(emailBody);

            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            throw new RuntimeException(e);

        }
    }

    public void sendLivraisonMail(livraison l, commande c) {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(ourMail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(c.getPan().getU1().getEmail()));
            message.setSubject("Votre collier est en route!");
            String emailBody = "cher(e) " + c.getPan().getU1().getPrenom() + "!,\n\n"
                    + "Nous vous remercions d'avoir fait vos achats chez nous. Votre numéro de commande :"
                    + c.getId_cmd()
                    + " a été expédié. Vous pouvez trouver ci-dessous les détails de votre commande :\n\n"
                    + "INFORMATIONS SUR LA COMMANDE\n"
                    + "Numéro de commande :" + c.getId_cmd() + " passé le " + c.getDate_cmd() + "\n"
                    + "Adresse de livraison :" + l.getAdresse_livraison() + "\n"
                    + "Valeur des produits :" + c.getTotal() + "TND \n"
                    + "Cordialement,\n"
                    + "L'équipe Elbaldi";
            message.setText(emailBody);

            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendAjoutProdCategnMail(List<String> list) {

        try {
            InternetAddress[] addresses = new InternetAddress[list.size()];
            for (int i = 0; i < list.size(); i++) {
                addresses[i] = new InternetAddress(list.get(i));
            }

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(ourMail));
            message.setRecipients(Message.RecipientType.TO, addresses);
            message.setSubject("Nouveau produit ajouté à votre catégorie préférée!");
            String emailBody = "Cher(e) client,\n"
                    + "J'espère que vous allez bien. Je voulais vous informer que nous avons ajouté un nouveau produit à votre catégorie préférée."
                    + "Nous avons sélectionné ce produit en pensant à vos besoins et à vos préférences. Nous pensons que cela pourrait être une excellente option pour vous."
                    + "Merci de votre confiance en notre entreprise. Nous espérons que vous apprécierez le nouveau produit autant que nous."
                    + "Cordialement,\n"
                    + "ElBaldi";
            message.setText(emailBody);

            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendPromo(promotion p) {
        // session.setDebug(true);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(ourMail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(p.getU().getEmail()));
            message.setSubject("Code Promo");
            String emailBody = "Bonjour " + p.getU().getPrenom() + "!,\n\n"
                    + "Vous avez gagné une reduction de "
                    + p.getTaux() * 100 + "%\n\n"
                    + "Voici votre code promo.\n\n"
                    + p.getCode_promo() + "\n\n"
                    + "A bientôt !\n"
                    + "L'équipe Elbaldi";

            message.setText(emailBody);

            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            throw new RuntimeException(e);

        }
    }

    public void sendParticipationMail(Participation p, Evenement e,Utilisateur u ) {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(ourMail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(u.getEmail()));
            message.setSubject("Votre participation est confirmé!");
            String emailBody
                    = "cher(e)"+ u .getPrenom() + 
                    "Nous vous remercions d'avoir fait votre participation. Votre numéro de participation :"
                    + p.getId_participation() + "\n"
                    + "Nom de l'événement : \n"
                    + e.getNom()+"\n"
                    + "Cordialement,\n"
                    + "L'équipe Elbaldi";
            message.setText(emailBody);

            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException ex) {
            throw new RuntimeException(ex);
        }
    }

}
