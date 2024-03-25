package it.contrader.service;

import it.contrader.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;


@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private CreatePdf pdf;

    public void sendEmail( String toEmail, String subject, List<String> referto){
        String fileName = "Analisi"+ System.currentTimeMillis()  +".pdf";
        pdf.createPdf(referto, "src/main/webapp/pdf/", fileName);

        try {

            MimeMessage message = mailSender.createMimeMessage();


            MimeMessageHelper helper = new MimeMessageHelper(message, true);


            helper.setFrom("healthcheckcontrader@gmail.com");
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText("Gentile Cliente,\n\n Abbiamo inserito con successo i risultati delle tue analisi.\n Se hai domande o necessiti di ulteriori informazioni, non esitare a contattarci.\n\n Grazie per aver scelto HealthCheck!\n\n Cordiali saluti,\n Il team di HealthCheck");

            File pdfFile = new File("src/main/webapp/pdf/" + fileName );
            helper.addAttachment(fileName, pdfFile);

            mailSender.send(message);

            System.out.println("Mail inviata con successo");
        } catch (MessagingException | MailException e) {
            throw new RuntimeException("Errore durante l'invio dell'email", e);
        }
    }

}
