package com.did.MyShop.entities.Ravitaillement;

import com.did.MyShop.entities.User.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    @OneToMany(mappedBy = "fournisseur",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Ravitaillement> ravitaillements;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String description;


}
