package com.did.MyShop.repositories.users;


import com.did.MyShop.entities.User.ResetPasswordJeton;
import com.did.MyShop.entities.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ResetPasswordJetonRepository extends JpaRepository<ResetPasswordJeton, Long> {
    Optional<ResetPasswordJeton> findByJeton(UUID token);
}
