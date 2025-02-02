package com.did.MyShop.mappers.Commande;

import com.did.MyShop.DTO.commande.FullPromotion;
import com.did.MyShop.DTO.commande.PromotionRequest;
import com.did.MyShop.DTO.commande.PromotionResponse;
import com.did.MyShop.entities.Commande.Promotion;
import com.did.MyShop.mappers.prodiut.ProduitMapper;

import java.util.HashSet;
import java.util.stream.Collectors;

public class PromotionMapper {

    public static Promotion toPromotion (PromotionRequest request) {

        return Promotion.builder()
                .name(request.name())
                .description(request.description())
                .reduction(request.reduction())
                .isPercent(request.isPercent())
                .endDate(request.endDate())
                .startDate(request.startDate())
                .produits(new HashSet<>())
                .build();

    };

    public static PromotionResponse toPromotionResponse (Promotion promotion) {
        return new PromotionResponse(
                promotion.getId(),
                promotion.getName(),
                promotion.getDescription(),
                promotion.getReduction(),
                promotion.getIsPercent(),
                promotion.getCreateDate(),
                promotion.getEndDate(),
                promotion.getStartDate()
        );
    }

    public static FullPromotion toFullPromotionResponse (Promotion promotion) {
        return new FullPromotion(
                promotion.getId(),
                promotion.getName(),
                promotion.getDescription(),
                promotion.getReduction(),
                promotion.getIsPercent(),
                promotion.getCreateDate(),
                promotion.getEndDate(),
                promotion.getStartDate(),
                promotion.getProduits().stream().map((ProduitMapper::toProduitResponseMini)).collect(Collectors.toSet())
        );
    }


}
