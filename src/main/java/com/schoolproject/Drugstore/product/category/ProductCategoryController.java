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
public class
ProductCategoryController {
    private final ProductCategoryService productCategoryService;
    private final ProductCategoryModelAssembler productCategoryModelAssembler;
    private final ProductCategoryMapperDto productCategoryMapperDto;


    @Autowired
    public ProductCategoryController(ProductCategoryService productCategoryService, ProductCategoryModelAssembler productCategoryModelAssembler, ProductCategoryMapperDto productCategoryMapperDto) {
        this.productCategoryService = productCategoryService;
        this.productCategoryModelAssembler = productCategoryModelAssembler;
        this.productCategoryMapperDto = productCategoryMapperDto;
    }

    @GetMapping("/productCategorys/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        ProductCategoryDto productCategoryDto = productCategoryService.getProductById(id);
        return productCategoryModelAssembler.toModel(productCategoryDto);
    }
    @GetMapping("/productCategorys")
    CollectionModel<EntityModel<ProductCategoryDto>> all(){
        List<EntityModel<ProductCategoryDto>> productCategorys = productCategoryService.getAllProducts().stream().map(productCategoryModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(productCategorys, linkTo(methodOn(ProductCategoryController.class).all()).withSelfRel());
    }

    @PostMapping("/productCategorys")
    ResponseEntity<?> newProductCategory(@RequestBody ProductCategoryCreationDto productCategoryCreationDto){
        EntityModel<ProductCategoryDto> productCategoryEntityModel = productCategoryModelAssembler.toModel(productCategoryService.addProductCategory(productCategoryCreationDto));
        return ResponseEntity.created(productCategoryEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productCategoryEntityModel);
    }

    @PutMapping("productCategorys/{id}")
    ResponseEntity<?> replaceProductCategory(@RequestBody ProductCategoryCreationDto productCategoryCreationDto, @PathVariable Integer id){
        ProductCategoryDto updateProductCategory = productCategoryService.updateProductCategory(productCategoryCreationDto, id);
        EntityModel<ProductCategoryDto> productCategoryEntityModel = productCategoryModelAssembler.toModel(updateProductCategory);
        return ResponseEntity.created(productCategoryEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productCategoryEntityModel);
    }

    @DeleteMapping("productCategorys/{id}")
    ResponseEntity<?> deleteProductCategory(@PathVariable Integer id){
        productCategoryService.deleteProductCategory(id);
        return ResponseEntity.noContent().build();
    }
}
