package com.did.MyShop.mappers.Commande;


import com.did.MyShop.DTO.commande.PaiementRequest;
import com.did.MyShop.DTO.commande.PaiementResponse;
import com.did.MyShop.entities.Commande.Commande;
import com.did.MyShop.entities.Commande.Paiement;

public class PaiementMapper {
    public static Paiement toPaiement (PaiementRequest request) {

        return Paiement.builder()
                .methode(request.methode())
                .reference(request.reference())
                .amount(request.amount())
                .date(request.date())
                .status(request.status())
                .comment(request.comment())
                .commande(getNewCommand(request.commandeId()))
                .build();

    };

    public static PaiementResponse toPaiementResponse (Paiement paiement) {
        return new PaiementResponse(
                paiement.getId(),
                paiement.getMethode(),
                paiement.getReference(),
                paiement.getAmount(),
                paiement.getDate(),
                paiement.getStatus(),
                paiement.getComment()
        );
    }

    private static Commande getNewCommand (Long id) {
        return Commande.builder().id(id).build();

    }


}
