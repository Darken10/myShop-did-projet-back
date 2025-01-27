package com.did.MyShop.DTO.produit;

import com.did.MyShop.enums.UniteProduitEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;
import java.util.Set;

public record ProduitRequest(

        @NotBlank(message = "Le libeller est obligatoire")
        String libelle,

        String description,

        @NotBlank(message = "Le prix est obligatoire")
        @PositiveOrZero(message = "Le prix ne peut pas etre inferieur a 0")
        Double prix,

        @NotBlank(message = "Le stock est obligatoire")
                @PositiveOrZero(message = "Le stock ne peut pas etre inferieur a 0")
        Integer stock,

        String image,


        Double seuil,

        UniteProduitEnum unite,

        @NotBlank(message = "La categorie est obligatoire")
        Long categoryId,

        Set<Long> tagsId



/*
        Set<Long> promotionsId
        */
) {
}
