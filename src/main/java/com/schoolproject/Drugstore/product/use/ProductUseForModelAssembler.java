package com.schoolproject.Drugstore.product.use;

import com.schoolproject.Drugstore.product.use.ProductUseForController;
import com.schoolproject.Drugstore.product.use.ProductUseForDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductUseForModelAssembler implements RepresentationModelAssembler<ProductUseForDto, EntityModel<ProductUseForDto>> {
    @Override
    public EntityModel<ProductUseForDto> toModel(ProductUseForDto productUseForDto){
        return EntityModel.of(productUseForDto,
                linkTo(methodOn(ProductUseForController.class).one(productUseForDto.getId())).withSelfRel(),
                linkTo(methodOn(ProductUseForController.class).all()).withRel("productUseForUseFors"));
    }
}
