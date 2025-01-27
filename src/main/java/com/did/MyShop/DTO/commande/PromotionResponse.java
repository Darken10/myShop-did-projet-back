package com.did.MyShop.DTO.commande;

import com.did.MyShop.entities.Produit.Produit;
import java.time.LocalDateTime;
import java.util.Set;

public record PromotionResponse(
         Long id,
         String name,
         String description,
         Double reduction,
         Boolean isPercent,
         LocalDateTime startDate,
         LocalDateTime endDate,
         LocalDateTime createDate

) {
}
