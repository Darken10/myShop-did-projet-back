package com.did.MyShop.DTO.livraison;

import com.did.MyShop.enums.StatusRavitaillement;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RavitaillementResquest(
        LocalDateTime createDate,
        LocalDateTime deliveredDate,
        StatusRavitaillement status,
        @NotNull(message = "Le fournisseur est obligatoire")
        Long fournisseurId,
        String description
) {
}
