package com.did.MyShop.controllers.commande;

import com.did.MyShop.DTO.commande.ClientRequest;
import com.did.MyShop.DTO.commande.ClientResponse;
import com.did.MyShop.services.commande.ClientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {


    private ClientService clientService;

     @GetMapping
    public List<ClientResponse> getAll() {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public ClientResponse getById(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @PostMapping
    public ClientResponse save(
         @Valid  @RequestBody   ClientRequest request
    ) {
        System.out.println(request);
         return clientService.create(request);
    }

    @PutMapping("/{id}")
    public ClientResponse update(
            @PathVariable Long id,
            @Valid @RequestBody ClientRequest request
    ) {
        return clientService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
          clientService.delete(id);
    }

}
