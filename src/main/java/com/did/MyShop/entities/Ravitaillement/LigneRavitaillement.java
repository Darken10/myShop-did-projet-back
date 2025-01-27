package com.did.MyShop.entities.Ravitaillement;

import com.did.MyShop.entities.Produit.Produit;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@ToString
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
    private Ravitaillement ravitaillement;
    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

}
