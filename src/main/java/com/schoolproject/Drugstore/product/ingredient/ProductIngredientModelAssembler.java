package com.schoolproject.Drugstore.product.ingredient;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductIngredientModelAssembler implements RepresentationModelAssembler<ProductIngredientDto, EntityModel<ProductIngredientDto>> {
    @Override
    public EntityModel<ProductIngredientDto> toModel(ProductIngredientDto productUnitDto){
        return EntityModel.of(productUnitDto,
                linkTo(methodOn(ProductIngredientController.class).one(productUnitDto.getId())).withSelfRel(),
                linkTo(methodOn(ProductIngredientController.class).all()).withRel("productUnits"));
    }
}
