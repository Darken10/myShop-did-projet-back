package com.did.MyShop.mappers.Raviatillement;


import com.did.MyShop.DTO.livraison.LigneRavitaillementResponseMini;
import com.did.MyShop.DTO.livraison.RavitaillementResponse;
import com.did.MyShop.DTO.livraison.RavitaillementResquest;
import com.did.MyShop.entities.Ravitaillement.Fournisseur;
import com.did.MyShop.entities.Ravitaillement.Ravitaillement;

import java.util.ArrayList;
import java.util.List;

public class RavitaillementMapper {
    public static Ravitaillement toRavitaillement(RavitaillementResquest request) {
        return Ravitaillement.builder()
                .createDate(request.createDate())
                .deliveredDate(request.deliveredDate())
                .status(request.status())
                .fournisseur(getFournisseur(request.fournisseurId()))
                .description(request.description())
                .build();
    }

    public static RavitaillementResponse toRavitaillementResponse(Ravitaillement ravitaillement) {
        List<LigneRavitaillementResponseMini> list = new ArrayList<>();
        if (ravitaillement.getLigneRavitaillements() != null ) {
            list = ravitaillement.getLigneRavitaillements().stream().map(LigneRavitaillementMapper::toLigneRavitaillementResponseMini).toList();
        }
        return new RavitaillementResponse(
                ravitaillement.getId(),
                ravitaillement.getCreateDate(),
                ravitaillement.getDeliveredDate(),
                ravitaillement.getStatus(),
                FournisseurMapper.toFournisseurMini(ravitaillement.getFournisseur()),
                list

        );
    }


    private static Fournisseur getFournisseur(Long id) {
        return Fournisseur.builder().id(id).build();
    }

}
