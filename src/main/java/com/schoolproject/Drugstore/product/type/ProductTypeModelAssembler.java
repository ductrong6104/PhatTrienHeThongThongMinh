package com.schoolproject.Drugstore.product.type;



import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductTypeModelAssembler implements RepresentationModelAssembler<ProductType, EntityModel<ProductType>> {
    @Override
    public EntityModel<ProductType> toModel(ProductType productType){
        return EntityModel.of(productType,
                linkTo(methodOn(ProductTypeController.class).one(productType.getId())).withSelfRel(),
                linkTo(methodOn(ProductTypeController.class).all()).withRel("productTypes"));
    }
}
