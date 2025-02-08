package com.did.MyShop.services.statistique;

import com.did.MyShop.DTO.statistique.CommandeStatistique;
import com.did.MyShop.DTO.statistique.VenteParJourDTO;
import com.did.MyShop.entities.Commande.Commande;
import com.did.MyShop.entities.Commande.Paiement;
import com.did.MyShop.entities.Produit.Produit;
import com.did.MyShop.enums.MethodePaiementEnum;
import com.did.MyShop.repositories.commande.CommandeRepository;
import com.did.MyShop.repositories.commande.LigneCommandeRepository;
import com.did.MyShop.repositories.commande.PaiementRepository;
import com.did.MyShop.repositories.commande.PromotionRepository;
import com.did.MyShop.repositories.produit.ProduitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class commandeStatistiqueService {

    private final CommandeRepository commandeRepository;
    private final LigneCommandeRepository ligneCommandeRepository;
    private ProduitRepository produitRepository;
    private PaiementRepository paiementRepository;
    private PromotionRepository promotionRepository;


    public CommandeStatistique venteStatistique() {
        return null;
    }

    public List<Object[]> getVentesParProduit() {
        return commandeRepository.findTotalVentesParProduit();
    }

    public List<Object[]> getQuantiteVendueParProduit() {
        return commandeRepository.findQuantiteVendueParProduit();
    }




    // Rapport global des ventes et bénéfices
    public Map<String, Double> getVentesEtBenefices() {
        Double totalVentes = 100D ;/*commandeRepository.findTotalVentes();*/
        Double totalAchats = produitRepository.findProduitsVendus().stream().mapToDouble(Produit::getPrix).sum();
        Double benefices = totalVentes - totalAchats;

        Map<String, Double> rapport = new HashMap<>();
        rapport.put("totalVentes", totalVentes);
        rapport.put("totalAchats", totalAchats);
        rapport.put("benefices", benefices);

        return rapport;
    }

    // Rapport des employés et caissiers
    public List<Map<String, Object>> getCommandesParCaissier(Long caissierId) {
        return commandeRepository.findCommandesParCaissier(caissierId).stream()
                .map(commande -> {
                    Map<String, Object> result = new HashMap<>();
                    result.put("commandeId", commande.getId());
                    result.put("client", commande.getClient().getName());
                    result.put("total", commande.getPaiements().stream().mapToDouble(Paiement::getAmount).sum());
                    return result;
                })
                .collect(Collectors.toList());
    }

    // Rapport des stocks avec alertes (ruptures, produits à faible quantité)
    public List<Produit> getProduitsFaibleStock() {
        return produitRepository.findByStockInferieurSeuil();
    }

    // Rapport des transactions suspectes
    public List<Commande> getTransactionsSuspectes() {
        return commandeRepository.findAnnuleesApresPaiement();
    }

    // Rapport des ventes par période
    public List<Commande> getVentesParPeriode(LocalDateTime startDate, LocalDateTime endDate) {
        return commandeRepository.findVentesParPeriode(startDate, endDate);
    }

    // Rapport des bénéfices
    public Double getBenefices() {
        Double totalVentes = 100D;/*commandeRepository.findTotalVentes();*/
        Double totalAchats = produitRepository.findProduitsVendus().stream().mapToDouble(Produit::getPrix).sum();
        return totalVentes - totalAchats;
    }

    // Rapport des promotions appliquées et leur impact sur les ventes
    public List<Map<String, Object>> getImpactPromotions() {
        return promotionRepository.findActives().stream()
                .map(promotion -> {
                    Map<String, Object> result = new HashMap<>();
                    result.put("promotionId", promotion.getId());
                    result.put("name", promotion.getName());
                    result.put("reduction", promotion.getReduction());
                    result.put("impact", commandeRepository.findVentesParPeriode(promotion.getStartDate(), promotion.getEndDate()).size());
                    return result;
                })
                .collect(Collectors.toList());
    }

    // Rapport des moyens de paiement
    public Map<MethodePaiementEnum, Double> getMoyensDePaiement() {
        return paiementRepository.findPaiementsGroupesParMethode();
    }

    public List<Map<String, Object>> getNombreVentesParJour(LocalDateTime startDate, LocalDateTime endDate) {
        List<Object[]> results = commandeRepository.findNombreVentesParJour(startDate, endDate);
        List<Map<String, Object>> ventesParJour = new ArrayList<>();

        for (Object[] row : results) {
            Map<String, Object> data = new HashMap<>();
            data.put("date", row[0]);  // La date
            data.put("nombreVentes", row[1]);  // Le nombre de ventes
            ventesParJour.add(data);
        }
        return ventesParJour;
    }

    public Double getChiffreAffaireParSemaine(LocalDate date) {
        return commandeRepository.findChiffreAffaireParSemaine(date);
    }

    /**
     * Calcule le chiffre d'affaires par jour sur la semaine définie par startDate.
     * La semaine est définie de startDate (inclus) à startDate + 6 jours (inclus).
     *
     * @param userId    l'ID du caissier (User)
     * @param startDate le premier jour de la semaine (format ISO : yyyy-MM-dd)
     * @return une map dont la clé est le nom du jour (ex: "Lundi") et la valeur le CA
     */
    public Map<String, Double> getChiffreAffaireParJourSemaine(Long userId, LocalDate startDate) {
        // Définir la borne de début : début de la journée
        LocalDateTime startDateTime = startDate.atStartOfDay();
        // Fin de la semaine : startDate + 6 jours à 23:59:59.999...
        LocalDateTime endDateTime = startDate.plusDays(6).atTime(LocalTime.MAX);

        List<Object[]> results = commandeRepository.findChiffreAffaireParJourSemaine(userId, startDateTime, endDateTime);
        Map<String, Double> chiffreAffaireParJour = new LinkedHashMap<>();

        // Pour obtenir le nom du jour, on formate la date (en fonction de la locale)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE");

        for (Object[] row : results) {
            // row[0] correspond à la date (jour)
            LocalDate date;
            if (row[0] instanceof LocalDate) {
                date = (LocalDate) row[0];
            } else if (row[0] instanceof java.sql.Date) {
                date = ((java.sql.Date) row[0]).toLocalDate();
            } else {
                date = LocalDate.parse(row[0].toString());
            }
            // row[1] correspond au chiffre d'affaires de ce jour
            Double chiffreAffaire = (Double) row[1];

            String dayName = date.format(formatter);
            chiffreAffaireParJour.put(dayName, chiffreAffaire);
        }
        return chiffreAffaireParJour;
    }


    /**
     * Retourne le montant total des paiements par jour pour une semaine donnée pour un caissier.
     *
     * @param userId    l'ID du caissier (User)
     * @param startDate la date de début de la semaine (ex. 2024-02-01)
     * @return une Map dont la clé est le nom du jour et la valeur le montant total des paiements ce jour
     */
    public Map<String, Double> getMontantPaiementParJourSemaine(Long userId, LocalDate startDate) {
        // Début de la semaine (début de journée)
        LocalDateTime startDateTime = startDate.atStartOfDay();
        // Fin de la semaine : startDate + 6 jours, fin de journée
        LocalDateTime endDateTime = startDate.plusDays(6).atTime(LocalTime.MAX);

        // Exécute la requête dans le repository
        List<Object[]> results = paiementRepository.findMontantPaiementParJourSemaine(userId, startDateTime, endDateTime);

        // Map pour stocker les résultats (on utilise LinkedHashMap pour préserver l'ordre)
        Map<String, Double> montantPaiementParJour = new LinkedHashMap<>();
        // Formatter pour obtenir le nom du jour (ex: "lundi", "mardi", etc.)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE");

        for (Object[] row : results) {
            LocalDate date;
            if (row[0] instanceof LocalDate) {
                date = (LocalDate) row[0];
            } else if (row[0] instanceof java.sql.Date) {
                date = ((java.sql.Date) row[0]).toLocalDate();
            } else {
                date = LocalDate.parse(row[0].toString());
            }
            Double montant = (Double) row[1];
            String dayName = date.format(formatter);
            montantPaiementParJour.put(dayName, montant);
        }

        return montantPaiementParJour;
    }
}


