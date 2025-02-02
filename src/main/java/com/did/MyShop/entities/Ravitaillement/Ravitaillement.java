package com.did.MyShop.entities.Ravitaillement;

import com.did.MyShop.entities.User.User;
import com.did.MyShop.enums.StatusRavitaillement;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ravitaillement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createDate;
    private LocalDateTime deliveredDate;
    private StatusRavitaillement  status;
    @OneToMany(mappedBy = "ravitaillement",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<LigneRavitaillement> ligneRavitaillements = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String description;


}
