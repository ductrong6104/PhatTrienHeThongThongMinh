package com.schoolproject.Drugstore.product.image;



import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductImageModelAssembler implements RepresentationModelAssembler<ProductImage, EntityModel<ProductImage>> {
    @Override
    public EntityModel<ProductImage> toModel(ProductImage productImage){
        return EntityModel.of(productImage,
                linkTo(methodOn(ProductImageController.class).one(productImage.getId())).withSelfRel(),
                linkTo(methodOn(ProductImageController.class).all()).withRel("productImages"));
    }
}
