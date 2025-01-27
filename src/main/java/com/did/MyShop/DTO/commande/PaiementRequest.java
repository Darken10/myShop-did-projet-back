package com.did.MyShop.DTO.commande;

import com.did.MyShop.enums.MethodePaiementEnum;
import com.did.MyShop.enums.StatusPaimentEnum;

import java.time.LocalDateTime;

public record PaiementRequest(
        MethodePaiementEnum methode,
        String reference,
        Double amount,
        LocalDateTime date,
        StatusPaimentEnum status,
        String comment,
        Long commandeId
) {
}
