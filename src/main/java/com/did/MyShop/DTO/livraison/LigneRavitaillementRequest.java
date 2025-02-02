package com.did.MyShop.DTO.livraison;

public record LigneRavitaillementRequest( // le ravitaillement doit etre passer en parametre de l'url
        Double quantite,
        Long produitId,
        Double prixUnitaire
) {
}
