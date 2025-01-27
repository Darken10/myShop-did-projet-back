package com.did.MyShop.repositories.produit;

import com.did.MyShop.entities.Produit.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
