package com.did.MyShop.DTO.livraison;

import com.did.MyShop.DTO.produit.ProduitResponseMini;
import com.did.MyShop.entities.Ravitaillement.Ravitaillement;

public record LigneRavitaillementResponse(
        Long id,
        Double quantite,
        RavitaillementResponse ravitaillement,
        ProduitResponseMini produit,
        Double prixUnitaire
) {
}
