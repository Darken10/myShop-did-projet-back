package com.did.MyShop.repositories.commande;

import com.did.MyShop.entities.Commande.Commande;
import com.did.MyShop.entities.Commande.Paiement;
import com.did.MyShop.entities.User.User;
import com.did.MyShop.enums.MethodePaiementEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {

    @Query("SELECT SUM(p.amount) FROM Paiement p WHERE p.commande.user.id = :userId")
    Double findPaiementsParCaissier(@Param("userId") Long userId);

    @Query("SELECT p.methode, SUM(p.amount) FROM Paiement p GROUP BY p.methode")
    Map<MethodePaiementEnum, Double> findPaiementsGroupesParMethode();

    @Query("SELECT FUNCTION('DATE', p.date) AS jour, SUM(p.amount) AS totalPaiement " +
            "FROM Paiement p " +
            "JOIN p.commande c " +
            "WHERE p.date BETWEEN :startDate AND :endDate " +
            "AND c.user.id = :userId " +
            "GROUP BY FUNCTION('DATE', p.date) " +
            "ORDER BY FUNCTION('DATE', p.date) ASC")
    List<Object[]> findMontantPaiementParJourSemaine(@Param("userId") Long userId,
                                                     @Param("startDate") LocalDateTime startDate,
                                                     @Param("endDate") LocalDateTime endDate);


    List<Paiement> findPaiementsByCommande(Commande commande);
}
