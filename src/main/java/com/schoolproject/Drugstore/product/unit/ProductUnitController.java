package com.schoolproject.Drugstore.product.unit;


import com.schoolproject.Drugstore.product.unit.*;
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
@CrossOrigin
public class ProductUnitController {
    private final ProductUnitService productUnitService;
    private final ProductUnitModelAssembler productUnitModelAssembler;
    private final ProductUnitMapperDto productUnitMapperDto;


    @Autowired
    public ProductUnitController(ProductUnitService productUnitService, ProductUnitModelAssembler productUnitModelAssembler, ProductUnitMapperDto productUnitMapperDto) {
        this.productUnitService = productUnitService;
        this.productUnitModelAssembler = productUnitModelAssembler;
        this.productUnitMapperDto = productUnitMapperDto;
    }

    @GetMapping("/productUnits/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        ProductUnitDto productUnitDto = productUnitService.getProductUnitById(id);
        return productUnitModelAssembler.toModel(productUnitDto);
    }
    @GetMapping("/productUnits")
    CollectionModel<EntityModel<ProductUnitDto>> all(){
        List<EntityModel<ProductUnitDto>> productUnits = productUnitService.getAllProductUnits().stream().map(productUnitModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(productUnits, linkTo(methodOn(ProductUnitController.class).all()).withSelfRel());
    }

    @PostMapping("/productUnits")
    ResponseEntity<?> newProductUnit(@RequestBody ProductUnitCreationDto productUnitCreationDto){
        EntityModel<ProductUnitDto> productUnitEntityModel = productUnitModelAssembler.toModel(productUnitService.addProductUnit(productUnitCreationDto));
        return ResponseEntity.created(productUnitEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productUnitEntityModel);
    }

    @PutMapping("productUnits/{id}")
    ResponseEntity<?> replaceProductUnit(@RequestBody ProductUnitCreationDto productUnitCreationDto, @PathVariable Integer id){
        ProductUnitDto updateProduct = productUnitService.updateProductUnit(productUnitCreationDto, id);
        EntityModel<ProductUnitDto> productUnitEntityModel = productUnitModelAssembler.toModel(updateProduct);
        return ResponseEntity.created(productUnitEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productUnitEntityModel);
    }

    @DeleteMapping("productUnits/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable Integer id){
        productUnitService.deleteProductUnit(id);
        return ResponseEntity.noContent().build();
    }
}
