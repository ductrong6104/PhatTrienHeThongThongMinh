package com.schoolproject.Drugstore.product.rate;



import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductRateModelAssembler implements RepresentationModelAssembler<ProductRate, EntityModel<ProductRate>> {
    @Override
    public EntityModel<ProductRate> toModel(ProductRate productRate){
        return EntityModel.of(productRate,
                linkTo(methodOn(ProductRateController.class).one(productRate.getId())).withSelfRel(),
                linkTo(methodOn(ProductRateController.class).all()).withRel("productRates"));
    }
}
