package com.did.MyShop.repositories.commande;

import com.did.MyShop.entities.Commande.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
}
