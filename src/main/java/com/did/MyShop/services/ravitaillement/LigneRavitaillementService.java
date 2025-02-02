package com.did.MyShop.services.ravitaillement;

import com.did.MyShop.DTO.livraison.LigneRavitaillementRequest;
import com.did.MyShop.Exceptions.RessourceNotFoundException;
import com.did.MyShop.entities.Produit.Produit;
import com.did.MyShop.entities.Ravitaillement.LigneRavitaillement;
import com.did.MyShop.entities.Ravitaillement.Ravitaillement;
import com.did.MyShop.mappers.Raviatillement.LigneRavitaillementMapper;
import com.did.MyShop.repositories.produit.ProduitRepository;
import com.did.MyShop.repositories.ravitaillement.LigneRavitaillementRepository;
import com.did.MyShop.repositories.ravitaillement.RavitaillementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LigneRavitaillementService {
    private final LigneRavitaillementRepository ligneRavitaillementRepository;
    private final RavitaillementRepository ravitaillementRepository;
    private final ProduitRepository produitRepository;

    public List<LigneRavitaillement> findAll() {
        return ligneRavitaillementRepository.findAll();
    }

    public LigneRavitaillement findById(Long id) {
        return ligneRavitaillementRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("Ligne Ravitaillement n°"+ id+" introuvable"));
    }

    public LigneRavitaillement save(Long ravitaillementId,LigneRavitaillementRequest ligneRavitaillementRequest) {
        LigneRavitaillement ligneRavitaillement = LigneRavitaillementMapper.toLigneRavitaillement(ligneRavitaillementRequest);
        ligneRavitaillement.setProduit(getProduit(ligneRavitaillementRequest.produitId()));
        ligneRavitaillement.setRavitaillement(getRavitaillement(ravitaillementId));
        return ligneRavitaillementRepository.save(ligneRavitaillement);
    }


    private Ravitaillement getRavitaillement(Long id) {
        return ravitaillementRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("Ravitaillement n°"+ id+" introuvable"));
    }

    private Produit getProduit(Long id) {
        return produitRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("Prosuit n°"+ id+" introuvable"));
    }



}
