package com.did.MyShop.mappers.prodiut;


import com.did.MyShop.DTO.produit.CategorieRequest;
import com.did.MyShop.DTO.produit.CategorieResponse;
import com.did.MyShop.entities.Produit.Category;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CategorieMapper {

    public static Category toCategorie(CategorieRequest request) {
        Category category = new Category();
        category.setName(request.name());
        category.setDescription(request.description());
        return category;
    }

    public static CategorieResponse toCategoriesResponse(Category category) {
        log.info("toCategoriesResponse : ------------------------------------------------------------ {}",category);
        return new CategorieResponse(category.getId(),category.getName(),category.getDescription());

    }
}
