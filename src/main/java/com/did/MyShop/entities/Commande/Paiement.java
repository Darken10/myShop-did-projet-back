package com.did.MyShop.entities.Commande;

import com.did.MyShop.enums.MethodePaiementEnum;
import com.did.MyShop.enums.StatusPaimentEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private MethodePaiementEnum methode;
    private String reference;
    private Double amount;
    private LocalDateTime date;
    private StatusPaimentEnum status;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "commande_id")
    @JsonIgnore
    private Commande commande;
}
