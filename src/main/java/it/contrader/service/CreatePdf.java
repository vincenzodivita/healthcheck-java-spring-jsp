package it.contrader.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class CreatePdf {

    PDDocument document = new PDDocument();
    PDPage newPage = new PDPage();
    PDPageContentStream content;

    public void createPdf(List<String> contentText, String filePath, String fileName){
        try {
            content = new PDPageContentStream(document, newPage);
            float y = newPage.getCropBox().getHeight();
            content.beginText();
            content.setFont(PDType1Font.COURIER, 15);
            content.newLineAtOffset(20,y-35);
            content.showText("Gentile Cliente,");
            content.newLineAtOffset(0, -15);
            content.showText("Grazie per utilizzare il nostro servizio.");
            content.newLineAtOffset(0, -15);
            content.showText("Qui di seguito trovi il tuo referto medico:");
            content.newLineAtOffset(0, -30);
            for (String line : contentText) {
                content.newLineAtOffset(0, -15);
                content.showText(line);

            }
            content.endText();
            content.close();
            document.addPage(newPage);
            document.save(new File(filePath, fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
