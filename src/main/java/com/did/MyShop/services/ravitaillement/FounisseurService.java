package com.did.MyShop.services.ravitaillement;

import com.did.MyShop.DTO.livraison.FournisseurRequest;
import com.did.MyShop.Exceptions.RessourceNotFoundException;
import com.did.MyShop.entities.Ravitaillement.Fournisseur;
import com.did.MyShop.entities.User.User;
import com.did.MyShop.mappers.Raviatillement.FournisseurMapper;
import com.did.MyShop.repositories.ravitaillement.FournisseurRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@AllArgsConstructor
public class FounisseurService {

    private FournisseurRepository fournisseurRepository;

    public List<Fournisseur> findAll() {
        return fournisseurRepository.findAll();
    }

    public Fournisseur findById(Long id) {
        return fournisseurRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("Fournisseur n°"+ id+" introuvable"));
    }

    public Fournisseur save(FournisseurRequest fournisseurResquest, Principal principal) {
        Fournisseur fournisseur = FournisseurMapper.toFournisseur(fournisseurResquest);
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        fournisseur.setUser(user);
        return fournisseurRepository.save(fournisseur);

    }

}
