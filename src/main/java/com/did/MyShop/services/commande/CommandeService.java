package com.did.MyShop.services.commande;


import com.did.MyShop.DTO.commande.CommandeRequest;
import com.did.MyShop.DTO.commande.CommandeResponse;
import com.did.MyShop.DTO.commande.CreateNewCommandeCredentiale;
import com.did.MyShop.DTO.commande.PaiementRequest;
import com.did.MyShop.Exceptions.RessourceNotFoundException;
import com.did.MyShop.entities.Commande.Client;
import com.did.MyShop.entities.Commande.Commande;
import com.did.MyShop.entities.Commande.LigneCommande;
import com.did.MyShop.entities.Commande.Paiement;
import com.did.MyShop.entities.Produit.Produit;
import com.did.MyShop.entities.User.User;
import com.did.MyShop.mappers.Commande.CommandeMapper;
import com.did.MyShop.mappers.Commande.PaiementMapper;
import com.did.MyShop.repositories.commande.ClientRepository;
import com.did.MyShop.repositories.commande.CommandeRepository;
import com.did.MyShop.repositories.produit.ProduitRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommandeService {

    private final PaiementService paiementService;
    private final ProduitRepository produitRepository;
    CommandeRepository commandeRepository;
    LigneCommandeService ligneCommandeService;
    private final ClientRepository clientRepository;


    public List<CommandeResponse> findAll(){
        return commandeRepository.findAll().stream().map(CommandeMapper::toCommandeResponse).collect(Collectors.toList());
    }

    public CommandeResponse findById(Long id){
        return CommandeMapper.toCommandeResponse(getCommande(id));
    }


    @Transactional
    public CommandeResponse create(CommandeRequest commandeRequest, Principal principal){
        Commande commande = CommandeMapper.toCommande(commandeRequest);
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        commande.setCreateAt(LocalDateTime.now());
        commande.setUser(user);
        commande.setClient(getClient(commandeRequest.clientId()));
        commande.setStatus(commandeRequest.status());
        commandeRepository.save(commande);
        commande.setLigneCommandes(new ArrayList<>());
        commandeRequest.ligneCommandes().forEach((lcreq)-> {
            LigneCommande lcI = ligneCommandeService.create(lcreq,commandeRepository.save(commande).getId());
            commande.getLigneCommandes().add(lcI);
        });


        return CommandeMapper.toCommandeResponse(commande);
    }



    public CommandeResponse update(Long id,CommandeRequest commandeRequest){
        Commande commande = getCommande(id);
        commande.setId(id);
        commande.setDescription(commandeRequest.description());
        commande.setStatus(commandeRequest.status());
        commandeRepository.save(commande);
        return CommandeMapper.toCommandeResponse(commande);
    }




    public void delete(Long id){
        commandeRepository.delete(getCommande(id));
    }

    private Commande getCommande(Long id){
        return commandeRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("Commande n°"+id +" non trouve"));
    }


    public Client getClient(Long id){
        return clientRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("Client n°"+id +" non trouve"));
    }

    public Produit getProduit(Long id){
        return produitRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("Produit n°"+id +" non trouve"));
    }


}
