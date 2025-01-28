package com.did.MyShop.DTO.produit;

import com.did.MyShop.enums.UniteProduitEnum;


public record ProduitResponseMini(
        Long id,
        String libelle,
        String description,
        Double prix,
        Integer stock,
        UniteProduitEnum unite
) {
}
