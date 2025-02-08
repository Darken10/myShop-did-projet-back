package com.did.MyShop.repositories.commande;

import com.did.MyShop.entities.Commande.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    @Query("SELECT p FROM Promotion p WHERE p.startDate <= CURRENT_DATE AND p.endDate >= CURRENT_DATE")
    List<Promotion> findActives();

    @Query("SELECT p FROM Promotion p WHERE p.startDate <= :date AND p.endDate >= :date")
    List<Promotion> findPromotionsValides(@Param("date") LocalDateTime date);
}
