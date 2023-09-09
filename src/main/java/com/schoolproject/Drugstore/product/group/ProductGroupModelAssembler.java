package com.schoolproject.Drugstore.product.group;



import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductGroupModelAssembler implements RepresentationModelAssembler<ProductGroup, EntityModel<ProductGroup>> {
    @Override
    public EntityModel<ProductGroup> toModel(ProductGroup productGroup){
        return EntityModel.of(productGroup,
                linkTo(methodOn(ProductGroupController.class).one(productGroup.getId())).withSelfRel(),
                linkTo(methodOn(ProductGroupController.class).all()).withRel("productGroups"));
    }
}
