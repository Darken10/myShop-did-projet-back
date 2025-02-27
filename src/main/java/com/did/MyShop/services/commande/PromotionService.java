package com.did.MyShop.services.commande;

import com.did.MyShop.DTO.commande.PromotionRequest;
import com.did.MyShop.Exceptions.RessourceNotFoundException;
import com.did.MyShop.entities.Commande.Promotion;
import com.did.MyShop.entities.Produit.Produit;
import com.did.MyShop.mappers.Commande.PromotionMapper;
import com.did.MyShop.repositories.commande.PromotionRepository;
import com.did.MyShop.repositories.produit.ProduitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PromotionService {

    private PromotionRepository promotionRepository;
    private ProduitRepository produitRepository;

    public List<Promotion> findAll(){
        return promotionRepository.findAll();
    }

    public Promotion findById(long promId){
        return promotionRepository.findById(promId).orElseThrow(()->new RessourceNotFoundException("Promotion not found"));
    }


    public Promotion save(PromotionRequest request){
        System.out.println(request);
        Promotion promotion = PromotionMapper.toPromotion(request);
        Set<Produit> produits = request.produitsId().stream().map(this::getProduit).collect(Collectors.toSet());
        promotion.setProduits(produits);
        promotion.setCreateDate(LocalDateTime.now());
        request.produitsId()
                .forEach(proId -> promotion.addProduit(produitRepository.findById(proId).orElseThrow(() -> new RessourceNotFoundException("Le produit n°"+proId+" associer a cet identifiant"))));
        System.out.println(promotion.getProduits().stream().map(Produit::getLibelle).collect(Collectors.toSet()));
        return promotionRepository.save(promotion);
    }

    private Produit getProduit(long prodId){
        return produitRepository.findById(prodId).orElseThrow(()->new RessourceNotFoundException("Produit not found"));
    }


    public void retirer(long promId,long prodId){
        Promotion promotion = findById(promId);
        Produit produit = getProduit(prodId);
        if(!promotion.getProduits().contains(produit)){

        promotion.removeProduit(produit);
        }
        promotionRepository.save(promotion);
    }
}
