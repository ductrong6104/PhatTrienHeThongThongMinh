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
public class NationController {
    private final NationService nationService;
    private final NationModelAssembler nationModelAssembler;


    @Autowired
    public NationController(NationService nationService, NationModelAssembler nationModelAssembler) {
        this.nationService = nationService;
        this.nationModelAssembler = nationModelAssembler;
    }

    @GetMapping("/nations/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        Nation nation = nationService.getNationById(id);
        return nationModelAssembler.toModel(nation);
    }
    @GetMapping("/nations")
    CollectionModel<EntityModel<Nation>> all(){
        List<EntityModel<Nation>> nations = nationService.getAllNations().stream().map(nationModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(nations, linkTo(methodOn(NationController.class).all()).withSelfRel());
    }

    @PostMapping("/nations")
    ResponseEntity<?> newNation(@RequestBody Nation nation){
        EntityModel<Nation> nationEntityModel = nationModelAssembler.toModel(nationService.addNation(nation));
        return ResponseEntity.created(nationEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(nationEntityModel);
    }

    @PutMapping("nations/{id}")
    ResponseEntity<?> replaceNation(@RequestBody Nation nation, @PathVariable Integer id){
        Nation updateNation = nationService.updateNation(nation, id);
        EntityModel<Nation> nationEntityModel = nationModelAssembler.toModel(updateNation);
        return ResponseEntity.created(nationEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(nationEntityModel);
    }

    @DeleteMapping("nations/{id}")
    ResponseEntity<?> deleteNation(@PathVariable Integer id){
        nationService.deleteNation(id);
        return ResponseEntity.noContent().build();
    }
}
