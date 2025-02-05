package com.did.MyShop.DTO.commande;

import com.did.MyShop.DTO.produit.ProduitResponseMini;

public record LigneCommandeMini(
        Long id,
        Double prixUnitaire,
        Double quantity,
        PromotionResponse promotion,
        ProduitResponseMini produit
) {
}
