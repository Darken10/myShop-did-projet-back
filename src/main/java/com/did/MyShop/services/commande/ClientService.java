package com.did.MyShop.services.commande;

import com.did.MyShop.DTO.commande.ClientRequest;
import com.did.MyShop.DTO.commande.ClientResponse;
import com.did.MyShop.Exceptions.RessourceNotFoundException;
import com.did.MyShop.entities.Commande.Client;
import com.did.MyShop.mappers.Commande.ClientMapper;
import com.did.MyShop.repositories.commande.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientService {

    ClientRepository clientRepository;

    public List<ClientResponse> findAll(){
        return clientRepository.findAll().stream().map(ClientMapper::toClientResponse).collect(Collectors.toList());
    }

    public ClientResponse findById(Long id){
        return ClientMapper.toClientResponse(getClient(id));
    }

    public ClientResponse create(ClientRequest clientRequest){
        Client client = ClientMapper.toClient(clientRequest);
        clientRepository.save(client);
        return ClientMapper.toClientResponse(client);
    }

    public ClientResponse update(Long id,ClientRequest clientRequest){
        Client client = getClient(id);
        client.setId(id);
        client.setName(clientRequest.name());
        client.setPhone(clientRequest.phone());
        client.setSolde(clientRequest.solde());
        clientRepository.save(client);
        return ClientMapper.toClientResponse(client);
    }

    public void delete(Long id){
        clientRepository.delete(getClient(id));
    }

    private Client getClient(Long id){
        return clientRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("Client n°"+id +" non trouve"));
    }

}
