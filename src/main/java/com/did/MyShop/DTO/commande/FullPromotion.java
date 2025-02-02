package com.did.MyShop.DTO.commande;

import com.did.MyShop.DTO.produit.ProduitResponseMini;

import java.time.LocalDateTime;
import java.util.Set;

public record FullPromotion(
        Long id,
        String name,
        String description,
        Double reduction,
        Boolean isPercent,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime createDate,
        Set<ProduitResponseMini> produits
) {
}
