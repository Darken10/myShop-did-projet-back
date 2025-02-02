package com.did.MyShop.DTO.livraison;

import java.util.List;

public record FounisseurResponseMini(
        Long id,
        String name,
        String address,
        String phoneNumber,
        String email
) {
}
