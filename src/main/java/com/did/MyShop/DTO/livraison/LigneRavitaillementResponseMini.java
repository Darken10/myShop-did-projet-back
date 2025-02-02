package com.did.MyShop.DTO.livraison;

import com.did.MyShop.DTO.produit.ProduitResponseMini;

public record LigneRavitaillementResponseMini(
        Long id,
        Double quantite,
        ProduitResponseMini produit,
        Double prixUnitaire
) {
}
