package com.did.MyShop.entities.Ravitaillement;

import com.did.MyShop.entities.Produit.Produit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LigneRavitaillement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double quantite;
    @ManyToOne
    @JoinColumn(name = "ravitaillement_id")
    @JsonIgnore
    private Ravitaillement ravitaillement;
    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

    private Double prixUnitaire;

}
