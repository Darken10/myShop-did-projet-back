package com.did.MyShop.controllers.produit;

import com.did.MyShop.DTO.produit.ProduitRequest;
import com.did.MyShop.DTO.produit.ProduitResponse;
import com.did.MyShop.DTO.produit.ProduitResponseMini;
import com.did.MyShop.services.produit.ProduitService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/produits")
@RestController
@AllArgsConstructor
public class ProduitController {
    private final ProduitService produitService;

    @GetMapping
    public List<ProduitResponse> getAll() {
        return produitService.getAll();

    }

    @GetMapping("/mini")
    public List<ProduitResponseMini> getAllMini() {
        return produitService.getAllMini();

    }

    @GetMapping("/{id}")
    public ProduitResponse getOne(
            @PathVariable Long id
    ) {
        return produitService.getById(id);
    }

    @PostMapping
    public ProduitResponse addProduit(
            @Valid  @RequestBody ProduitRequest produitRequest
    ){
       return produitService.save(produitRequest) ;
    }

    @PutMapping("/{id}")
    public ProduitResponse updateProduit(
            @Valid @RequestBody ProduitRequest produitRequest,
            @PathVariable Long id
    ){
        return produitService.update(id, produitRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteProduit(@PathVariable Long id){

        produitService.delete(id);
    }



}
