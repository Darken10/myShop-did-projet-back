package com.did.MyShop.mappers.user;


import com.did.MyShop.DTO.user.UserRequest;
import com.did.MyShop.DTO.user.UserResponse;
import com.did.MyShop.entities.User.User;

import java.util.stream.Collectors;

public class UserMapper {

    public static User toUser(UserRequest userRequest) {
        return User
                .builder()
                .firstName(userRequest.firstName())
                .lastName(userRequest.lastName())
                .genre(userRequest.genre())
                .dateNaissance(userRequest.dateNaissance())
                .email(userRequest.email())
                .phoneNumber(String.valueOf(userRequest.phoneNumber()))
                .matricule(userRequest.matricule())
                .build();
    }

    public static UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getGenre(),
                user.getDateNaissance(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getMatricule(),
                user.getStatus(),
                user.getRoles().stream().map(RoleMapper::toRoleResponse).collect(Collectors.toList())
        );
    }
}
