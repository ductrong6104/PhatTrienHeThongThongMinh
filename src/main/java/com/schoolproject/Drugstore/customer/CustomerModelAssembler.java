package com.schoolproject.Drugstore.customer;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CustomerModelAssembler implements RepresentationModelAssembler<CustomerDto, EntityModel<CustomerDto>> {
    @Override
    public EntityModel<CustomerDto> toModel(CustomerDto customerDto){
        return EntityModel.of(customerDto,
                linkTo(methodOn(CustomerController.class).one(customerDto.getId())).withSelfRel(),
                linkTo(methodOn(CustomerController.class).all()).withRel("customerDtos"));
    }
}
