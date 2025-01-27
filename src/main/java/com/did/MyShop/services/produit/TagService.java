package com.did.MyShop.services.produit;

import com.did.MyShop.DTO.produit.TagRequest;
import com.did.MyShop.DTO.produit.TagResponse;
import com.did.MyShop.Exceptions.RessourceNotFoundException;
import com.did.MyShop.entities.Produit.Tag;
import com.did.MyShop.mappers.prodiut.TagMapper;
import com.did.MyShop.repositories.produit.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TagService {
    private TagRepository tagRepository;

    public List<TagResponse> getAll(){
        return  tagRepository.findAll().stream().map(TagMapper::toTagsResponse).collect(Collectors.toList());
    }

    public TagResponse getById(Long id){
        return TagMapper.toTagsResponse(tagRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("Tag n°"+ id+" introuvable")));
    }

    public TagResponse save(TagRequest tagRequest){
        return TagMapper.toTagsResponse(tagRepository.save(TagMapper.toTag(tagRequest)));
    }

    public TagResponse update(Long id, TagRequest tagRequest){
        Tag cat = tagRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("Tag n°"+ id+" introuvable"));
        cat.setName(tagRequest.name());
        cat.setDescription(tagRequest.description());
        return TagMapper.toTagsResponse(tagRepository.save(cat));
    }

    public void delete(Long id){
        Tag cat = tagRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("Tag n°"+ id+" introuvable"));
        tagRepository.delete(cat);
    }

}
