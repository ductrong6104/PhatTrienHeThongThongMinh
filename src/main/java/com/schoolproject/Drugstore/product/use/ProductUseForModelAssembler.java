package com.schoolproject.Drugstore.product.use;



import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductUseForModelAssembler implements RepresentationModelAssembler<ProductUseFor, EntityModel<ProductUseFor>> {
    @Override
    public EntityModel<ProductUseFor> toModel(ProductUseFor productUseFor){
        return EntityModel.of(productUseFor,
                linkTo(methodOn(ProductUseForController.class).one(productUseFor.getId())).withSelfRel(),
                linkTo(methodOn(ProductUseForController.class).all()).withRel("productUseFors"));
    }
}
