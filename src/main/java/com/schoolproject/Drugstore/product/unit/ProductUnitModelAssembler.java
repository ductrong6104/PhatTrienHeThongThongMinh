package com.schoolproject.Drugstore.product.unit;



import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductUnitModelAssembler implements RepresentationModelAssembler<ProductUnit, EntityModel<ProductUnit>> {
    @Override
    public EntityModel<ProductUnit> toModel(ProductUnit productUnit){
        return EntityModel.of(productUnit,
                linkTo(methodOn(ProductUnitController.class).one(productUnit.getId())).withSelfRel(),
                linkTo(methodOn(ProductUnitController.class).all()).withRel("productUnits"));
    }
}
