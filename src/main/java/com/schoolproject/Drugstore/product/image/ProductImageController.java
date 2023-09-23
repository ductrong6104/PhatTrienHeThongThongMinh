package com.schoolproject.Drugstore.product.image;



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
public class ProductImageController {
    private final ProductImageService productImageService;
    private final ProductImageModelAssembler productImageModelAssembler;


    @Autowired
    public ProductImageController(ProductImageService productImageService, ProductImageModelAssembler productImageModelAssembler) {
        this.productImageService = productImageService;
        this.productImageModelAssembler = productImageModelAssembler;
    }

    @GetMapping("/productImages/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        ProductImage productImage = productImageService.getProductImageById(id);
        return productImageModelAssembler.toModel(productImage);
    }
    @GetMapping("/productImages")
    CollectionModel<EntityModel<ProductImage>> all(){
        List<EntityModel<ProductImage>> productImages = productImageService.getAllProductImages().stream().map(productImageModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(productImages, linkTo(methodOn(ProductImageController.class).all()).withSelfRel());
    }

    @PostMapping("/productImages")
    ResponseEntity<?> newProductImage(@RequestBody ProductImage productImage){
        EntityModel<ProductImage> productImageEntityModel = productImageModelAssembler.toModel(productImageService.addProductImage(productImage));

        return ResponseEntity.created(productImageEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productImageEntityModel);
    }

    @PutMapping("productImages/{id}")
    ResponseEntity<?> replaceProductImage(@RequestBody ProductImage productImage, @PathVariable Integer id){
        ProductImage updateProductImage = productImageService.updateProductImage(productImage, id);
        EntityModel<ProductImage> productImageEntityModel = productImageModelAssembler.toModel(updateProductImage);
        return ResponseEntity.created(productImageEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productImageEntityModel);
    }

    @DeleteMapping("productImages/{id}")
    ResponseEntity<?> deleteProductImage(@PathVariable Integer id){
        productImageService.deleteProductImage(id);
        return ResponseEntity.noContent().build();
    }
}
