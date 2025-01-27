package com.did.MyShop.mappers.prodiut;

import com.did.MyShop.DTO.produit.ProduitRequest;
import com.did.MyShop.DTO.produit.ProduitResponse;
import com.did.MyShop.entities.Produit.Category;
import com.did.MyShop.entities.Produit.Produit;
import com.did.MyShop.entities.Produit.Tag;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class ProduitMapper {
    public static Produit toProduit(ProduitRequest produit){

        Produit p = new Produit();
        assigneObjetProduit(produit, p);
        return p;

    }

    public static Set<Tag> getTags(Set<Long> tagsId) {

        return tagsId.stream().map(id -> {
                var tag = new Tag();
                tag.setId(id);
                return tag;
            }).collect(Collectors.toSet());
    }

    public static Category getCategorie(Long catId) {
        var cat = new Category();
        cat.setId(catId);
        return cat;
    };


    public static ProduitResponse toProduitResponse(Produit produit) {
        return new ProduitResponse(
                produit.getId(),
                produit.getLibelle(),
                produit.getDescription(),
                produit.getPrix(),
                produit.getStock(),
                produit.getImage(),
                produit.getSeuil(),
                produit.getUnite(),
                CategorieMapper.toCategoriesResponse(produit.getCategory()),
                produit.getTags().stream().map(TagMapper::toTagsResponse).collect(Collectors.toSet()),
                produit.getPromotions(),
                produit.getLigneRavitaillements()
        );

    }

    public static void assigneObjetProduit(ProduitRequest produitRequest, Produit produit) {
        produit.setLibelle(produitRequest.libelle());
        produit.setDescription(produitRequest.description());
        produit.setPrix(produitRequest.prix());
        produit.setStock(produitRequest.stock());
        produit.setSeuil(produitRequest.seuil());
        produit.setUnite(produitRequest.unite());
        produit.setImage(produitRequest.image());
        produit.setCategory(getCategorie(produitRequest.categoryId()));
        produit.setTags(getTags(produitRequest.tagsId()));
    }
}
