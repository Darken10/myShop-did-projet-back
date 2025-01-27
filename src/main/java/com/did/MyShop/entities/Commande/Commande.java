package com.did.MyShop.entities.Commande;

import com.did.MyShop.entities.User.User;
import com.did.MyShop.enums.StatusCommandEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @Enumerated(EnumType.STRING)
    private StatusCommandEnum status;
    private String description;
    @OneToMany
    private List<LigneCommande> ligneCommandes = new ArrayList<>();
    @OneToMany
    private List<Paiement> paiements = new ArrayList<>();
    private LocalDateTime createAt;

}
