package com.did.MyShop.DTO.commande;

public record ClientResponse(
        Long id,
        String name,
        String phone,
        Double solde,
        String adress
) {
}
