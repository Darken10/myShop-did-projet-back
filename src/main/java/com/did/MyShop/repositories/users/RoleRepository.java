package com.did.MyShop.repositories.users;

import com.did.MyShop.entities.User.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
