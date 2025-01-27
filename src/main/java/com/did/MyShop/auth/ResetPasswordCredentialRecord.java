package com.did.MyShop.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ResetPasswordCredentialRecord(

        @NotBlank(message = "L'email est obligatoire.")
        @Email(message = "L'email doit être valide.")
        String email,

        @NotBlank(message = "Le matricule est obligatoire.")
        @Pattern(regexp = "[A-Za-z0-9]{6,}", message = "Le matricule doit être alphanumérique et contenir au moins 6 caractères.")
        String matricule,

        @NotBlank(message = "L'url est obligatoire.")
        String url // en entre http://localhost:4200/reset-pwd et en sortie le lien sera donc http://localhost:4200/reset-pwd?jeton=azerty...azerty
) {
}
