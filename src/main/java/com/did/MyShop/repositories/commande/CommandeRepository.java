package com.did.MyShop.repositories.commande;

import com.did.MyShop.entities.Commande.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
}
