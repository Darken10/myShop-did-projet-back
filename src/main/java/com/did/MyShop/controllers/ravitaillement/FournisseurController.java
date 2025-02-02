package com.did.MyShop.controllers.ravitaillement;

import com.did.MyShop.DTO.livraison.FounisseurResponseMini;
import com.did.MyShop.DTO.livraison.FournisseurRequest;
import com.did.MyShop.DTO.livraison.FournisseurResponse;
import com.did.MyShop.DTO.livraison.RavitaillementResquest;
import com.did.MyShop.mappers.Raviatillement.FournisseurMapper;
import com.did.MyShop.mappers.Raviatillement.RavitaillementMapper;
import com.did.MyShop.services.ravitaillement.FounisseurService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("fournisseurs")
@AllArgsConstructor
public class FournisseurController {
    private final FounisseurService founisseurService;

    @GetMapping
    public List<FournisseurResponse> getAll(){
        return founisseurService.findAll().stream().map(FournisseurMapper::toFournisseurResponse).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public FournisseurResponse getById( @PathVariable Long id){
        return FournisseurMapper.toFournisseurResponse(founisseurService.findById(id));
    }

    @PostMapping
    public FounisseurResponseMini save(
            @Valid @RequestBody FournisseurRequest ravitaillementResquest,
            Principal principal
    ){
        return FournisseurMapper.toFournisseurMini(founisseurService.save(ravitaillementResquest, principal));
    }

}
