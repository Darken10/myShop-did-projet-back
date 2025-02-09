package com.did.MyShop.repositories.commande;

import com.did.MyShop.entities.Commande.Commande;
import com.did.MyShop.entities.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.did.MyShop.DTO.statistique.VenteParJourDTO;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

    @Query("SELECT c FROM Commande c order by c.createAt DESC ")
    List<Commande> findAllInverse();

    @Query("SELECT lc.produit.id, SUM(lc.prixUnitaire * lc.quantity) " +
            "FROM LigneCommande lc " +
            "GROUP BY lc.produit.id")
    List<Object[]> findTotalVentesParProduit();

    @Query("SELECT lc.produit.id, SUM(lc.quantity) " +
            "FROM LigneCommande lc " +
            "GROUP BY lc.produit.id")
    List<Object[]> findQuantiteVendueParProduit();

    List<Commande> findAllByUser(User user);

    /*@Query("SELECT SUM(c.ligneCommandes.size) FROM Commande c")
    Double findTotalVentes();*/

    @Query("SELECT SUM(l.prixUnitaire * l.quantity) FROM LigneCommande l")
    Double findTotalAchats();

    @Query("SELECT c FROM Commande c WHERE c.user.id = :userId")
    List<Commande> findCommandesParCaissier(@Param("userId") Long userId);

    @Query("SELECT c FROM Commande c WHERE c.createAt BETWEEN :startDate AND :endDate")
    List<Commande> findVentesParPeriode(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT c FROM Commande c WHERE c.status = 'ANNULEE' AND c.paiements IS NOT EMPTY")
    List<Commande> findAnnuleesApresPaiement();

    @Query("SELECT c FROM Commande c WHERE c.client.id = :clientId")
    List<Commande> findCommandesByClient(@Param("clientId") Long clientId);


    @Query("SELECT FUNCTION('DATE', c.createAt) AS jour, COUNT(c) AS nombreVentes " +
            "FROM Commande c " +
            "WHERE c.createAt BETWEEN :startDate AND :endDate " +
            "GROUP BY FUNCTION('DATE', c.createAt) " +
            "ORDER BY FUNCTION('DATE', c.createAt) ASC")
    List<Object[]> findNombreVentesParJour(@Param("startDate") LocalDateTime startDate,
                                           @Param("endDate") LocalDateTime endDate);


    @Query("SELECT SUM(lc.prixUnitaire * lc.quantity) " +
            "FROM LigneCommande lc " +
            "JOIN lc.commande c " +
            "WHERE FUNCTION('YEARWEEK', c.createAt) = FUNCTION('YEARWEEK', :date)")
    Double findChiffreAffaireParSemaine(@Param("date") LocalDate date);

    @Query("SELECT DATE(c.createAt) AS jour, SUM(lc.prixUnitaire * lc.quantity) AS chiffreAffaire " +
            "FROM LigneCommande lc " +
            "JOIN lc.commande c " +
            "WHERE c.createAt BETWEEN :startDate AND :endDate " +
            "AND c.user.id = :userId " +
            "GROUP BY DATE(c.createAt) " +
            "ORDER BY DATE(c.createAt)")
    List<Object[]> findChiffreAffaireParJourSemaine(@Param("userId") Long userId,
                                                    @Param("startDate") LocalDateTime startDate,
                                                    @Param("endDate") LocalDateTime endDate);

    @Query("SELECT DATE(c.createAt) AS jour, SUM(lc.prixUnitaire * lc.quantity) AS chiffreAffaire " +
            "FROM LigneCommande lc " +
            "JOIN lc.commande c " +
            "WHERE c.createAt BETWEEN :startDate AND :endDate " +
            "GROUP BY DATE(c.createAt) " +
            "ORDER BY DATE(c.createAt)")
    List<Object[]> findChiffreAffaireParJourSemainePourTous(@Param("startDate") LocalDateTime startDate,
                                                            @Param("endDate") LocalDateTime endDate);

}
