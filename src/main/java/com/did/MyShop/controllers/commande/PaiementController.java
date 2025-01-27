package com.did.MyShop.controllers.commande;

import com.did.MyShop.DTO.commande.PaiementRequest;
import com.did.MyShop.DTO.commande.PaiementResponse;
import com.did.MyShop.mappers.Commande.PaiementMapper;
import com.did.MyShop.services.commande.PaiementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/paiements")
@AllArgsConstructor
public class PaiementController {

    private final PaiementService paiementService;

    @GetMapping
    public List<PaiementResponse> all() {
        return paiementService.findAll().stream().map(PaiementMapper::toPaiementResponse).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public PaiementResponse getPaiement( @PathVariable Long id) {
        return PaiementMapper.toPaiementResponse(paiementService.findById(id));
    }

    @PostMapping
    public PaiementResponse create(@RequestBody PaiementRequest request) {
        return PaiementMapper.toPaiementResponse(paiementService.save(request));
    }
}
