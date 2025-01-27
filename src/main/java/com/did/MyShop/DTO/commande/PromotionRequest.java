package com.did.MyShop.DTO.commande;

import java.time.LocalDateTime;
import java.util.Set;

public record PromotionRequest(
        String name,
        String description,
        Double reduction,
        Boolean isPercent,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime createDate,
        Set<Long> produitsId
) {
}
