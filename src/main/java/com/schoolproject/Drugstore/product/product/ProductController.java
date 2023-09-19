package com.schoolproject.Drugstore.product.product;


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
ProductController {
    private final ProductService productService;
    private final ProductModelAssembler productModelAssembler;
    private final ProductMapperDto productMapperDto;


    @Autowired
    public ProductController(ProductService productService, ProductModelAssembler productModelAssembler, ProductMapperDto productMapperDto) {
        this.productService = productService;
        this.productModelAssembler = productModelAssembler;
        this.productMapperDto = productMapperDto;
    }

    @GetMapping("/products/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        ProductDto productDto = productService.getProductById(id);
        return productModelAssembler.toModel(productDto);
    }
    @GetMapping("/products")
    CollectionModel<EntityModel<ProductDto>> all(){
        List<EntityModel<ProductDto>> products = productService.getAllProducts().stream().map(productModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(products, linkTo(methodOn(ProductController.class).all()).withSelfRel());
    }

    @PostMapping("/products")
    ResponseEntity<?> newProduct(@RequestBody ProductCreationDto productCreationDto){
        EntityModel<ProductDto> productEntityModel = productModelAssembler.toModel(productService.addProduct(productCreationDto));
        return ResponseEntity.created(productEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productEntityModel);
    }

    @PutMapping("products/{id}")
    ResponseEntity<?> replaceProduct(@RequestBody ProductCreationDto productCreationDto, @PathVariable Integer id){
        ProductDto updateProduct = productService.updateProduct(productCreationDto, id);
        EntityModel<ProductDto> productEntityModel = productModelAssembler.toModel(updateProduct);
        return ResponseEntity.created(productEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productEntityModel);
    }

    @DeleteMapping("products/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
