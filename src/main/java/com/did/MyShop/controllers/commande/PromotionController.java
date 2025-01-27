package com.did.MyShop.controllers.commande;

import com.did.MyShop.DTO.commande.PromotionRequest;
import com.did.MyShop.DTO.commande.PromotionResponse;
import com.did.MyShop.entities.Commande.Promotion;
import com.did.MyShop.mappers.Commande.PromotionMapper;
import com.did.MyShop.services.commande.PromotionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/promostions")
@AllArgsConstructor
public class PromotionController {

    private final PromotionService promotionService;

    @GetMapping
    public List<PromotionResponse> findAll(){
        return promotionService.findAll().stream().map(PromotionMapper::toPromotionResponse).collect(Collectors.toList());
    }

    @GetMapping("{promoId}")
    public PromotionResponse findOne(
            @PathVariable Long promoId){
        return PromotionMapper.toPromotionResponse(promotionService.findById(promoId));
    }

    @PostMapping
    public PromotionResponse create(@Valid @RequestBody PromotionRequest request){
        return PromotionMapper.toPromotionResponse(promotionService.save(request));
    }

}
