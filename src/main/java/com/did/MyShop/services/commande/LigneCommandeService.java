package com.did.MyShop.services.commande;



import com.did.MyShop.DTO.commande.LigneCommandeRequest;
import com.did.MyShop.Exceptions.RessourceNotFoundException;
import com.did.MyShop.entities.Commande.Client;
import com.did.MyShop.entities.Commande.Commande;
import com.did.MyShop.entities.Commande.LigneCommande;
import com.did.MyShop.entities.Commande.Promotion;
import com.did.MyShop.entities.Produit.Produit;
import com.did.MyShop.mappers.Commande.LigneCommandeMapper;
import com.did.MyShop.repositories.commande.ClientRepository;
import com.did.MyShop.repositories.commande.CommandeRepository;
import com.did.MyShop.repositories.commande.LigneCommandeRepository;
import com.did.MyShop.repositories.commande.PromotionRepository;
import com.did.MyShop.repositories.produit.ProduitRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.did.MyShop.DTO.commande.LigneCommandeResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LigneCommandeService {

    private final CommandeRepository commandeRepository;
    private final ProduitRepository produitRepository;
    private final ClientRepository clientRepository;
    private final PromotionRepository promotionRepository;
    LigneCommandeRepository ligneCommandeRepository;

    public List<LigneCommandeResponse> findAll(){
        return ligneCommandeRepository.findAll().stream().map(LigneCommandeMapper::toLigneCommandeResponse).collect(Collectors.toList());
    }

    public LigneCommandeResponse findById(Long id){
        return LigneCommandeMapper.toLigneCommandeResponse(getLigneCommande(id));
    }


    public LigneCommandeResponse create(LigneCommandeRequest commandeRequest){
        LigneCommande ligneCommande = LigneCommandeMapper.toLigneCommande(commandeRequest);
        ligneCommandeRepository.save(ligneCommande);
        return LigneCommandeMapper.toLigneCommandeResponse(ligneCommande);
    }

    @Transactional
    public LigneCommande create(LigneCommandeRequest commandeRequest,Long commandeId){
        LigneCommande ligneCommande = LigneCommandeMapper.toLigneCommande(commandeRequest);
        Produit produit = getProduit(commandeRequest.produitId());
        ligneCommande.setCommande(getCommande(commandeId));
        ligneCommande.setProduit(produit);
        if (commandeRequest.promotionsId()!= null){
            ligneCommande.setPromotion(this.getPromotion(commandeRequest.promotionsId()));
        } else {
            ligneCommande.setPromotion(Promotion.builder().id(1L).isPercent(true).reduction(0D).build());
        }
        ligneCommandeRepository.save(ligneCommande);
        produit.setStock((int) (produit.getStock() - ligneCommande.getQuantity()));
        produitRepository.save(produit);
        return ligneCommande;
    }




    public LigneCommandeResponse update(Long id,LigneCommandeRequest ligneCommandeRequest){
        LigneCommande ligneCommande = getLigneCommande(id);
        ligneCommande.setId(id);
        ligneCommande.setPrixUnitaire(ligneCommandeRequest.prixUnitaire());
        ligneCommande = ligneCommandeRepository.save(ligneCommande);
        ligneCommande = ligneCommandeRepository.findById(ligneCommande.getId()).orElseThrow(()->new RessourceNotFoundException("la ligne commande non trouve"));
        return LigneCommandeMapper.toLigneCommandeResponse(ligneCommande);
    }

    public void delete(Long id){
        ligneCommandeRepository.delete(getLigneCommande(id));
    }

    private LigneCommande getLigneCommande(Long id){
        return ligneCommandeRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("ligne Commande n°"+id +" non trouve"));
    }

    private Commande getCommande(Long id){
        return commandeRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("Commande n°"+id +" non trouve"));
    }

    private Produit getProduit(Long id){
        return produitRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("Produit n°"+id +" non trouve"));
    }

    private Promotion getPromotion(Long id){
        return promotionRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("Produit n°"+id +" non trouve"));
    }





}
