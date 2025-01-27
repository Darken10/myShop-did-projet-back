package com.did.MyShop.auth;

import com.did.MyShop.DTO.user.UserResponse;
import lombok.NonNull;

public record AuthenticationResponse(
        @NonNull String token,
        @NonNull String refreshToken,
        UserResponse user
) {
}
