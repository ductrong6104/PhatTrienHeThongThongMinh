package com.schoolproject.Drugstore.product.group;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/productGroups")
@CrossOrigin
public class ProductGroupController implements ProductGroupOperations{
    private final ProductGroupService productGroupService;
    private final ProductGroupModelAssembler productGroupModelAssembler;
    private final ProductGroupMapperDto productGroupMapperDto;


    @Autowired
    public ProductGroupController(ProductGroupService productGroupService, ProductGroupModelAssembler productGroupModelAssembler, ProductGroupMapperDto productGroupMapperDto) {
        this.productGroupService = productGroupService;
        this.productGroupModelAssembler = productGroupModelAssembler;
        this.productGroupMapperDto = productGroupMapperDto;
    }

    @GetMapping("/{id}")
    public EntityModel<?> one(@PathVariable Integer id){
        ProductGroupDto productGroupDto = productGroupService.getProductById(id);
        return productGroupModelAssembler.toModel(productGroupDto);
    }
    @GetMapping("")
    public CollectionModel<EntityModel<ProductGroupDto>> all(){
        List<EntityModel<ProductGroupDto>> productGroups = productGroupService.getAllProducts().stream().map(productGroupModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(productGroups, linkTo(methodOn(ProductGroupController.class).all()).withSelfRel());
    }

    @PostMapping("")
    public ResponseEntity<?> newProductGroup(@RequestBody ProductGroupCreationDto productGroupCreationDto){
        EntityModel<ProductGroupDto> productGroupEntityModel = productGroupModelAssembler.toModel(productGroupService.addProductGroup(productGroupCreationDto));
        return ResponseEntity.created(productGroupEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productGroupEntityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> replaceProductGroup(@RequestBody ProductGroupCreationDto productGroupCreationDto, @PathVariable Integer id){
        ProductGroupDto updateProductGroup = productGroupService.updateProductGroup(productGroupCreationDto, id);
        EntityModel<ProductGroupDto> productGroupEntityModel = productGroupModelAssembler.toModel(updateProductGroup);
        return ResponseEntity.created(productGroupEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productGroupEntityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductGroup(@PathVariable Integer id){
        productGroupService.deleteProductGroup(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/typeId")
    public CollectionModel<EntityModel<ProductGroupDto>> getProductGroupsByTypeId(@RequestParam Integer typeId){
        List<EntityModel<ProductGroupDto>> productGroups = productGroupService.getProductsByTypeId(typeId).stream().map(productGroupModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(productGroups, linkTo(methodOn(ProductGroupController.class).all()).withSelfRel());
    }
}
