package com.schoolproject.Drugstore.product.category;



import com.schoolproject.Drugstore.product.group.ProductGroupOperations;
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
@RequestMapping("/productCategorys")
@CrossOrigin
public class ProductCategoryController implements ProductCategoryOperations {
    private final ProductCategoryService productCategoryService;
    private final ProductCategoryModelAssembler productCategoryModelAssembler;
    private final ProductCategoryMapperDto productCategoryMapperDto;


    @Autowired
    public ProductCategoryController(ProductCategoryService productCategoryService, ProductCategoryModelAssembler productCategoryModelAssembler, ProductCategoryMapperDto productCategoryMapperDto) {
        this.productCategoryService = productCategoryService;
        this.productCategoryModelAssembler = productCategoryModelAssembler;
        this.productCategoryMapperDto = productCategoryMapperDto;
    }

    @GetMapping("/{id}")
    public EntityModel<?> one(@PathVariable Integer id){
        ProductCategoryDto productCategoryDto = productCategoryService.getProductById(id);
        return productCategoryModelAssembler.toModel(productCategoryDto);
    }
    @GetMapping("")
    public CollectionModel<EntityModel<ProductCategoryDto>> all(){
        List<EntityModel<ProductCategoryDto>> productCategorys = productCategoryService.getAllProducts().stream().map(productCategoryModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(productCategorys, linkTo(methodOn(ProductCategoryController.class).all()).withSelfRel());
    }

    @PostMapping("")
    public ResponseEntity<?> newProductCategory(@RequestBody ProductCategoryCreationDto productCategoryCreationDto){
        EntityModel<ProductCategoryDto> productCategoryEntityModel = productCategoryModelAssembler.toModel(productCategoryService.addProductCategory(productCategoryCreationDto));
        return ResponseEntity.created(productCategoryEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productCategoryEntityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> replaceProductCategory(@RequestBody ProductCategoryCreationDto productCategoryCreationDto, @PathVariable Integer id){
        ProductCategoryDto updateProductCategory = productCategoryService.updateProductCategory(productCategoryCreationDto, id);
        EntityModel<ProductCategoryDto> productCategoryEntityModel = productCategoryModelAssembler.toModel(updateProductCategory);
        return ResponseEntity.created(productCategoryEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productCategoryEntityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductCategory(@PathVariable Integer id){
        productCategoryService.deleteProductCategory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/loc-danh-muc-theo-loai")
    @Override
    public CollectionModel<EntityModel<ProductCategoryDto>> filterCategoryByType(Integer typeId) {
        List<EntityModel<ProductCategoryDto>> productCategorys = productCategoryService.filterCategoryByType(typeId).stream().map(productCategoryModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(productCategorys, linkTo(methodOn(ProductCategoryController.class).all()).withSelfRel());
    }

    @GetMapping("/loc-danh-muc-theo-nhom")
    @Override
    public CollectionModel<EntityModel<ProductCategoryDto>> filterCategoryByGroup(Integer groupId) {
        List<EntityModel<ProductCategoryDto>> productCategorys = productCategoryService.filterCategoryByGroup(groupId).stream().map(productCategoryModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(productCategorys, linkTo(methodOn(ProductCategoryController.class).all()).withSelfRel());
    }

}
