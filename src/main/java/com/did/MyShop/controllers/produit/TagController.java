package com.did.MyShop.controllers.produit;

import com.did.MyShop.DTO.produit.TagRequest;
import com.did.MyShop.DTO.produit.TagResponse;
import com.did.MyShop.services.produit.TagService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/tags")
@RestController
@AllArgsConstructor
public class TagController {
    private  TagService tagService;

    @GetMapping
    public List<TagResponse> getAll() {
        return tagService.getAll();

    }

    @GetMapping("/{id}")
    public TagResponse getOne(
            @PathVariable Long id
    ) {
        return tagService.getById(id);
    }

    @PostMapping
    public TagResponse addCategory(
            @RequestBody TagRequest categorieRequest
    ){
       return tagService.save(categorieRequest);
    }

    @PutMapping("/{id}")
    public TagResponse updateCategory(
            @RequestBody TagRequest categorieRequest,
            @PathVariable Long id
    ){
        return tagService.update(id,categorieRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        tagService.delete(id);
    }



}
