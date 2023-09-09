package com.schoolproject.Drugstore.product.unit;



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
public class ProductUnitController {
    private final ProductUnitService productUnitService;
    private final ProductUnitModelAssembler productUnitModelAssembler;


    @Autowired
    public ProductUnitController(ProductUnitService productUnitService, ProductUnitModelAssembler productUnitModelAssembler) {
        this.productUnitService = productUnitService;
        this.productUnitModelAssembler = productUnitModelAssembler;
    }

    @GetMapping("/productUnits/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        ProductUnit productUnit = productUnitService.getProductUnitById(id);
        return productUnitModelAssembler.toModel(productUnit);
    }
    @GetMapping("/productUnits")
    CollectionModel<EntityModel<ProductUnit>> all(){
        List<EntityModel<ProductUnit>> productUnits = productUnitService.getAllProductUnits().stream().map(productUnitModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(productUnits, linkTo(methodOn(ProductUnitController.class).all()).withSelfRel());
    }

    @PostMapping("/productUnits")
    ResponseEntity<?> newProductUnit(@RequestBody ProductUnit productUnit){
        EntityModel<ProductUnit> productUnitEntityModel = productUnitModelAssembler.toModel(productUnitService.addProductUnit(productUnit));

        return ResponseEntity.created(productUnitEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productUnitEntityModel);
    }

    @PutMapping("productUnits/{id}")
    ResponseEntity<?> replaceProductUnit(@RequestBody ProductUnit productUnit, @PathVariable Integer id){
        ProductUnit updateProductUnit = productUnitService.updateProductUnit(productUnit, id);
        EntityModel<ProductUnit> productUnitEntityModel = productUnitModelAssembler.toModel(updateProductUnit);
        return ResponseEntity.created(productUnitEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productUnitEntityModel);
    }

    @DeleteMapping("productUnits/{id}")
    ResponseEntity<?> deleteProductUnit(@PathVariable Integer id){
        productUnitService.deleteProductUnit(id);
        return ResponseEntity.noContent().build();
    }
}
