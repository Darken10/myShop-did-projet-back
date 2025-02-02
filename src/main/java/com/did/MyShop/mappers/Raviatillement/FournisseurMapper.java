package com.did.MyShop.mappers.Raviatillement;

import com.did.MyShop.DTO.livraison.FounisseurResponseMini;
import com.did.MyShop.DTO.livraison.FournisseurRequest;
import com.did.MyShop.DTO.livraison.FournisseurResponse;
import com.did.MyShop.entities.Ravitaillement.Fournisseur;

public class FournisseurMapper {

    public static Fournisseur toFournisseur(FournisseurRequest fournisseurRequest){
        return Fournisseur
                .builder()
                .name(fournisseurRequest.name())
                .address(fournisseurRequest.address())
                .phoneNumber(fournisseurRequest.phoneNumber())
                .email(fournisseurRequest.email())
                .description(fournisseurRequest.description())
                .build();
    }


    public static FournisseurResponse toFournisseurResponse(Fournisseur fournisseur){
        return new FournisseurResponse(
                fournisseur.getId(),
                fournisseur.getName(),
                fournisseur.getAddress(),
                fournisseur.getPhoneNumber(),
                fournisseur.getEmail(),
                fournisseur.getDescription(),
                fournisseur.getRavitaillements().stream().map(RavitaillementMapper::toRavitaillementResponse).toList()
        );
    }

    public static FounisseurResponseMini toFournisseurMini(Fournisseur fournisseur) {
        return new FounisseurResponseMini(
                fournisseur.getId(),
                fournisseur.getName(),
                fournisseur.getAddress(),
                fournisseur.getPhoneNumber(),
                fournisseur.getEmail()
        );
    }
}
