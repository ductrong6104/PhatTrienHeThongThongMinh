package com.schoolproject.Drugstore.product.type;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductTypeModelAssembler implements RepresentationModelAssembler<ProductTypeDto, EntityModel<ProductTypeDto>> {
    @Override
    public EntityModel<ProductTypeDto> toModel(ProductTypeDto productGroupDto){
        return EntityModel.of(productGroupDto,
                linkTo(methodOn(ProductTypeController.class).one(productGroupDto.getId())).withSelfRel(),
                linkTo(methodOn(ProductTypeController.class).all()).withRel("products"));
    }
}
