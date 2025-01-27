package com.did.MyShop.mappers.user;

import com.did.MyShop.DTO.user.RoleRequest;
import com.did.MyShop.DTO.user.RoleResponse;
import com.did.MyShop.entities.User.Role;

public class RoleMapper {

    public static Role toRole(RoleRequest roleRequest) {
        return Role
                .builder()
                .libelle(roleRequest.libelle())
                .description(roleRequest.description())
                .build();
    }

    public static RoleResponse toRoleResponse(Role role) {
        return new RoleResponse(
                role.getId(),
                role.getLibelle(),
                role.getDescription()
        );
    }
}
