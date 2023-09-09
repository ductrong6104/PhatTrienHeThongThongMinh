package com.schoolproject.Drugstore.product.use;



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
public class ProductUseForController {
    private final ProductUseForService productUseForService;
    private final ProductUseForModelAssembler productUseForModelAssembler;


    @Autowired
    public ProductUseForController(ProductUseForService productUseForService, ProductUseForModelAssembler productUseForModelAssembler) {
        this.productUseForService = productUseForService;
        this.productUseForModelAssembler = productUseForModelAssembler;
    }

    @GetMapping("/productUseFors/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        ProductUseFor productUseFor = productUseForService.getProductUseForById(id);
        return productUseForModelAssembler.toModel(productUseFor);
    }
    @GetMapping("/productUseFors")
    CollectionModel<EntityModel<ProductUseFor>> all(){
        List<EntityModel<ProductUseFor>> productUseFors = productUseForService.getAllProductUseFors().stream().map(productUseForModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(productUseFors, linkTo(methodOn(ProductUseForController.class).all()).withSelfRel());
    }

    @PostMapping("/productUseFors")
    ResponseEntity<?> newProductUseFor(@RequestBody ProductUseFor productUseFor){
        EntityModel<ProductUseFor> productUseForEntityModel = productUseForModelAssembler.toModel(productUseForService.addProductUseFor(productUseFor));

        return ResponseEntity.created(productUseForEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productUseForEntityModel);
    }

    @PutMapping("productUseFors/{id}")
    ResponseEntity<?> replaceProductUseFor(@RequestBody ProductUseFor productUseFor, @PathVariable Integer id){
        ProductUseFor updateProductUseFor = productUseForService.updateProductUseFor(productUseFor, id);
        EntityModel<ProductUseFor> productUseForEntityModel = productUseForModelAssembler.toModel(updateProductUseFor);
        return ResponseEntity.created(productUseForEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productUseForEntityModel);
    }

    @DeleteMapping("productUseFors/{id}")
    ResponseEntity<?> deleteProductUseFor(@PathVariable Integer id){
        productUseForService.deleteProductUseFor(id);
        return ResponseEntity.noContent().build();
    }
}
