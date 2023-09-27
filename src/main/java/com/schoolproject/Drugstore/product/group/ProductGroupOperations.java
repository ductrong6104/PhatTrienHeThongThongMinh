package com.schoolproject.Drugstore.product.group;


import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequestMapping("/productGroups")
public interface ProductGroupOperations {
    @GetMapping("/{id}")
    EntityModel<?> one(@PathVariable Integer id);
    @GetMapping("")
    CollectionModel<EntityModel<ProductGroupDto>> all();

    @PostMapping("")
    ResponseEntity<?> newProductGroup(@RequestBody ProductGroupCreationDto productGroupCreationDto);

    @PutMapping("/{id}")
    ResponseEntity<?> replaceProductGroup(@RequestBody ProductGroupCreationDto productGroupCreationDto, @PathVariable Integer id);

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteProductGroup(@PathVariable Integer id);

    @GetMapping("/typeId")
    CollectionModel<EntityModel<ProductGroupDto>> getProductGroupsByTypeId(@RequestParam Integer typeId);


}
