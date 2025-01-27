package com.did.MyShop.entities.Ravitaillement;

import com.did.MyShop.enums.StatusRavitaillement;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@ToString
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
    @OneToMany(mappedBy = "ravitaillement")
    private List<LigneRavitaillement> ligneRavitaillements;

}
