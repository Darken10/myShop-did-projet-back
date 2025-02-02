package com.did.MyShop.DTO.livraison;


import java.util.List;

public record FournisseurResponse(
         Long id,
         String name,
         String address,
         String phoneNumber,
         String email,
         String description,
         List<RavitaillementResponse> ravitaillements
) {
}
