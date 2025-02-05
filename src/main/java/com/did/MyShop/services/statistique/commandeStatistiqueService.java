package com.did.MyShop.services.statistique;

import com.did.MyShop.DTO.statistique.CommandeStatistique;
import com.did.MyShop.DTO.statistique.VenteParJourDTO;
import com.did.MyShop.repositories.commande.CommandeRepository;
import com.did.MyShop.repositories.commande.LigneCommandeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class commandeStatistiqueService {

    private final CommandeRepository commandeRepository;
    private final LigneCommandeRepository ligneCommandeRepository;


    public CommandeStatistique venteStatistique() {
        return null;
    }

    public List<Object[]> getVentesParProduit() {
        return commandeRepository.findTotalVentesParProduit();
    }

    public List<Object[]> getQuantiteVendueParProduit() {
        return commandeRepository.findQuantiteVendueParProduit();
    }

   /* public List<VenteParJourDTO> getVentesParSemaine(LocalDateTime startDate, LocalDateTime endDate) {
        return ligneCommandeRepository.findVentesParSemaine(startDate, endDate);
    }*/
}
