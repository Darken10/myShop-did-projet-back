package com.did.MyShop.mappers.Commande;

import com.did.MyShop.DTO.commande.ClientRequest;
import com.did.MyShop.DTO.commande.ClientResponse;
import com.did.MyShop.entities.Commande.Client;

public class ClientMapper {
    public static Client toClient (ClientRequest request) {
        return Client.builder()
                .name(request.name())
                .phone(request.phone())
                .solde(request.solde())
                .adress(request.adress())
                .build();

    };

    public static ClientResponse toClientResponse (Client client) {
        return new ClientResponse(
                client.getId(),
                client.getName(),
                client.getPhone(),
                client.getSolde(),
                client.getAdress()
                );
    }
}
