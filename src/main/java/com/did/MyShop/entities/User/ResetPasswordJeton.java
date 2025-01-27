package com.did.MyShop.entities.User;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResetPasswordJeton {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    private UUID jeton;

    @Column( nullable = false)
    private Boolean isExpire;

    @Column( nullable = false)
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private User user;


}
