package com.did.MyShop.repositories.users;

import com.did.MyShop.entities.User.Role;
import com.did.MyShop.entities.User.User;
import com.did.MyShop.enums.StatusUserEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select p from User p order by p.firstName limit 1")
    Optional<User> findUser();

    Optional<User> findByMatricule(String matricule);

    Optional<User> findUserByEmailAndMatricule(String email, String matricule);

    Optional<User>  findUserByMatriculeOrEmailOrPhoneNumber(String matricule, String email, String phoneNumber);

    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.libelle = :role")
    List<User> findUsersByRole(@Param("role") String role);

    Double countByStatus(StatusUserEnum status);

    Double countByRolesContaining(Role role);
}
