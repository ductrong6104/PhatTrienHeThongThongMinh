package com.schoolproject.Drugstore.product.ingredient;



import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductIngredientModelAssembler implements RepresentationModelAssembler<ProductIngredient, EntityModel<ProductIngredient>> {
    @Override
    public EntityModel<ProductIngredient> toModel(ProductIngredient productIngredient){
        return EntityModel.of(productIngredient,
                linkTo(methodOn(ProductIngredientController.class).one(productIngredient.getId())).withSelfRel(),
                linkTo(methodOn(ProductIngredientController.class).all()).withRel("productIngredients"));
    }
}
