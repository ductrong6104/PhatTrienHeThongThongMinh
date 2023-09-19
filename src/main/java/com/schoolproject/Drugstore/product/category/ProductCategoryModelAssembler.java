package com.schoolproject.Drugstore.product.category;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductCategoryModelAssembler implements RepresentationModelAssembler<ProductCategoryDto, EntityModel<ProductCategoryDto>> {
    @Override
    public EntityModel<ProductCategoryDto> toModel(ProductCategoryDto productCategoryDto){
        return EntityModel.of(productCategoryDto,
                linkTo(methodOn(ProductCategoryController.class).one(productCategoryDto.getId())).withSelfRel(),
                linkTo(methodOn(ProductCategoryController.class).all()).withRel("products"));
    }
}
