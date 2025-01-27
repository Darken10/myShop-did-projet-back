package com.did.MyShop.mappers.prodiut;

import com.did.MyShop.DTO.produit.TagRequest;
import com.did.MyShop.DTO.produit.TagResponse;
import com.did.MyShop.entities.Produit.Tag;

public class TagMapper {

    public static Tag toTag(TagRequest request){
        Tag tag = new Tag();
        tag.setName(request.name());
        tag.setDescription(request.description());
        return tag;
    }

    public static TagResponse toTagsResponse(Tag tag) {
        return new TagResponse(tag.getId(), tag.getName(), tag.getDescription());

    }
}
