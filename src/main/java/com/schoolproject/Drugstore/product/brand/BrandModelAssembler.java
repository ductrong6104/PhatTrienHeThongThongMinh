package com.schoolproject.Drugstore.product.brand;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BrandModelAssembler implements RepresentationModelAssembler<BrandDto, EntityModel<BrandDto>> {
    @Override
    public EntityModel<BrandDto> toModel(BrandDto brandDto){
        return EntityModel.of(brandDto,
                linkTo(methodOn(BrandController.class).one(brandDto.getId())).withSelfRel(),
                linkTo(methodOn(BrandController.class).all()).withRel("products"));
    }
}
