package com.did.MyShop.DTO.commande;



import com.did.MyShop.enums.StatusCommandEnum;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateNewCommandeCredentiale(
        @NotNull(message = "Un client doit etre fournie")
         Long clientId,
         String description,
         StatusCommandEnum status,
        List<CommandUnitaireRequest> productIdList
) {
}
