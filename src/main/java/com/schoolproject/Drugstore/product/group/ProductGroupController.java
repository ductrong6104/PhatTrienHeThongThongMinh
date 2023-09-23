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
public class
ProductGroupController {
    private final ProductGroupService productGroupService;
    private final ProductGroupModelAssembler productGroupModelAssembler;
    private final ProductGroupMapperDto productGroupMapperDto;


    @Autowired
    public ProductGroupController(ProductGroupService productGroupService, ProductGroupModelAssembler productGroupModelAssembler, ProductGroupMapperDto productGroupMapperDto) {
        this.productGroupService = productGroupService;
        this.productGroupModelAssembler = productGroupModelAssembler;
        this.productGroupMapperDto = productGroupMapperDto;
    }

    @GetMapping("/productGroups/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        ProductGroupDto productGroupDto = productGroupService.getProductById(id);
        return productGroupModelAssembler.toModel(productGroupDto);
    }
    @GetMapping("/productGroups")
    CollectionModel<EntityModel<ProductGroupDto>> all(){
        List<EntityModel<ProductGroupDto>> productGroups = productGroupService.getAllProducts().stream().map(productGroupModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(productGroups, linkTo(methodOn(ProductGroupController.class).all()).withSelfRel());
    }

    @PostMapping("/productGroups")
    ResponseEntity<?> newProductGroup(@RequestBody ProductGroupCreationDto productGroupCreationDto){
        EntityModel<ProductGroupDto> productGroupEntityModel = productGroupModelAssembler.toModel(productGroupService.addProductGroup(productGroupCreationDto));
        return ResponseEntity.created(productGroupEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productGroupEntityModel);
    }

    @PutMapping("productGroups/{id}")
    ResponseEntity<?> replaceProductGroup(@RequestBody ProductGroupCreationDto productGroupCreationDto, @PathVariable Integer id){
        ProductGroupDto updateProductGroup = productGroupService.updateProductGroup(productGroupCreationDto, id);
        EntityModel<ProductGroupDto> productGroupEntityModel = productGroupModelAssembler.toModel(updateProductGroup);
        return ResponseEntity.created(productGroupEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productGroupEntityModel);
    }

    @DeleteMapping("productGroups/{id}")
    ResponseEntity<?> deleteProductGroup(@PathVariable Integer id){
        productGroupService.deleteProductGroup(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/productGroups/typeId")
    EntityModel<?> getProductGroupByTypeId(@RequestParam Integer typeId){
        ProductGroupDto productGroupDto = productGroupService.getProductByTypeId(typeId);
        return productGroupModelAssembler.toModel(productGroupDto);
    }
}
