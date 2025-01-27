package com.did.MyShop.controllers.commande;


import com.did.MyShop.DTO.commande.LigneCommandeRequest;
import com.did.MyShop.DTO.commande.LigneCommandeResponse;
import com.did.MyShop.services.commande.LigneCommandeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/ligne-commandes")
public class LigneCommandeController {

/*
    private final LigneCommandeService ligneCommandeService;

    @GetMapping
    public List<LigneCommandeResponse> getAll() {
        return ligneCommandeService.findAll();

    }

    @GetMapping("/{id}")
    public LigneCommandeResponse getById(@PathVariable Long id) {
        return ligneCommandeService.findById(id);
    }

    @PostMapping
    public LigneCommandeResponse save(
            @Valid @RequestBody LigneCommandeRequest request
    ) {
        return ligneCommandeService.create(request);
    }

    @PutMapping("/{id}")
    public LigneCommandeResponse update(
            @PathVariable Long id,
            @Valid @RequestBody LigneCommandeRequest request
    ) {
        return ligneCommandeService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
        ligneCommandeService.delete(id);
    }
*/

}
