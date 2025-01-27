package com.did.MyShop.repositories.users;

import com.did.MyShop.entities.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select p from User p order by p.firstName limit 1")
    Optional<User> findUser();

    Optional<User> findByMatricule(String matricule);

    Optional<User> findUserByEmailAndMatricule(String email, String matricule);

    Optional<User>  findUserByMatriculeOrEmailOrPhoneNumber(String matricule, String email, String phoneNumber);
}
