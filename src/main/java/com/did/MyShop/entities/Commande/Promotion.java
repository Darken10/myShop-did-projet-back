package com.did.MyShop.entities.Commande;

import com.did.MyShop.entities.Produit.Produit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double reduction;
    private Boolean isPercent;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createDate;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "produit_promotion",
            joinColumns = @JoinColumn(name = "produit_id"),
            inverseJoinColumns = @JoinColumn(name = "promotion_id")
    )
    @JsonIgnore
    private Set<Produit> produits = new HashSet<>();

    public void addProduit(Produit produit) {
        this.produits.add(produit);
        produit.getPromotions().add(this);  // Assurer la bidirectionnalité
    }

    public void removeProduit(Produit produit) {
        this.produits.remove(produit);
        produit.getPromotions().remove(this);
    }

}
