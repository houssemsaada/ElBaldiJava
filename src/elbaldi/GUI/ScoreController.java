/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.quiz;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import elbaldi.GUI.JouerQuizController;
import java.io.IOException;
import java.util.Hashtable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import elbaldi.models.Utilisateur;
import elbaldi.models.promotion;
import elbaldi.services.PromotionCRUD;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Random;
import javafx.embed.swing.SwingFXUtils;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import elbaldi.services.MailerService;
import elbaldi.services.UserSession;
import java.util.Properties;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author selim
 */
public class ScoreController implements Initializable {

    private float score;

    @FXML
    private Label fxscore;
    @FXML
    private Label reduction;
    @FXML
    private Label termine;
    @FXML
    private Label bravo;
    @FXML
    private Label codeqr;
    @FXML
    private Label echec;

    @FXML
    private ImageView qrfix;
    @FXML
    private Hyperlink email;
    @FXML
    private ImageView imgfx;

    float taux;
    String promoCode = "ELBALDI";
    PromotionCRUD pc = new PromotionCRUD();
    UserSession userSession = new UserSession();
    Utilisateur u = userSession.getUser();
    promotion promo = new promotion();
    @FXML
    private ImageView sadfx;
    @FXML
    private Label a;
    @FXML
    private Label aa;
    /**
     * Initializes the controller class.
     */
    public void setscore(float score) throws IOException, WriterException {

        this.score = score;
        System.out.println(score);
        fxscore.setText(Float.toString(score));

        fxscore.setText(String.format("%.0f%%", score));

        if (score == 100.0) {
            bravo.setText(" Bravo !");
            termine.setText("Quiz terminé avec succés");
            codeqr.setText("pour profitez d'une réduction de 15%");
            promo = generatePromoCode((int) score);
            String promoCode = promo.getCode_promo();

            // Affichage du code promo généré dans le label codeqr
            reduction.setText("Scanner ce code QR");
            // Génération du code QR
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            BitMatrix bitMatrix = new QRCodeWriter().encode(promoCode, BarcodeFormat.QR_CODE, 200, 200);
            MatrixToImageWriter.writeToStream(bitMatrix, "png", out);
            byte[] qrBytes = out.toByteArray();
            ByteArrayInputStream in = new ByteArrayInputStream(qrBytes);

            // Conversion du code QR en image
            Image qrImage = SwingFXUtils.toFXImage(ImageIO.read(in), null);

            // Affichage de l'image dans l'ImageView
            qrfix.setImage(qrImage);
            // vboxqr.setVisible(true);

        } else if (score >= 75.0 && score <= 99.99) {
            bravo.setText(" Bravo !");
            termine.setText("Quiz terminé avec succés");
            codeqr.setText("pour profitez d'une réduction de 10%");
            //codeqr.setText(" Veuillez scanner ce code QR pour en profiter");
            promo = generatePromoCode((int) score);
            String promoCode = promo.getCode_promo();
            // Affichage du code promo généré dans le label codeqr
            reduction.setText("Scanner ce code QR");
            // Génération du code QR
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            BitMatrix bitMatrix = new QRCodeWriter().encode(promoCode, BarcodeFormat.QR_CODE, 200, 200);
            MatrixToImageWriter.writeToStream(bitMatrix, "png", out);
            byte[] qrBytes = out.toByteArray();
            ByteArrayInputStream in = new ByteArrayInputStream(qrBytes);

            // Conversion du code QR en image
            Image qrImage = SwingFXUtils.toFXImage(ImageIO.read(in), null);

            // Affichage de l'image dans l'ImageView
            qrfix.setImage(qrImage);
            // vboxqr.setVisible(true);

            // Affichage du code promo généré dans le label codeqr
            // codeqr.setText("avec le code Promo " + promoCode);
        } else if (score >= 0.45 && score <= 74.99) {
            bravo.setText(" Bravo !");
            termine.setText("Quiz terminé avec succés");
            codeqr.setText("pour profitez d'une réduction de 8%");
            //codeqr.setText(" Veuillez scanner ce code QR pour en profiter");
            promo = generatePromoCode((int) score);
            String promoCode = promo.getCode_promo();
            // Affichage du code promo généré dans le label codeqr
            reduction.setText("Scanner ce code QR ");
            // Génération du code QR
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            BitMatrix bitMatrix = new QRCodeWriter().encode(promoCode, BarcodeFormat.QR_CODE, 200, 200);
            MatrixToImageWriter.writeToStream(bitMatrix, "png", out);
            byte[] qrBytes = out.toByteArray();
            ByteArrayInputStream in = new ByteArrayInputStream(qrBytes);

            // Conversion du code QR en image
            Image qrImage = SwingFXUtils.toFXImage(ImageIO.read(in), null);

            // Affichage de l'image dans l'ImageView
            qrfix.setImage(qrImage);
            // vboxqr.setVisible(true);

            // Affichage du code promo généré dans le label codeqr
            // codeqr.setText("avec le code Promo " + promoCode);
        } else {
            imgfx.setVisible(false);
            sadfx.setVisible(true);
            a.setVisible(false);
            email.setVisible(false);
            aa.setVisible(false);
            echec.setText("Désolé, vous n'avez pas réussi le quiz !!!");
        }

    }

    public promotion generatePromoCode(int score) {
        Random random = new Random();
        String promoCode = null;
        boolean codeExiste = true;
        while (codeExiste) {
            promoCode = "ELBALDI";
            for (int i = 0; i < 4; i++) {
                promoCode += String.valueOf(random.nextInt(10));
            }
            codeExiste = pc.promocodeExiste(promoCode);
        }
        if (score == 100) {
            taux = 0.15f;
            promotion promo = new promotion(promoCode, taux, u);
            pc.ajouterpromotion(promo);
            return promo;

        }
        if (score >= 75.0 && score <= 99.99) {
            taux = 0.10f;
            promotion promo = new promotion(promoCode, taux, u);
            pc.ajouterpromotion(promo);
            return promo;

        }
        if (score >= 0.45 && score <= 74.99) {
            taux = 0.08f;
            promotion promo = new promotion(promoCode, taux, u);
            pc.ajouterpromotion(promo);
            return promo;
        }

        return null;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void email(ActionEvent event) {
        MailerService ms = new MailerService();
        ms.sendPromo(promo);
    }

}
