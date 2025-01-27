package com.did.MyShop.mappers.Commande;


import com.did.MyShop.DTO.commande.LigneCommandeRequest;
import com.did.MyShop.DTO.commande.LigneCommandeResponse;
import com.did.MyShop.entities.Commande.LigneCommande;
import com.did.MyShop.entities.Commande.Promotion;
import com.did.MyShop.entities.Produit.Produit;
import com.did.MyShop.mappers.prodiut.ProduitMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LigneCommandeMapper {

    public static LigneCommande toLigneCommande (LigneCommandeRequest request) {

        return LigneCommande.builder()
                .prixUnitaire(request.prixUnitaire())
                .quantity(request.quantity())
                .promotion(getPromotion(request.promotionsId()))
                .produit(getProduit(request.produitId()))
                .build();

    }

    public static LigneCommandeResponse toLigneCommandeResponse (LigneCommande ligneCommande) {

        log.info("LigneCommandeMapper.toLigneCommandeResponse() : {}", ligneCommande.getProduit().getDescription());
        return new LigneCommandeResponse(
                ligneCommande.getId(),
                ligneCommande.getPrixUnitaire(),
                ligneCommande.getQuantity(),
                ProduitMapper.toProduitResponse(ligneCommande.getProduit())
        );
    }


    private static Promotion getPromotion (Long promotionId) {
        Promotion promotion = new Promotion();
        promotion.setId(promotionId);
        return promotion;
    }

    private static Produit getProduit (Long id) {
        Produit produit = new Produit();
        produit.setId(id);
        return produit;
    }


}
