package com.schoolproject.Drugstore.product.dosageform;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductDosageFormModelAssembler implements RepresentationModelAssembler<ProductDosageFormDto, EntityModel<ProductDosageFormDto>> {
    @Override
    public EntityModel<ProductDosageFormDto> toModel(ProductDosageFormDto productDosageForm){
        return EntityModel.of(productDosageForm,
                linkTo(methodOn(ProductDosageFormController.class).one(productDosageForm.getId())).withSelfRel(),
                linkTo(methodOn(ProductDosageFormController.class).all()).withRel("products"));
    }
}
