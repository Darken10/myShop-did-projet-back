package com.did.MyShop.auth;


import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequest(
        @NotBlank(message = "Le matricule est obligatoire.")
        String matricule,

        @NotBlank(message = "Le mot de passe est obligatoire.")
        String password
) {
}

