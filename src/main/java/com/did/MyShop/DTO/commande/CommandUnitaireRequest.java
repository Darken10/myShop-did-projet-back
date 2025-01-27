package com.did.MyShop.DTO.commande;

public record CommandUnitaireRequest(
        Double prixUnitaire,
        Double quantity,
        Long productId,
        Long promotionId
) {
}
