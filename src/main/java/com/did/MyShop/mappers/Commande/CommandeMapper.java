package com.did.MyShop.mappers.Commande;

import com.did.MyShop.DTO.commande.CommandUnitaireRequest;
import com.did.MyShop.DTO.commande.CommandeRequest;
import com.did.MyShop.DTO.commande.CommandeResponse;
import com.did.MyShop.DTO.commande.PaiementResponse;
import com.did.MyShop.entities.Commande.Client;
import com.did.MyShop.entities.Commande.Commande;
import com.did.MyShop.entities.Commande.LigneCommande;
import com.did.MyShop.entities.User.User;
import com.did.MyShop.enums.StatusCommandEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CommandeMapper {

    public static Commande toCommande (CommandeRequest request) {

        return Commande.builder()
                .client(getClient(request.clientId()))
                .user(new User())
                .status(StatusCommandEnum.NEW)
                .description(request.description())/*
                .ligneCommandes(request.ligneCommandes().stream().map((id)->getLigneCommande(i)).toList())*/
                .build();

    };



    public static CommandeResponse toCommandeResponse (Commande commande) {
        List<PaiementResponse> payement = new ArrayList<>();
        if (commande.getPaiements()!=null && !commande.getPaiements().isEmpty()) {
             payement = commande.getPaiements().stream().map((PaiementMapper::toPaiementResponse)).toList();
        }
        return new CommandeResponse(
                commande.getId(),
                ClientMapper.toClientResponse(commande.getClient()),
                commande.getDescription(),
                commande.getStatus(),
                payement,
                commande.getCreateAt()
        );
    }


    private static Client getClient(Long clientId) {
        Client client = new Client();
        client.setId(clientId);
        return client;
    }

    private static LigneCommande getLigneCommande(Long ligneId) {
        LigneCommande ligne = new LigneCommande();
        ligne.setId(ligneId);
        return ligne;
    }

}
