package com.did.MyShop.DTO.livraison;

public record FournisseurRequest(
        String name,
        String address,
        String phoneNumber,
        String email,
        String description
) {
}
