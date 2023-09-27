package com.schoolproject.Drugstore.product.product;

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

@RequestMapping("/products")
public interface ProductOperations {
    @GetMapping("/{id}")
    EntityModel<?> one(@PathVariable Integer id);
    @GetMapping("")
    CollectionModel<EntityModel<ProductDto>> all();

    @PostMapping("")
    ResponseEntity<?> newProduct(@RequestBody ProductCreationDto productCreationDto);

    @PutMapping("/{id}")
    ResponseEntity<?> replaceProduct(@RequestBody ProductCreationDto productCreationDto, @PathVariable Integer id);
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable Integer id);

    // chuc nang tren thanh tim kiem thuoc
    @GetMapping("/tim-kiem")
    CollectionModel<EntityModel<ProductDto>> searchProducts(@RequestParam("s") String text);

    // chua them chuc nang hide parameter de bao mat
    // giong voi chuc nang  loc-san-pham-lien-quan: lien quan theo danh muc
    @GetMapping("/loc-san-pham-theo-loai")
    CollectionModel<EntityModel<ProductDto>> filterProductsByType(@RequestParam("f") Integer typeId);

    @GetMapping("/loc-san-pham-theo-nhom")
    CollectionModel<EntityModel<ProductDto>> filterProductsByGroup(@RequestParam("f") Integer groupId);
    @GetMapping("/loc-san-pham-theo-danh-muc")
    CollectionModel<EntityModel<ProductDto>> filterProductsByCategory(@RequestParam("f") Integer categoryId);

    @GetMapping("/loc-san-pham-theo-thuong-hieu")
    CollectionModel<EntityModel<ProductDto>> filterProductsByBrand(@RequestParam("f") Integer brandId);

    @GetMapping("/loc-san-pham-theo-doi-tuong-su-dung")
    CollectionModel<EntityModel<ProductDto>> filterProductsByProductUseFor(@RequestParam("f") Integer productUseForId);
    @GetMapping("/loc-san-pham-theo-doi-tuong-chi-dinh")
    CollectionModel<EntityModel<ProductDto>> filterProductsByProductSpecifyFor(@RequestParam("f") Integer productSpecifyForId);
    // chua lam
    @GetMapping("/loc-san-pham-theo-gia")
    CollectionModel<EntityModel<ProductDto>> filterProductsByPrice(@RequestParam("priceFrom") BigDecimal priceFrom, @RequestParam("priceTo") BigDecimal priceTo);

}
