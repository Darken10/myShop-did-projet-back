package com.did.MyShop.DTO.statistique;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VenteParJourDTO {
    private LocalDateTime date;
    private Long montantVendu;

}
