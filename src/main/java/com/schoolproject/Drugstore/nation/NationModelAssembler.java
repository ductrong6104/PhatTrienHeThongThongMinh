package com.schoolproject.Drugstore.nation;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class NationModelAssembler implements RepresentationModelAssembler<NationDto, EntityModel<NationDto>> {
    @Override
    public EntityModel<NationDto> toModel(NationDto nationDto){
        return EntityModel.of(nationDto,
                linkTo(methodOn(NationController.class).one(nationDto.getId())).withSelfRel(),
                linkTo(methodOn(NationController.class).all()).withRel("products"));
    }
}
