package com.schoolproject.Drugstore.nation;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class NationModelAssembler implements RepresentationModelAssembler<Nation, EntityModel<Nation>> {
    @Override
    public EntityModel<Nation> toModel(Nation customer){
        return EntityModel.of(customer,
                linkTo(methodOn(NationController.class).one(customer.getId())).withSelfRel(),
                linkTo(methodOn(NationController.class).all()).withRel("customers"));
    }
}
