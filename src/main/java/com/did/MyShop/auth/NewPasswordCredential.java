package com.did.MyShop.auth;

import jakarta.validation.constraints.NotBlank;

public record NewPasswordCredential(
        @NotBlank(message = "Le mot de passe est obligatoire.")
        String password,

         @NotBlank(message = "L jeton est obligatoire.")
         String jeton

) {
}
