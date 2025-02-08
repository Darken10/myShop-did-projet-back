package com.did.MyShop.controllers.statistique;

import com.did.MyShop.entities.Commande.Commande;
import com.did.MyShop.entities.Produit.Produit;
import com.did.MyShop.enums.MethodePaiementEnum;
import com.did.MyShop.services.statistique.commandeStatistiqueService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistiques")
@AllArgsConstructor
public class commandeStatistiqueController {

    private commandeStatistiqueService rapportService;

    // Rapport global des ventes et bénéfices
    @GetMapping("/ventes-benefices")
    public ResponseEntity<Map<String, Double>> getVentesEtBenefices() {
        return ResponseEntity.ok(rapportService.getVentesEtBenefices());
    }

    // Rapport des employés et caissiers
    @GetMapping("/caissiers/{caissierId}")
    public ResponseEntity<List<Map<String, Object>>> getCommandesParCaissier(@PathVariable Long caissierId) {
        return ResponseEntity.ok(rapportService.getCommandesParCaissier(caissierId));
    }

    // Rapport des stocks avec alertes (ruptures, produits à faible quantité)
    @GetMapping("/stocks")
    public ResponseEntity<List<Produit>> getProduitsFaibleStock() {
        return ResponseEntity.ok(rapportService.getProduitsFaibleStock());
    }

    // Rapport des transactions suspectes
    @GetMapping("/transactions-suspectes")
    public ResponseEntity<List<Commande>> getTransactionsSuspectes() {
        return ResponseEntity.ok(rapportService.getTransactionsSuspectes());
    }

    // Rapport des ventes par période
    @GetMapping("/ventes-par-periode")
    public ResponseEntity<List<Commande>> getVentesParPeriode(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        return ResponseEntity.ok(rapportService.getVentesParPeriode(startDate, endDate));
    }

    // Rapport des bénéfices
    @GetMapping("/benefices")
    public ResponseEntity<Double> getBenefices() {
        return ResponseEntity.ok(rapportService.getBenefices());
    }

    // Rapport des promotions appliquées et leur impact sur les ventes
    @GetMapping("/impact-promotions")
    public ResponseEntity<List<Map<String, Object>>> getImpactPromotions() {
        return ResponseEntity.ok(rapportService.getImpactPromotions());
    }

    // Rapport des moyens de paiement
    @GetMapping("/moyens-de-paiement")
    public ResponseEntity<Map<MethodePaiementEnum, Double>> getMoyensDePaiement() {
        return ResponseEntity.ok(rapportService.getMoyensDePaiement());
    }

    @GetMapping("/ventes-semaine")
    public ResponseEntity<List<Map<String, Object>>> getNombreVentesParJour(
            @RequestParam("startDate") String startDateStr) {

        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate date = LocalDate.parse(startDateStr);;
        LocalDateTime dateTime = date.atStartOfDay(); // Convert LocalDate to LocalDateTime
        LocalDateTime endDate = dateTime.plusDays(6); // Une semaine (7 jours)

        List<Map<String, Object>> ventesParJour = rapportService.getNombreVentesParJour(dateTime, endDate);
        return ResponseEntity.ok(ventesParJour);
    }

    @GetMapping("/chiffre-affaire-semaine")
    public ResponseEntity<Double> getChiffreAffaireParSemaine(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        Double chiffreAffaire = rapportService.getChiffreAffaireParSemaine(date);
        return ResponseEntity.ok(chiffreAffaire != null ? chiffreAffaire : 0.0);
    }


    /**
     * Endpoint pour obtenir le chiffre d'affaires par jour sur une semaine pour un caissier.
     * Exemple d'appel : GET /api/statistiques/chiffre-affaire-caissier-semaine?userId=1&startDate=2024-02-01
     *
     * @param userId    l'ID du caissier (User)
     * @param startDate la date de début de la semaine (format ISO : yyyy-MM-dd)
     * @return une map associant le nom du jour et le chiffre d'affaires de ce jour
     */
    @GetMapping("/chiffre-affaire-caissier-semaine")
    public ResponseEntity<Map<String, Double>> getChiffreAffaireParJourSemaine(
            @RequestParam("userId") Long userId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate) {
        Map<String, Double> result = rapportService.getChiffreAffaireParJourSemaine(userId, startDate);
        return ResponseEntity.ok(result);
    }



    /**
     * Endpoint pour obtenir le montant des paiements par jour sur une semaine pour un caissier.
     * Exemple d'appel :
     * GET /api/statistiques/montant-paiement-caissier-semaine?userId=1&startDate=2024-02-01
     *
     * @param userId    l'ID du caissier (User)
     * @param startDate la date de début de la semaine (format ISO: yyyy-MM-dd)
     * @return une Map associant le nom du jour et le montant total des paiements ce jour
     */
    @GetMapping("/montant-paiement-caissier-semaine")
    public ResponseEntity<Map<String, Double>> getMontantPaiementParJourSemaine(
            @RequestParam("userId") Long userId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate) {
        Map<String, Double> result = rapportService.getMontantPaiementParJourSemaine(userId, startDate);
        return ResponseEntity.ok(result);
    }


}
