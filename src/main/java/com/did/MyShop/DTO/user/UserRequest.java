package com.did.MyShop.DTO.user;

import com.did.MyShop.enums.GenreUserEnum;
import com.did.MyShop.enums.StatusUserEnum;

import java.util.List;

public record UserRequest(
        String firstName,
        String lastName,
        GenreUserEnum genre,
        String dateNaissance,
        String email,
        String phoneNumber,
        String matricule,
        StatusUserEnum status,
        List<Long> rolesId
) {
}
