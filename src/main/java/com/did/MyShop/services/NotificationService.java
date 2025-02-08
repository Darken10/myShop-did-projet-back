package com.did.MyShop.services;


import com.did.MyShop.entities.Produit.Produit;
import com.did.MyShop.entities.User.User;
import com.did.MyShop.repositories.produit.ProduitRepository;
import com.did.MyShop.repositories.users.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class NotificationService {

    private final JavaMailSender mailSender;
    private final UserRepository userRepository;
    private final ProduitRepository produitRepository;

    /**
     * Envoie un e-mail HTML aux gestionnaires contenant un tableau des produits en carence.
     */
    public void notifyManagersStockInsuffisant() throws MessagingException {
        // Récupérer la liste des produits en carence
        List<Produit> produitsEnCarence = produitRepository.findProduitsEnCarence();

        // Si aucun produit en carence, pas d'envoi d'email
        if (produitsEnCarence.isEmpty()) {
            return;
        }

        // Récupérer tous les utilisateurs ayant le rôle "GESTIONNAIRE"
        List<User> managers = userRepository.findUsersByRole("GESTIONNAIRE");

        String subject = "Alerte : Stock insuffisant pour certains produits";

        // Construire le corps de l'e-mail en HTML avec un tableau
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body>");
        sb.append("<h2>Liste des produits en carence</h2>");
        sb.append("<table border='1' style='border-collapse: collapse; width: 100%;'>");
        sb.append("<thead>")
                .append("<tr>")
                .append("<th style='padding: 8px; text-align: left;'>Nom du produit</th>")
                .append("<th style='padding: 8px; text-align: left;'>Stock actuel</th>")
                .append("<th style='padding: 8px; text-align: left;'>Seuil d'alerte</th>")
                .append("<th style='padding: 8px; text-align: left;'>Description</th>")
                .append("</tr>")
                .append("</thead>");
        sb.append("<tbody>");
        for (Produit produit : produitsEnCarence) {
            sb.append("<tr>")
                    .append("<td style='padding: 8px;'>").append(produit.getLibelle()).append("</td>")
                    .append("<td style='padding: 8px;'>").append(produit.getStock()).append("</td>")
                    .append("<td style='padding: 8px;'>").append(produit.getSeuil()).append("</td>")
                    .append("<td style='padding: 8px;'>").append(produit.getDescription() != null ? produit.getDescription() : "").append("</td>")
                    .append("</tr>");
        }
        sb.append("</tbody>");
        sb.append("</table>");
        sb.append("<p>Veuillez procéder au réapprovisionnement dès que possible.</p>");
        sb.append("<br><p>Cordialement,<br>Votre Système de Gestion</p>");
        sb.append("</body></html>");

        String htmlBody = sb.toString();

        // Envoyer l'e-mail à chaque gestionnaire
        for (User manager : managers) {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(manager.getEmail());
            helper.setSubject(subject);
            helper.setText(htmlBody, true); // true pour indiquer que c'est du HTML
            mailSender.send(message);
        }
    }
}


