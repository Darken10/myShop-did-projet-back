package com.did.MyShop.DTO.commande;

import com.did.MyShop.enums.StatusCommandEnum;

import java.time.LocalDateTime;
import java.util.List;

public record CommandeResponse(
        Long id,
        ClientResponse client,
        String description,
        StatusCommandEnum status,
        List<PaiementResponse> paiements,
        LocalDateTime createAt
) {
}
