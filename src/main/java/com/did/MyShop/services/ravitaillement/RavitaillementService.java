package com.did.MyShop.services.ravitaillement;

import com.did.MyShop.DTO.livraison.LigneRavitaillementRequest;
import com.did.MyShop.DTO.livraison.RavitaillementResquest;
import com.did.MyShop.Exceptions.RessourceNotFoundException;
import com.did.MyShop.entities.Produit.Produit;
import com.did.MyShop.entities.Ravitaillement.Fournisseur;
import com.did.MyShop.entities.Ravitaillement.LigneRavitaillement;
import com.did.MyShop.entities.Ravitaillement.Ravitaillement;
import com.did.MyShop.entities.User.User;
import com.did.MyShop.enums.StatusRavitaillement;
import com.did.MyShop.mappers.Raviatillement.LigneRavitaillementMapper;
import com.did.MyShop.mappers.Raviatillement.RavitaillementMapper;
import com.did.MyShop.repositories.produit.ProduitRepository;
import com.did.MyShop.repositories.ravitaillement.FournisseurRepository;
import com.did.MyShop.repositories.ravitaillement.LigneRavitaillementRepository;
import com.did.MyShop.repositories.ravitaillement.RavitaillementRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class RavitaillementService {

    private final RavitaillementRepository ravitaillementRepository;
    private final FournisseurRepository fournisseurRepository;
    private final LigneRavitaillementService ligneRavitaillementService;
    private final LigneRavitaillementRepository ligneRavitaillementRepository;
    private final ProduitRepository produitRepository;

    public List<Ravitaillement> findAll() {
        return ravitaillementRepository.findAll();
    }

    public Ravitaillement findById(Long id) {
        return ravitaillementRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("Ravitaillement n°"+ id+" introuvable"));
    }

    public Ravitaillement save(RavitaillementResquest ravitaillementResquest, Principal principal) {
        Ravitaillement ravitaillement = RavitaillementMapper.toRavitaillement(ravitaillementResquest);
        ravitaillement.setFournisseur(getFounisseur(ravitaillementResquest.fournisseurId()));
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        ravitaillement.setUser(user);
        ravitaillement.setCreateDate(LocalDateTime.now());
        ravitaillement.setStatus(StatusRavitaillement.NEW);
        return ravitaillementRepository.save(ravitaillement);

    }

    public Ravitaillement addLigneRavitaillement(Long ravitailleId, LigneRavitaillementRequest request) {
        Ravitaillement ravitaillement = getRavitaillement(ravitailleId);
        LigneRavitaillement lg = LigneRavitaillement
                .builder()
                .quantite(request.quantite())
                .produit(getProduit(request.produitId()))
                .ravitaillement(ravitaillement)
                .prixUnitaire(request.prixUnitaire())
                .build();
        ligneRavitaillementRepository.save(lg);
        return ravitaillement;
    }

    private Fournisseur getFounisseur(Long id){
        return fournisseurRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("Founisseur n°"+ id+" introuvable"));
    }

    private Ravitaillement getRavitaillement(Long id){
        return ravitaillementRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("Ravitaillement n°"+ id+" introuvable"));
    }

    private Produit getProduit(Long id){
        return produitRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("Produit n°"+ id+" introuvable"));
    }
}
