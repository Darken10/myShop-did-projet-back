package com.did.MyShop.DTO.commande;


public record ClientRequest(
        String name,
        String phone,
        Double solde,
        String adress
) {
}
