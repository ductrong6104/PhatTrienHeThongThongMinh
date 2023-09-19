package com.schoolproject.Drugstore.product.comment;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductCommentModelAssembler implements RepresentationModelAssembler<ProductCommentDto, EntityModel<ProductCommentDto>> {
    @Override
    public EntityModel<ProductCommentDto> toModel(ProductCommentDto productUnitDto){
        return EntityModel.of(productUnitDto,
                linkTo(methodOn(ProductCommentController.class).one(productUnitDto.getId())).withSelfRel(),
                linkTo(methodOn(ProductCommentController.class).all()).withRel("productUnits"));
    }
}
