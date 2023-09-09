package com.schoolproject.Drugstore.product.specify;



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
public class ProductSpecifyForController {
    private final ProductSpecifyForService productSpecifyForService;
    private final ProductSpecifyForModelAssembler productSpecifyForModelAssembler;


    @Autowired
    public ProductSpecifyForController(ProductSpecifyForService productSpecifyForService, ProductSpecifyForModelAssembler productSpecifyForModelAssembler) {
        this.productSpecifyForService = productSpecifyForService;
        this.productSpecifyForModelAssembler = productSpecifyForModelAssembler;
    }

    @GetMapping("/productSpecifyFors/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        ProductSpecifyFor productSpecifyFor = productSpecifyForService.getProductSpecifyForById(id);
        return productSpecifyForModelAssembler.toModel(productSpecifyFor);
    }
    @GetMapping("/productSpecifyFors")
    CollectionModel<EntityModel<ProductSpecifyFor>> all(){
        List<EntityModel<ProductSpecifyFor>> productSpecifyFors = productSpecifyForService.getAllProductSpecifyFors().stream().map(productSpecifyForModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(productSpecifyFors, linkTo(methodOn(ProductSpecifyForController.class).all()).withSelfRel());
    }

    @PostMapping("/productSpecifyFors")
    ResponseEntity<?> newProductSpecifyFor(@RequestBody ProductSpecifyFor productSpecifyFor){
        EntityModel<ProductSpecifyFor> productSpecifyForEntityModel = productSpecifyForModelAssembler.toModel(productSpecifyForService.addProductSpecifyFor(productSpecifyFor));

        return ResponseEntity.created(productSpecifyForEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productSpecifyForEntityModel);
    }

    @PutMapping("productSpecifyFors/{id}")
    ResponseEntity<?> replaceProductSpecifyFor(@RequestBody ProductSpecifyFor productSpecifyFor, @PathVariable Integer id){
        ProductSpecifyFor updateProductSpecifyFor = productSpecifyForService.updateProductSpecifyFor(productSpecifyFor, id);
        EntityModel<ProductSpecifyFor> productSpecifyForEntityModel = productSpecifyForModelAssembler.toModel(updateProductSpecifyFor);
        return ResponseEntity.created(productSpecifyForEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productSpecifyForEntityModel);
    }

    @DeleteMapping("productSpecifyFors/{id}")
    ResponseEntity<?> deleteProductSpecifyFor(@PathVariable Integer id){
        productSpecifyForService.deleteProductSpecifyFor(id);
        return ResponseEntity.noContent().build();
    }
}
