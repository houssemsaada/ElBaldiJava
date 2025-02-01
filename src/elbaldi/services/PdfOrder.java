/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.services;

import com.itextpdf.text.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import elbaldi.models.commande;
import java.io.FileOutputStream;
import javafx.scene.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import elbaldi.models.produit;

/**
 *
 * @author houss
 */
public class PdfOrder {

    public void orderPdf(commande c) {
        try {

            Document document = new Document();
           // PdfWriter.getInstance(document, new FileOutputStream("C:\\ordrePdfn" + c.getId_cmd() + ".pdf"));
             PdfWriter.getInstance(document, new FileOutputStream("C:\\xampp\\htdocs\\images\\ordrePdfn" + c.getId_cmd() + ".pdf"));
            document.open();
            com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
            com.itextpdf.text.Font fontmini = FontFactory.getFont(FontFactory.COURIER, 11, BaseColor.BLACK);
            com.itextpdf.text.Font fontgras = FontFactory.getFont(FontFactory.TIMES_BOLD, 14, BaseColor.BLACK);
            com.itextpdf.text.Font fontgrastitre = FontFactory.getFont(FontFactory.TIMES_BOLD, 50, BaseColor.BLACK);
            document.add(new Paragraph("\n\n"));
            Chunk chunk = new Chunk("  Facture ", fontgrastitre);
            document.add(chunk);
            document.add(new Paragraph("\n\n\n\n"));

            Image image = Image.getInstance("http://localhost/images/elbaldi.jpg");
            image.scaleToFit(100, 100);
            image.setAbsolutePosition(460, 720);
            document.add(image);
            chunk = new Chunk("  Vendeur: ", fontgras);
            document.add(chunk);
            chunk = new Chunk("      ElBaldi ", font);
            document.add(chunk);
            document.add(new Paragraph("\n\n"));

            chunk = new Chunk("  Client: ", fontgras);
            document.add(chunk);
            chunk = new Chunk("      " + c.getPan().getU1().getPrenom() + " " + c.getPan().getU1().getNom(), font);
            document.add(chunk);
            document.add(new Paragraph("\n"));
            chunk = new Chunk("                " + c.getAdresse(), fontmini);
            document.add(chunk);
            document.add(new Paragraph("\n\n"));
            chunk = new Chunk("  Date de facturation   Référence de commande   Nombre des articles ", fontgras);
            document.add(chunk);
            document.add(new Paragraph("\n"));
            chunk = new Chunk("  " + c.getDate_cmd() + "               " + c.getId_cmd() + "                    " + c.getPan().getNombre_article(), fontmini);
            document.add(chunk);
            document.add(new Paragraph("\n\n\n\n"));
            PdfPTable table = new PdfPTable(4);
            PdfPCell cell = new PdfPCell();
            cell.setPhrase(new com.itextpdf.text.Phrase("Description\n ",fontgras));
            table.addCell(cell);
            cell.setPhrase(new com.itextpdf.text.Phrase("Quantité",fontgras));
            table.addCell(cell);
            cell.setPhrase(new com.itextpdf.text.Phrase("prix untiaire",fontgras));
            table.addCell(cell);
            cell.setPhrase(new com.itextpdf.text.Phrase("total",fontgras));
            table.addCell(cell);
            // Add cells to table
            for (int i = 0; i < c.getPan().getList().size(); i++) {
                produit produit = c.getPan().getList().get(i);
                
                cell.setPhrase(new com.itextpdf.text.Phrase(produit.getLibelle()));
                table.addCell(cell);
                 cell.setPhrase(new com.itextpdf.text.Phrase(produit.getQuantite()+"\n "));
                table.addCell(cell);
                 cell.setPhrase(new com.itextpdf.text.Phrase(produit.getPrix_vente()+""));
                table.addCell(cell);
                Float total_produit = produit.getPrix_vente() * produit.getQuantite();
                 cell.setPhrase(new com.itextpdf.text.Phrase(total_produit+""));
                table.addCell(cell);
            }

            document.add(table);
            document.add(new Paragraph("\n\n"));
            chunk = new Chunk("                                                                              Total :  ", fontgras);
            
            document.add(chunk);
            chunk = new Chunk("  " + c.getTotal() + " DT", fontgras);
            document.add(chunk);
//            chunk = new Chunk("    total  :", fontgras);
//            pdfPCell1.addElement(chunk);
//            chunk = new Chunk("    " + c.getTotal() + " TND", font);
//            pdfPCell1.addElement(chunk);

//            document.add(pdfPTable);

            document.close();
            System.out.println("pdf ajouter !");
        } catch (Exception e) {
            System.out.println("Error PDF " + e.getMessage());

        }

    }
}
