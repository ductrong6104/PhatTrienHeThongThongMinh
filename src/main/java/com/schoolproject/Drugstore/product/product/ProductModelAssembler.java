package com.schoolproject.Drugstore.product.product;


import com.schoolproject.Drugstore.product.product.Product;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductModelAssembler implements RepresentationModelAssembler<ProductDto, EntityModel<ProductDto>> {
    @Override
    public EntityModel<ProductDto> toModel(ProductDto productDto){
        return EntityModel.of(productDto,
                linkTo(methodOn(ProductController.class).one(productDto.getId())).withSelfRel(),
                linkTo(methodOn(ProductController.class).all()).withRel("products"));
    }
}
