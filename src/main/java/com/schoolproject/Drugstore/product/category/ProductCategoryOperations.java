package com.schoolproject.Drugstore.product.category;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequestMapping("/productCategorys")
public interface ProductCategoryOperations {
    @GetMapping("/{id}")
    EntityModel<?> one(@PathVariable Integer id);
    @GetMapping("")
    CollectionModel<EntityModel<ProductCategoryDto>> all();

    @PostMapping("")
    ResponseEntity<?> newProductCategory(@RequestBody ProductCategoryCreationDto productCategoryCreationDto);

    @PutMapping("/{id}")
    ResponseEntity<?> replaceProductCategory(@RequestBody ProductCategoryCreationDto productCategoryCreationDto, @PathVariable Integer id);

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteProductCategory(@PathVariable Integer id);

    @GetMapping("/loc-danh-muc-theo-loai")
    CollectionModel<EntityModel<ProductCategoryDto>> filterCategoryByType(@RequestParam("f") Integer typeId);
    @GetMapping("/loc-danh-muc-theo-nhom")
    CollectionModel<EntityModel<ProductCategoryDto>> filterCategoryByGroup(@RequestParam("f") Integer groupId);
}
