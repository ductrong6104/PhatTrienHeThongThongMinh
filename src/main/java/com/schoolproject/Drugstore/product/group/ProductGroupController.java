package com.schoolproject.Drugstore.product.group;



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
public class ProductGroupController {
    private final ProductGroupService productGroupService;
    private final ProductGroupModelAssembler productGroupModelAssembler;


    @Autowired
    public ProductGroupController(ProductGroupService productGroupService, ProductGroupModelAssembler productGroupModelAssembler) {
        this.productGroupService = productGroupService;
        this.productGroupModelAssembler = productGroupModelAssembler;
    }

    @GetMapping("/productGroups/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        ProductGroup productGroup = productGroupService.getProductGroupById(id);
        return productGroupModelAssembler.toModel(productGroup);
    }
    @GetMapping("/productGroups")
    CollectionModel<EntityModel<ProductGroup>> all(){
        List<EntityModel<ProductGroup>> productGroups = productGroupService.getAllProductGroups().stream().map(productGroupModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(productGroups, linkTo(methodOn(ProductGroupController.class).all()).withSelfRel());
    }

    @PostMapping("/productGroups")
    ResponseEntity<?> newProductGroup(@RequestBody ProductGroup productGroup){
        EntityModel<ProductGroup> productGroupEntityModel = productGroupModelAssembler.toModel(productGroupService.addProductGroup(productGroup));

        return ResponseEntity.created(productGroupEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productGroupEntityModel);
    }

    @PutMapping("productGroups/{id}")
    ResponseEntity<?> replaceProductGroup(@RequestBody ProductGroup productGroup, @PathVariable Integer id){
        ProductGroup updateProductGroup = productGroupService.updateProductGroup(productGroup, id);
        EntityModel<ProductGroup> productGroupEntityModel = productGroupModelAssembler.toModel(updateProductGroup);
        return ResponseEntity.created(productGroupEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productGroupEntityModel);
    }

    @DeleteMapping("productGroups/{id}")
    ResponseEntity<?> deleteProductGroup(@PathVariable Integer id){
        productGroupService.deleteProductGroup(id);
        return ResponseEntity.noContent().build();
    }
}
