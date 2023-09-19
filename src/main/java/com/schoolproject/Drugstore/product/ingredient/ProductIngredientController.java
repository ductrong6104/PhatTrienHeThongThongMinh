package com.schoolproject.Drugstore.product.ingredient;


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
ProductIngredientController {
    private final ProductIngredientService productIngredientService;
    private final ProductIngredientModelAssembler productIngredientModelAssembler;
    private final ProductIngredientMapperDto productIngredientMapperDto;


    @Autowired
    public ProductIngredientController(ProductIngredientService productIngredientService, ProductIngredientModelAssembler productIngredientModelAssembler, ProductIngredientMapperDto productIngredientMapperDto) {
        this.productIngredientService = productIngredientService;
        this.productIngredientModelAssembler = productIngredientModelAssembler;
        this.productIngredientMapperDto = productIngredientMapperDto;
    }

    @GetMapping("/productIngredients/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        ProductIngredientDto productIngredientDto = productIngredientService.getProductIngredientById(id);
        return productIngredientModelAssembler.toModel(productIngredientDto);
    }
    @GetMapping("/productIngredients")
    CollectionModel<EntityModel<ProductIngredientDto>> all(){
        List<EntityModel<ProductIngredientDto>> productIngredients = productIngredientService.getAllProductIngredients().stream().map(productIngredientModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(productIngredients, linkTo(methodOn(ProductIngredientController.class).all()).withSelfRel());
    }

    @PostMapping("/productIngredients")
    ResponseEntity<?> newProductIngredient(@RequestBody ProductIngredientCreationDto productIngredientCreationDto){
        EntityModel<ProductIngredientDto> productIngredientEntityModel = productIngredientModelAssembler.toModel(productIngredientService.addProductIngredient(productIngredientCreationDto));
        return ResponseEntity.created(productIngredientEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productIngredientEntityModel);
    }

    @PutMapping("productIngredients/{id}")
    ResponseEntity<?> replaceProductIngredient(@RequestBody ProductIngredientCreationDto productIngredientCreationDto, @PathVariable Integer id){
        ProductIngredientDto updateProduct = productIngredientService.updateProductIngredient(productIngredientCreationDto, id);
        EntityModel<ProductIngredientDto> productIngredientEntityModel = productIngredientModelAssembler.toModel(updateProduct);
        return ResponseEntity.created(productIngredientEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productIngredientEntityModel);
    }

    @DeleteMapping("productIngredients/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable Integer id){
        productIngredientService.deleteProductIngredient(id);
        return ResponseEntity.noContent().build();
    }
}
