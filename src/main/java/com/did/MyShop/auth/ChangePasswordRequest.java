package com.did.MyShop.auth;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChangePasswordRequest(
        @NotBlank(message = "Le mot de passe actuel est obligatoire.")
        String currentPassword,

        @NotBlank(message = "Le nouveau mot de passe est obligatoire.")
        @Size(min = 4, max = 20, message = "Le mot de passe doit contenir entre 8 et 20 caractères.")
        String newPassword,

        @NotBlank(message = "La confirmation du mot de passe est obligatoire.")
        String confirmationPassword
        // TODO: Je pense que cest mieux de prendre que le currentPwd et le newPwd seulment. ca ne sert a absolument rien de prendre le comfirmation. cela se fait juste cote front (angular)
) {
}

