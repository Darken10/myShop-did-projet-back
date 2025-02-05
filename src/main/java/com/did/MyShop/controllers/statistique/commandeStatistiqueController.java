package com.did.MyShop.controllers.statistique;

import com.did.MyShop.DTO.statistique.CommandeStatistique;
import com.did.MyShop.DTO.statistique.VenteParJourDTO;
import com.did.MyShop.services.statistique.commandeStatistiqueService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/auth/test")
@AllArgsConstructor
public class commandeStatistiqueController {

    private final commandeStatistiqueService commandeStatistiqueService;

    @GetMapping("commande-statistique")
    public CommandeStatistique getCommandeStatistique(){
        return commandeStatistiqueService.venteStatistique();
    }

    @GetMapping("/ventes-par-produit")
    public List<Object[]> getVentesParProduit() {
        return commandeStatistiqueService.getVentesParProduit();
    }

    @GetMapping("/quantite-par-produit")
    public List<Object[]> getQuantiteVendueParProduit() {
        return commandeStatistiqueService.getQuantiteVendueParProduit();
    }

    /*@GetMapping("/ventes-par-semaine")
    public List<VenteParJourDTO> getVentesParSemaine(@RequestParam("startDate") String startDateStr,
                                                     @RequestParam("endDate") String endDateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDate = LocalDateTime.parse(startDateStr, formatter);
        LocalDateTime endDate = LocalDateTime.parse(endDateStr, formatter);

        return commandeStatistiqueService.getVentesParSemaine(startDate, endDate);
    }*/

}
