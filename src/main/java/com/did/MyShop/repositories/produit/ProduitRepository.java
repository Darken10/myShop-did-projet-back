package com.did.MyShop.repositories.produit;

import com.did.MyShop.entities.Produit.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit,Long> {
}
