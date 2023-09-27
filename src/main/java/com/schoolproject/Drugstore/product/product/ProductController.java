package com.schoolproject.Drugstore.product.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping("/products")
public class ProductController implements ProductOperations {
    private final ProductService productService;
    private final ProductModelAssembler productModelAssembler;
    private final ProductMapperDto productMapperDto;


    @Autowired
    public ProductController(ProductService productService, ProductModelAssembler productModelAssembler, ProductMapperDto productMapperDto) {
        this.productService = productService;
        this.productModelAssembler = productModelAssembler;
        this.productMapperDto = productMapperDto;
    }

    @GetMapping("/{id}")
    public EntityModel<?> one(@PathVariable Integer id){
        ProductDto productDto = productService.getProductById(id);
        return productModelAssembler.toModel(productDto);
    }

    @GetMapping("")
    public CollectionModel<EntityModel<ProductDto>> all(){
        List<EntityModel<ProductDto>> products = productService.getAllProducts().stream().map(productModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(products, linkTo(methodOn(ProductController.class).all()).withSelfRel());
    }

    @PostMapping("")
    public ResponseEntity<?> newProduct(@RequestBody ProductCreationDto productCreationDto){
        EntityModel<ProductDto> productEntityModel = productModelAssembler.toModel(productService.addProduct(productCreationDto));
        return ResponseEntity.created(productEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productEntityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> replaceProduct(@RequestBody ProductCreationDto productCreationDto, @PathVariable Integer id){
        ProductDto updateProduct = productService.updateProduct(productCreationDto, id);
        EntityModel<ProductDto> productEntityModel = productModelAssembler.toModel(updateProduct);
        return ResponseEntity.created(productEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productEntityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/tim-kiem")
    public CollectionModel<EntityModel<ProductDto>> searchProducts(@RequestParam("s") String text){
        text = "%" + text + "%";
        List<EntityModel<ProductDto>> products = productService.searchProductsLikeText(text).stream().map(productModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(products, linkTo(methodOn(ProductController.class).all()).withSelfRel());
    }

    // chua them chuc nang hide parameter de bao mat
    @GetMapping("/loc-san-pham-theo-loai")
    public CollectionModel<EntityModel<ProductDto>> filterProductsByType(@RequestParam("f") Integer typeId){
        List<EntityModel<ProductDto>> products = productService.filterProductsByType(typeId).stream().map(productModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(products, linkTo(methodOn(ProductController.class).all()).withSelfRel());
    }
    @GetMapping("/loc-san-pham-theo-nhom")
    @Override
    public CollectionModel<EntityModel<ProductDto>> filterProductsByGroup(Integer groupId) {
        List<EntityModel<ProductDto>> products = productService.filterProductsByGroup(groupId).stream().map(productModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(products, linkTo(methodOn(ProductController.class).all()).withSelfRel());
    }
    @GetMapping("/loc-san-pham-theo-danh-muc")
    @Override
    public CollectionModel<EntityModel<ProductDto>> filterProductsByCategory(Integer categoryId) {
        List<EntityModel<ProductDto>> products = productService.filterProductsByCategory(categoryId).stream().map(productModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(products, linkTo(methodOn(ProductController.class).all()).withSelfRel());
    }
    @GetMapping("/loc-san-pham-theo-thuong-hieu")
    @Override
    public CollectionModel<EntityModel<ProductDto>> filterProductsByBrand(Integer brandId) {
        List<EntityModel<ProductDto>> products = productService.filterProductsByBrand(brandId).stream().map(productModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(products, linkTo(methodOn(ProductController.class).all()).withSelfRel());
    }
    @GetMapping("/loc-san-pham-theo-doi-tuong-su-dung")
    @Override
    public CollectionModel<EntityModel<ProductDto>> filterProductsByProductUseFor(Integer productUseForId) {
        List<EntityModel<ProductDto>> products = productService.filterProductsByProductUseFor(productUseForId).stream().map(productModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(products, linkTo(methodOn(ProductController.class).all()).withSelfRel());
    }
    @GetMapping("/loc-san-pham-theo-doi-tuong-chi-dinh")
    @Override
    public CollectionModel<EntityModel<ProductDto>> filterProductsByProductSpecifyFor(Integer productSpecifyForId) {
        List<EntityModel<ProductDto>> products = productService.filterProductsByProductSpecifyFor(productSpecifyForId).stream().map(productModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(products, linkTo(methodOn(ProductController.class).all()).withSelfRel());
    }
    @GetMapping("/loc-san-pham-theo-gia")
    @Override
    public CollectionModel<EntityModel<ProductDto>> filterProductsByPrice(BigDecimal priceFrom, BigDecimal priceTo) {
        return null;
    }
}
