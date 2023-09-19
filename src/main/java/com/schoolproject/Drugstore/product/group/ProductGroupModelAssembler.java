package com.schoolproject.Drugstore.product.group;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductGroupModelAssembler implements RepresentationModelAssembler<ProductGroupDto, EntityModel<ProductGroupDto>> {
    @Override
    public EntityModel<ProductGroupDto> toModel(ProductGroupDto productGroupDto){
        return EntityModel.of(productGroupDto,
                linkTo(methodOn(ProductGroupController.class).one(productGroupDto.getId())).withSelfRel(),
                linkTo(methodOn(ProductGroupController.class).all()).withRel("products"));
    }
}
