package com.schoolproject.Drugstore.nation;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping()
public class
NationController {
    private final NationService nationService;
    private final NationModelAssembler nationModelAssembler;
    private final NationMapperDto nationMapperDto;


    @Autowired
    public NationController(NationService nationService, NationModelAssembler nationModelAssembler, NationMapperDto nationMapperDto) {
        this.nationService = nationService;
        this.nationModelAssembler = nationModelAssembler;
        this.nationMapperDto = nationMapperDto;
    }

    @GetMapping("/nations/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        NationDto nationDto = nationService.getProductById(id);
        return nationModelAssembler.toModel(nationDto);
    }

    @GetMapping("/nationsId")
    CollectionModel<EntityModel<NationDto>> getNationById(@RequestParam("id") Integer id){
        System.out.println("id nation: " + id);
        List<EntityModel<NationDto>> nations = nationService.getAllProducts().stream().map(nationModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(nations, linkTo(methodOn(NationController.class).all()).withSelfRel());
    }
    @GetMapping("/nations")
    CollectionModel<EntityModel<NationDto>> all(){
        List<EntityModel<NationDto>> nations = nationService.getAllProducts().stream().map(nationModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(nations, linkTo(methodOn(NationController.class).all()).withSelfRel());
    }

    @PostMapping("/nations")
    ResponseEntity<?> newNation(@RequestBody NationCreationDto nationCreationDto){
        EntityModel<NationDto> nationEntityModel = nationModelAssembler.toModel(nationService.addNation(nationCreationDto));
        return ResponseEntity.created(nationEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(nationEntityModel);
    }

    @PutMapping("nations/{id}")
    ResponseEntity<?> replaceNation(@RequestBody NationCreationDto nationCreationDto, @PathVariable Integer id){
        NationDto updateNation = nationService.updateNation(nationCreationDto, id);
        EntityModel<NationDto> nationEntityModel = nationModelAssembler.toModel(updateNation);
        return ResponseEntity.created(nationEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(nationEntityModel);
    }

    @DeleteMapping("nations/{id}")
    ResponseEntity<?> deleteNation(@PathVariable Integer id){
        nationService.deleteNation(id);
        return ResponseEntity.noContent().build();
    }
}
