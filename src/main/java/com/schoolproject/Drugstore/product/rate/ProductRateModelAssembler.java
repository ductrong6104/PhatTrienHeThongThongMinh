package com.schoolproject.Drugstore.product.rate;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductRateModelAssembler implements RepresentationModelAssembler<ProductRateDto, EntityModel<ProductRateDto>> {
    @Override
    public EntityModel<ProductRateDto> toModel(ProductRateDto productUnitDto){
        return EntityModel.of(productUnitDto,
                linkTo(methodOn(ProductRateController.class).one(productUnitDto.getId())).withSelfRel(),
                linkTo(methodOn(ProductRateController.class).all()).withRel("productUnits"));
    }
}
