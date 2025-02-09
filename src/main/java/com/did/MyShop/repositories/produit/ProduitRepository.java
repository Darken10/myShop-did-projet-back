package com.did.MyShop.repositories.produit;

import com.did.MyShop.entities.Produit.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit,Long> {

    @Query("SELECT p FROM Produit p WHERE p.stock <= p.seuil")
    List<Produit> findByStockInferieurSeuil();

    @Query("SELECT p FROM Produit p WHERE p.stock = 0")
    List<Produit> findByStockInferieurOuEgalA(@Param("stock") int stock);

    @Query("SELECT p FROM Produit p WHERE p.id IN (SELECT lp.produit.id FROM LigneCommande lp WHERE lp.commande.status = 'VALIDEE')")
    List<Produit> findProduitsVendus();

    // Récupère tous les produits dont le stock est inférieur au seuil d'alerte
    @Query("SELECT p FROM Produit p WHERE p.stock < p.seuil")
    List<Produit> findProduitsEnCarence();

    @Query("SELECT p FROM Produit p WHERE p.stock <= 0")
    List<Produit> countProduitNoStock();

    @Query("SELECT p FROM Produit p WHERE p.stock >= p.seuil")
    List<Produit> countProduitStock();



}
