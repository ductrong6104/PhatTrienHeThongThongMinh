package com.schoolproject.Drugstore.product.category;




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
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;
    private final ProductCategoryModelAssembler productCategoryModelAssembler;


    @Autowired
    public ProductCategoryController(ProductCategoryService productCategoryService, ProductCategoryModelAssembler productCategoryModelAssembler) {
        this.productCategoryService = productCategoryService;
        this.productCategoryModelAssembler = productCategoryModelAssembler;
    }

    @GetMapping("/productCategorys/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        ProductCategory productCategory = productCategoryService.getProductCategoryById(id);
        return productCategoryModelAssembler.toModel(productCategory);
    }
    @GetMapping("/productCategorys")
    CollectionModel<EntityModel<ProductCategory>> all(){
        List<EntityModel<ProductCategory>> productCategorys = productCategoryService.getAllProductCategorys().stream().map(productCategoryModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(productCategorys, linkTo(methodOn(ProductCategoryController.class).all()).withSelfRel());
    }

    @PostMapping("/productCategorys")
    ResponseEntity<?> newProductCategory(@RequestBody ProductCategory productCategory){
        EntityModel<ProductCategory> productCategoryEntityModel = productCategoryModelAssembler.toModel(productCategoryService.addProductCategory(productCategory));
        return ResponseEntity.created(productCategoryEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productCategoryEntityModel);
    }

    @PutMapping("productCategorys/{id}")
    ResponseEntity<?> replaceProductCategory(@RequestBody ProductCategory productCategory, @PathVariable Integer id){
        ProductCategory updateProductCategory = productCategoryService.updateProductCategory(productCategory, id);
        EntityModel<ProductCategory> productCategoryEntityModel = productCategoryModelAssembler.toModel(updateProductCategory);
        return ResponseEntity.created(productCategoryEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productCategoryEntityModel);
    }

    @DeleteMapping("productCategorys/{id}")
    ResponseEntity<?> deleteProductCategory(@PathVariable Integer id){
        productCategoryService.deleteProductCategory(id);
        return ResponseEntity.noContent().build();
    }
}
