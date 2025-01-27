package com.did.MyShop.auth;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class SendEmail {

    private final JavaMailSender javaMailSender;

    public void sendResetPasswordEmail(String to, String link) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(to);
        helper.setSubject("Réinitialisez votre mot de passe");
        helper.setFrom("no-reply@myshop.com");
        // Contenu HTML
        String htmlContent = """
            <html>
            <body>
                <h1>Réinitialisation de votre mot de passe</h1>
                <p>Bonjour,</p>
                <p>Vous avez demandé la réinitialisation de votre mot de passe. Cliquez sur le bouton ci-dessous pour procéder :</p>
                <a href="%s" style="display: inline-block; padding: 10px 20px; color: white; background-color: #007BFF; text-decoration: none; border-radius: 5px;">Réinitialiser le mot de passe</a>
                <p>Si vous n'avez pas demandé cette action, vous pouvez ignorer ce message.</p>
                <p>Cordialement,<br>L'équipe de support</p>
            </body>
            </html>
        """.formatted(link);

        helper.setText(htmlContent, true); // True pour indiquer que le contenu est en HTML
        javaMailSender.send(mimeMessage);
    }

    public void sendEmail(String to, String subject, String body) {
        log.info("Sending email to {}", to);
        System.out.println("Sending email to : " + to);
        System.out.println("Sending subject : " + subject);
        System.out.println("Sending body : " + body);
        //TODO : on doit envoyer un mail contenant le lien front avec le jeton en params
    }
}
