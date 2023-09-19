package com.schoolproject.Drugstore.product.unit;


import com.schoolproject.Drugstore.product.unit.ProductUnitDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductUnitModelAssembler implements RepresentationModelAssembler<ProductUnitDto, EntityModel<ProductUnitDto>> {
    @Override
    public EntityModel<ProductUnitDto> toModel(ProductUnitDto productUnitDto){
        return EntityModel.of(productUnitDto,
                linkTo(methodOn(ProductUnitController.class).one(productUnitDto.getId())).withSelfRel(),
                linkTo(methodOn(ProductUnitController.class).all()).withRel("productUnits"));
    }
}
