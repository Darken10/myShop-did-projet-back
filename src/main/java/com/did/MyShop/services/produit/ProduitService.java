package com.did.MyShop.services.produit;

import com.did.MyShop.DTO.produit.ProduitRequest;
import com.did.MyShop.DTO.produit.ProduitResponse;
import com.did.MyShop.Exceptions.RessourceNotFoundException;
import com.did.MyShop.entities.Produit.Produit;
import com.did.MyShop.entities.Produit.Tag;
import com.did.MyShop.mappers.prodiut.ProduitMapper;
import com.did.MyShop.repositories.produit.CategorieRepository;
import com.did.MyShop.repositories.produit.ProduitRepository;
import com.did.MyShop.repositories.produit.TagRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static com.did.MyShop.mappers.prodiut.ProduitMapper.*;

@Slf4j
@Service
@AllArgsConstructor

public class ProduitService {

    private final CategorieRepository categorieRepository;
    private final TagRepository tagRepository;
    private ProduitRepository produitRepository;


    public List<ProduitResponse> getAll(){
        return  produitRepository.findAll().stream().map(ProduitMapper::toProduitResponse).collect(Collectors.toList());
    }

    public ProduitResponse getById(Long id){
        return ProduitMapper.toProduitResponse(produitRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("Categorie n°"+ id+" introuvable")));
    }

    public ProduitResponse save(ProduitRequest produitRequest){
        isCategorieExist(produitRequest.categoryId());
        produitRequest.tagsId().forEach(this::isTagExist);
        System.out.println(produitRequest);
        List<Tag> tags = tagRepository.findAllById(produitRequest.tagsId());

        Produit produit = ProduitMapper.toProduit(produitRequest);

        produit.setTags(new HashSet<>());
        produitRequest.tagsId().forEach(tagId->produit.addTag(tagRepository.findById(tagId).orElseThrow(() -> new RessourceNotFoundException("Aucun role associer a cet identifiant"))));

        Produit produit2 = produitRepository.save(produit);
        System.out.println("=================================================================");
        produit2.getTags().forEach(System.out::println);
        ProduitResponse produitResponse = ProduitMapper.toProduitResponse(produit2);

        return produitResponse;
    }

    public ProduitResponse update(Long id, ProduitRequest produitRequest){
        Produit produit = produitRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("produit n°"+ id+" introuvable"));
        isCategorieExist(produitRequest.categoryId());
        produitRequest.tagsId().forEach(this::isTagExist);
        assigneObjetProduit(produitRequest, produit);
        return ProduitMapper.toProduitResponse(produitRepository.save(produit));
    }


    public void delete(Long id){
        Produit produit = produitRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("produit n°"+ id+" introuvable"));
        produitRepository.delete(produit);
    }

    private void isCategorieExist(Long id){
        categorieRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("Categorie n°"+ id+" introuvable"));
    }

    private void isTagExist(Long id){
        System.out.println("isTagExist  : tag "+id );
        tagRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("Tag n°" + id + " introuvable"));
    }
}
