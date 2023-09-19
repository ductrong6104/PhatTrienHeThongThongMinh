package com.schoolproject.Drugstore.order.status;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderStatusModelAssembler implements RepresentationModelAssembler<OrderStatusDto, EntityModel<OrderStatusDto>> {
    @Override
    public EntityModel<OrderStatusDto> toModel(OrderStatusDto orderStatusDto){
        return EntityModel.of(orderStatusDto,
                linkTo(methodOn(OrderStatusController.class).one(orderStatusDto.getId())).withSelfRel(),
                linkTo(methodOn(OrderStatusController.class).all()).withRel("orderStatusDtos"));
    }
}
