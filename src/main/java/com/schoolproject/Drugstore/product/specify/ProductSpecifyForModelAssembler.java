package com.schoolproject.Drugstore.product.specify;



import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductSpecifyForModelAssembler implements RepresentationModelAssembler<ProductSpecifyFor, EntityModel<ProductSpecifyFor>> {
    @Override
    public EntityModel<ProductSpecifyFor> toModel(ProductSpecifyFor productSpecifyFor){
        return EntityModel.of(productSpecifyFor,
                linkTo(methodOn(ProductSpecifyForController.class).one(productSpecifyFor.getId())).withSelfRel(),
                linkTo(methodOn(ProductSpecifyForController.class).all()).withRel("productSpecifyFors"));
    }
}
