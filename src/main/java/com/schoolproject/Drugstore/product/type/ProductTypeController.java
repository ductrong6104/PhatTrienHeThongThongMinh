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
@CrossOrigin
public class ProductTypeController {
    private final ProductTypeService productTypeService;
    private final ProductTypeModelAssembler productTypeModelAssembler;
    private final ProductTypeMapperDto productTypeMapperDto;


    @Autowired
    public ProductTypeController(ProductTypeService productTypeService, ProductTypeModelAssembler productTypeModelAssembler, ProductTypeMapperDto productTypeMapperDto) {
        this.productTypeService = productTypeService;
        this.productTypeModelAssembler = productTypeModelAssembler;
        this.productTypeMapperDto = productTypeMapperDto;
    }

    @GetMapping("/productTypes/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        ProductTypeDto productTypeDto = productTypeService.getProductById(id);
        return productTypeModelAssembler.toModel(productTypeDto);
    }
    @GetMapping("/productTypes")
    CollectionModel<EntityModel<ProductTypeDto>> all(){
        List<EntityModel<ProductTypeDto>> productTypes = productTypeService.getAllProducts().stream().map(productTypeModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(productTypes, linkTo(methodOn(ProductTypeController.class).all()).withSelfRel());
    }

    @PostMapping("/productTypes")
    ResponseEntity<?> newProductType(@RequestBody ProductTypeCreationDto productTypeCreationDto){
        EntityModel<ProductTypeDto> productTypeEntityModel = productTypeModelAssembler.toModel(productTypeService.addProductType(productTypeCreationDto));
        return ResponseEntity.created(productTypeEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productTypeEntityModel);
    }

    @PutMapping("productTypes/{id}")
    ResponseEntity<?> replaceProductType(@RequestBody ProductTypeCreationDto productTypeCreationDto, @PathVariable Integer id){
        ProductTypeDto updateProductType = productTypeService.updateProductType(productTypeCreationDto, id);
        EntityModel<ProductTypeDto> productTypeEntityModel = productTypeModelAssembler.toModel(updateProductType);
        return ResponseEntity.created(productTypeEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productTypeEntityModel);
    }

    @DeleteMapping("productTypes/{id}")
    ResponseEntity<?> deleteProductType(@PathVariable Integer id){
        productTypeService.deleteProductType(id);
        return ResponseEntity.noContent().build();
    }


}
