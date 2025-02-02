package com.did.MyShop.mappers.Raviatillement;

import com.did.MyShop.DTO.livraison.LigneRavitaillementRequest;
import com.did.MyShop.DTO.livraison.LigneRavitaillementResponse;
import com.did.MyShop.DTO.livraison.LigneRavitaillementResponseMini;
import com.did.MyShop.entities.Produit.Produit;
import com.did.MyShop.entities.Ravitaillement.LigneRavitaillement;
import com.did.MyShop.entities.Ravitaillement.Ravitaillement;
import com.did.MyShop.mappers.prodiut.ProduitMapper;

public class LigneRavitaillementMapper {

    public static LigneRavitaillement toLigneRavitaillement(LigneRavitaillementRequest request) {
        return LigneRavitaillement.builder()
                .quantite(request.quantite())
                .prixUnitaire(request.prixUnitaire())
                .produit(getProduit(request.produitId()))
                .build();
    }

    public static LigneRavitaillementResponse toLigneRavitaillementResponse(LigneRavitaillement ravitaillement) {
        return new LigneRavitaillementResponse(
                ravitaillement.getId(),
                ravitaillement.getQuantite(),
                RavitaillementMapper.toRavitaillementResponse(ravitaillement.getRavitaillement()),
                ProduitMapper.toProduitResponseMini(ravitaillement.getProduit()),
                ravitaillement.getPrixUnitaire()
        );
    }

    public static LigneRavitaillementResponseMini toLigneRavitaillementResponseMini(LigneRavitaillement ravitaillement) {
        return new LigneRavitaillementResponseMini(
                ravitaillement.getId(),
                ravitaillement.getQuantite(),
                ProduitMapper.toProduitResponseMini(ravitaillement.getProduit()),
                ravitaillement.getPrixUnitaire()
        );
    }


    private static Produit getProduit(Long id){
        return Produit.builder().id(id).build();
    }

    private static Ravitaillement getRavitaillement(Long id){
        return Ravitaillement.builder().id(id).build();
    }


}
