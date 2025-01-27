package com.did.MyShop.controllers.commande;

import com.did.MyShop.DTO.commande.CommandeRequest;
import com.did.MyShop.DTO.commande.CommandeResponse;
import com.did.MyShop.DTO.commande.CreateNewCommandeCredentiale;
import com.did.MyShop.services.commande.CommandeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/commandes")
public class CommandeController {


    private final CommandeService commandeService;

    @GetMapping
    public List<CommandeResponse> getAll() {
        return commandeService.findAll();

    }

    @GetMapping("/{id}")
    public CommandeResponse getById(@PathVariable Long id) {
        return commandeService.findById(id);
    }

    @PostMapping
    public CommandeResponse save(
            @Valid @RequestBody CommandeRequest request,
            Principal userConnected
    ) {
        return commandeService.create(request,userConnected);
    }


/*
    @PutMapping("/{id}")
    public CommandeResponse update(
            @PathVariable Long id,
            @Valid @RequestBody CommandeRequest request
    ) {
        return commandeService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
        commandeService.delete(id);
    }
*/
}
