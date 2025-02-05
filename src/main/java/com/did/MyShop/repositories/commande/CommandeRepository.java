package com.did.MyShop.repositories.commande;

import com.did.MyShop.entities.Commande.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.did.MyShop.DTO.statistique.VenteParJourDTO;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

    @Query("SELECT lc.produit.id, SUM(lc.prixUnitaire * lc.quantity) " +
            "FROM LigneCommande lc " +
            "GROUP BY lc.produit.id")
    List<Object[]> findTotalVentesParProduit();

    @Query("SELECT lc.produit.id, SUM(lc.quantity) " +
            "FROM LigneCommande lc " +
            "GROUP BY lc.produit.id")
    List<Object[]> findQuantiteVendueParProduit();

     /*@Query("SELECT new com.did.MyShop.DTO.statistique.VenteParJourDTO" +
            "(lc.commande.createAt, SUM(lc.prixUnitaire * lc.quantity)) " +
            "FROM LigneCommande lc " +
            "WHERE lc.commande.createAt BETWEEN :startDate AND :endDate " +
            "GROUP BY lc.commande.createAt")
    List<VenteParJourDTO> findVentesParSemaine(@Param("startDate") LocalDateTime startDate,
                                               @Param("endDate") LocalDateTime endDate);*/

}
