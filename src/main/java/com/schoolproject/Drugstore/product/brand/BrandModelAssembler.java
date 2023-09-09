package com.schoolproject.Drugstore.product.brand;



import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BrandModelAssembler implements RepresentationModelAssembler<Brand, EntityModel<Brand>> {
    @Override
    public EntityModel<Brand> toModel(Brand brand){
        return EntityModel.of(brand,
                linkTo(methodOn(BrandController.class).one(brand.getId())).withSelfRel(),
                linkTo(methodOn(BrandController.class).all()).withRel("brands"));
    }
}
