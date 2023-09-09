package com.schoolproject.Drugstore.payment;


import com.schoolproject.Drugstore.order.order.Order;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PaymentModelAssembler implements RepresentationModelAssembler<Payment, EntityModel<Payment>> {
    @Override
    public EntityModel<Payment> toModel(Payment payment){
        return EntityModel.of(payment,
                linkTo(methodOn(PaymentController.class).one(payment.getId())).withSelfRel(),
                linkTo(methodOn(PaymentController.class).all()).withRel("payments"));
    }
}
