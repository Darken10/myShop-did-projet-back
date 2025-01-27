package com.did.MyShop.DTO.commande;


import com.did.MyShop.DTO.produit.ProduitResponse;

public record LigneCommandeResponse(
        Long id,
         Double prixUnitaire,
         Double quantity,
        ProduitResponse produit
) {
}
