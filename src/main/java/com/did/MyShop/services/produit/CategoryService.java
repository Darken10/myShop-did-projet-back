package com.did.MyShop.services.produit;

import com.did.MyShop.DTO.produit.CategorieRequest;
import com.did.MyShop.DTO.produit.CategorieResponse;
import com.did.MyShop.Exceptions.RessourceNotFoundException;
import com.did.MyShop.entities.Produit.Category;
import com.did.MyShop.mappers.prodiut.CategorieMapper;
import com.did.MyShop.repositories.produit.CategorieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {

    private  CategorieRepository categorieRepository;


    public List<CategorieResponse> getAll(){
        return  categorieRepository.findAll().stream().map(CategorieMapper::toCategoriesResponse).collect(Collectors.toList());
    }

    public Category getById(Long id){
        return categorieRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("Categorie n°"+ id+" introuvable"));
    }

    public Category save(CategorieRequest categoryRequest){
        return categorieRepository.save(CategorieMapper.toCategorie(categoryRequest));
    }

    public Category update(Long id, CategorieRequest categoryRequest){
        Category cat = categorieRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("Categorie n°\"+ id+\" introuvable"));
        cat.setName(categoryRequest.name());
        cat.setDescription(categoryRequest.description());
        return categorieRepository.save(cat);
    }

    public void delete(Long id){
        Category cat = categorieRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("Categorie n°\"+ id+\" introuvable"));
        categorieRepository.delete(cat);
    }
}
