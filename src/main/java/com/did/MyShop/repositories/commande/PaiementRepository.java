package com.did.MyShop.repositories.commande;

import com.did.MyShop.entities.Commande.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
}
