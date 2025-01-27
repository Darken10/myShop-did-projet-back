package com.did.MyShop.DTO.produit;

import com.did.MyShop.entities.Commande.Promotion;
import com.did.MyShop.entities.Ravitaillement.LigneRavitaillement;
import com.did.MyShop.enums.UniteProduitEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.List;
import java.util.Set;

public record ProduitResponse(
        Long id,
        String libelle,
        String description,
        Double prix,
        Integer stock,
        String image,
        Double seuil,
        UniteProduitEnum unite,
        CategorieResponse category,
        Set<TagResponse> tags,
        Set<Promotion> promotions,
        List<LigneRavitaillement> ligneRavitaillements
) {
}
