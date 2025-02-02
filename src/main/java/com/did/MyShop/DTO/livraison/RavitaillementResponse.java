package com.did.MyShop.DTO.livraison;

import com.did.MyShop.enums.StatusRavitaillement;

import java.time.LocalDateTime;
import java.util.List;

public record RavitaillementResponse(
        Long id,
        LocalDateTime createDate,
        LocalDateTime deliveredDate,
        StatusRavitaillement status,
        FounisseurResponseMini founisseur,
        List<LigneRavitaillementResponseMini> ligneRavitaillements
) {
}
