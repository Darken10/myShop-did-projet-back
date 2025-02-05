package com.did.MyShop.DTO.statistique;

import java.util.Date;

public record CommandeStatistique(
        Date startDate,
        Date endDate,
        DaysCommandeStatistique day1,
        DaysCommandeStatistique day2,
        DaysCommandeStatistique day3,
        DaysCommandeStatistique day4,
        DaysCommandeStatistique day5,
        DaysCommandeStatistique day6,
        DaysCommandeStatistique day7
) {
}
