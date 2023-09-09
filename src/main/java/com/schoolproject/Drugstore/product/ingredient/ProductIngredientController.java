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
public class ProductIngredientController {
    private final ProductIngredientService productIngredientService;
    private final ProductIngredientModelAssembler productIngredientModelAssembler;


    @Autowired
    public ProductIngredientController(ProductIngredientService productIngredientService, ProductIngredientModelAssembler productIngredientModelAssembler) {
        this.productIngredientService = productIngredientService;
        this.productIngredientModelAssembler = productIngredientModelAssembler;
    }

    @GetMapping("/productIngredients/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        ProductIngredient productIngredient = productIngredientService.getProductIngredientById(id);
        return productIngredientModelAssembler.toModel(productIngredient);
    }
    @GetMapping("/productIngredients")
    CollectionModel<EntityModel<ProductIngredient>> all(){
        List<EntityModel<ProductIngredient>> productIngredients = productIngredientService.getAllProductIngredients().stream().map(productIngredientModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(productIngredients, linkTo(methodOn(ProductIngredientController.class).all()).withSelfRel());
    }

    @PostMapping("/productIngredients")
    ResponseEntity<?> newProductIngredient(@RequestBody ProductIngredient productIngredient){
        EntityModel<ProductIngredient> productIngredientEntityModel = productIngredientModelAssembler.toModel(productIngredientService.addProductIngredient(productIngredient));

        return ResponseEntity.created(productIngredientEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productIngredientEntityModel);
    }

    @PutMapping("productIngredients/{id}")
    ResponseEntity<?> replaceProductIngredient(@RequestBody ProductIngredient productIngredient, @PathVariable Integer id){
        ProductIngredient updateProductIngredient = productIngredientService.updateProductIngredient(productIngredient, id);
        EntityModel<ProductIngredient> productIngredientEntityModel = productIngredientModelAssembler.toModel(updateProductIngredient);
        return ResponseEntity.created(productIngredientEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productIngredientEntityModel);
    }

    @DeleteMapping("productIngredients/{id}")
    ResponseEntity<?> deleteProductIngredient(@PathVariable Integer id){
        productIngredientService.deleteProductIngredient(id);
        return ResponseEntity.noContent().build();
    }
}
