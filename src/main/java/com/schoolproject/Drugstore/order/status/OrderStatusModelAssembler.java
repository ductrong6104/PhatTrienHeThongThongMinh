package com.schoolproject.Drugstore.order.status;


import com.schoolproject.Drugstore.order.order.Order;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderStatusModelAssembler implements RepresentationModelAssembler<OrderStatus, EntityModel<OrderStatus>> {
    @Override
    public EntityModel<OrderStatus> toModel(OrderStatus orderStatus){
        return EntityModel.of(orderStatus,
                linkTo(methodOn(OrderStatusController.class).one(orderStatus.getId())).withSelfRel(),
                linkTo(methodOn(OrderStatusController.class).all()).withRel("orderStatus"));
    }
}
