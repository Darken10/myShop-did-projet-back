package com.did.MyShop.services.commande;

import com.did.MyShop.DTO.commande.PaiementRequest;
import com.did.MyShop.Exceptions.RessourceNotFoundException;
import com.did.MyShop.entities.Commande.Commande;
import com.did.MyShop.entities.Commande.Paiement;
import com.did.MyShop.mappers.Commande.PaiementMapper;
import com.did.MyShop.repositories.commande.CommandeRepository;
import com.did.MyShop.repositories.commande.PaiementRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
@AllArgsConstructor
public class PaiementService {
    private final CommandeRepository commandeRepository;
    private PaiementRepository paiementRepository;

    public List<Paiement> findAll(){
        return paiementRepository.findAll();
    }

    public Paiement findById(long id){
        return paiementRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("Paiement not found"));
    }

    @Transactional
    public Paiement save(PaiementRequest paiementRequest){
        Paiement paiement = PaiementMapper.toPaiement(paiementRequest);
        Commande commande = getCommande(paiementRequest.commandeId());
        AtomicReference<Double> amountDejaPayer = new AtomicReference<>(0D);
        commande.getPaiements().forEach(paie -> {
            amountDejaPayer.set(amountDejaPayer.get() + (double) paie.getAmount());
        });
        System.out.println("===========================================================");
        log.info("Valeur deja payer : {}", amountDejaPayer.get());
        paiement.setCommande(commande);
        return paiementRepository.save(paiement);
    }

    private Commande getCommande(long id){
        return commandeRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("Commande n°"+id+" non trouve"));
    }



}
