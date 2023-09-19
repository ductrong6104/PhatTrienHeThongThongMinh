package com.schoolproject.Drugstore.payment;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PaymentModelAssembler implements RepresentationModelAssembler<PaymentDto, EntityModel<PaymentDto>> {
    @Override
    public EntityModel<PaymentDto> toModel(PaymentDto productUnitDto){
        return EntityModel.of(productUnitDto,
                linkTo(methodOn(PaymentController.class).one(productUnitDto.getId())).withSelfRel(),
                linkTo(methodOn(PaymentController.class).all()).withRel("productUnits"));
    }
}
