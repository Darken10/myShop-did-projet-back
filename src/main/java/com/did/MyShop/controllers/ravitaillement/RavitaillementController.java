package com.did.MyShop.controllers.ravitaillement;

import com.did.MyShop.DTO.livraison.LigneRavitaillementRequest;
import com.did.MyShop.DTO.livraison.RavitaillementResponse;
import com.did.MyShop.DTO.livraison.RavitaillementResquest;
import com.did.MyShop.entities.Ravitaillement.Ravitaillement;
import com.did.MyShop.mappers.Raviatillement.RavitaillementMapper;
import com.did.MyShop.services.ravitaillement.LigneRavitaillementService;
import com.did.MyShop.services.ravitaillement.RavitaillementService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("ravitaillements")
@AllArgsConstructor
public class RavitaillementController {

    private final RavitaillementService ravitaillementService;

    @GetMapping
    public List<RavitaillementResponse> getAll(){
        return ravitaillementService.findAll().stream().map(RavitaillementMapper::toRavitaillementResponse).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public RavitaillementResponse getById( @PathVariable Long id){
        return RavitaillementMapper.toRavitaillementResponse(ravitaillementService.findById(id));
    }

    @PostMapping
    public RavitaillementResponse save(
            @Valid @RequestBody RavitaillementResquest ravitaillementResquest,
            Principal principal
    ){
        return RavitaillementMapper.toRavitaillementResponse(ravitaillementService.save(ravitaillementResquest, principal));
    }


    @PostMapping("{id}/add-ligne-ravitaillement")
    public RavitaillementResponse addLigneRavitaillement(
            @PathVariable Long id,
            @Valid @RequestBody LigneRavitaillementRequest request
    ){
        System.out.println(request);
        System.out.println("-----------------------------------------------");
        return RavitaillementMapper.toRavitaillementResponse(ravitaillementService.addLigneRavitaillement(id, request));
    }



}
