package com.did.MyShop.controllers.produit;

import com.did.MyShop.DTO.produit.CategorieRequest;
import com.did.MyShop.DTO.produit.CategorieResponse;
import com.did.MyShop.entities.Produit.Category;
import com.did.MyShop.mappers.prodiut.CategorieMapper;
import com.did.MyShop.services.produit.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/categories")
@RestController
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;

    @GetMapping
    public List<CategorieResponse> getAll() {
        return categoryService.getAll();

    }

    @GetMapping("/{id}")
    public CategorieResponse getOne(
            @PathVariable Long id
    ) {
        return CategorieMapper.toCategoriesResponse(categoryService.getById(id));
    }

    @PostMapping
    public Category addCategory(
            @RequestBody CategorieRequest categorieRequest
    ){
       return categoryService.save(categorieRequest) ;
    }

    @PutMapping("/{id}")
    public Category updateCategory(
            @RequestBody CategorieRequest categorieRequest,
            @PathVariable Long id
    ){
        return categoryService.update(id,categorieRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryService.delete(id);
    }



}
