package com.schoolproject.Drugstore.product.type;



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
public class ProductTypeController {
    private final ProductTypeService productTypeService;
    private final ProductTypeModelAssembler productTypeModelAssembler;


    @Autowired
    public ProductTypeController(ProductTypeService productTypeService, ProductTypeModelAssembler productTypeModelAssembler) {
        this.productTypeService = productTypeService;
        this.productTypeModelAssembler = productTypeModelAssembler;
    }

    @GetMapping("/productTypes/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        ProductType productType = productTypeService.getProductTypeById(id);
        return productTypeModelAssembler.toModel(productType);
    }
    @GetMapping("/productTypes")
    CollectionModel<EntityModel<ProductType>> all(){
        List<EntityModel<ProductType>> productTypes = productTypeService.getAllProductTypes().stream().map(productTypeModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(productTypes, linkTo(methodOn(ProductTypeController.class).all()).withSelfRel());
    }

    @PostMapping("/productTypes")
    ResponseEntity<?> newProductType(@RequestBody ProductType productType){
        EntityModel<ProductType> productTypeEntityModel = productTypeModelAssembler.toModel(productTypeService.addProductType(productType));

        return ResponseEntity.created(productTypeEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productTypeEntityModel);
    }

    @PutMapping("productTypes/{id}")
    ResponseEntity<?> replaceProductType(@RequestBody ProductType productType, @PathVariable Integer id){
        ProductType updateProductType = productTypeService.updateProductType(productType, id);
        EntityModel<ProductType> productTypeEntityModel = productTypeModelAssembler.toModel(updateProductType);
        return ResponseEntity.created(productTypeEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productTypeEntityModel);
    }

    @DeleteMapping("productTypes/{id}")
    ResponseEntity<?> deleteProductType(@PathVariable Integer id){
        productTypeService.deleteProductType(id);
        return ResponseEntity.noContent().build();
    }
}
