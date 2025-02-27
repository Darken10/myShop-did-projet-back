package com.did.MyShop.controllers.commande;

import com.did.MyShop.DTO.commande.FullPromotion;
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
@RequestMapping("/promotions")
@AllArgsConstructor
public class PromotionController {

    private final PromotionService promotionService;

    @GetMapping
    public List<PromotionResponse> findAll(){
        return promotionService.findAll().stream().map(PromotionMapper::toPromotionResponse).collect(Collectors.toList());
    }
    

    @GetMapping("{promoId}")
    public FullPromotion findOne(
            @PathVariable Long promoId){
        return PromotionMapper.toFullPromotionResponse(promotionService.findById(promoId));
    }

    @GetMapping("{promoId}/retire/{prodId}")
    public void retiter(
            @PathVariable("promoId") Long promoId,
            @PathVariable Long prodId
    ){
        promotionService.retirer(promoId,prodId);
    }



    @PostMapping
    public PromotionResponse create(@Valid @RequestBody PromotionRequest request){
        return PromotionMapper.toPromotionResponse(promotionService.save(request));
    }

}
