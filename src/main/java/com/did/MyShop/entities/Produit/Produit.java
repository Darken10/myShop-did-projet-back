package com.did.MyShop.entities.Produit;

import com.did.MyShop.entities.Commande.LigneCommande;
import com.did.MyShop.entities.Commande.Promotion;
import com.did.MyShop.entities.Ravitaillement.LigneRavitaillement;
import com.did.MyShop.entities.User.Role;
import com.did.MyShop.enums.UniteProduitEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private double prix;
    private int stock;
    private String description;
    private String image;
    private Double seuil;
    @Enumerated(EnumType.STRING)
    private UniteProduitEnum unite;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(mappedBy = "produits",cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER)
    private Set<Tag> tags = new HashSet<>();

    @ManyToMany(mappedBy = "produits",cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER)
    private Set<Promotion> promotions =  new HashSet<>();
    @OneToMany(mappedBy = "produit")
    private List<LigneRavitaillement> ligneRavitaillements;

    @OneToMany(mappedBy = "produit")
    private List<LigneCommande> ligneCommandes;


    public void addTag(Tag tag) {
        this.tags.add(tag);
        tag.getProduits().add(this);  // Assurer la bidirectionnalité
    }

    public void addPromotion(Promotion promotion) {
        this.promotions.add(promotion);
        promotion.getProduits().add(this);  // Assurer la bidirectionnalité
    }
}
