package com.schoolproject.Drugstore.product.specify;



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
public class ProductSpecifyForController {
    private final ProductSpecifyForService productSpecifyForService;
    private final ProductSpecifyForModelAssembler productSpecifyForModelAssembler;
    private final ProductSpecifyForMapperDto productSpecifyForMapperDto;


    @Autowired
    public ProductSpecifyForController(ProductSpecifyForService productSpecifyForService, ProductSpecifyForModelAssembler productSpecifyForModelAssembler, ProductSpecifyForMapperDto productSpecifyForMapperDto) {
        this.productSpecifyForService = productSpecifyForService;
        this.productSpecifyForModelAssembler = productSpecifyForModelAssembler;
        this.productSpecifyForMapperDto = productSpecifyForMapperDto;
    }

    @GetMapping("/productSpecifyFors/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        ProductSpecifyForDto productSpecifyForDto = productSpecifyForService.getProductById(id);
        return productSpecifyForModelAssembler.toModel(productSpecifyForDto);
    }
    @GetMapping("/productSpecifyFors")
    CollectionModel<EntityModel<ProductSpecifyForDto>> all(){
        List<EntityModel<ProductSpecifyForDto>> productSpecifyFors = productSpecifyForService.getAllProducts().stream().map(productSpecifyForModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(productSpecifyFors, linkTo(methodOn(ProductSpecifyForController.class).all()).withSelfRel());
    }

    @PostMapping("/productSpecifyFors")
    ResponseEntity<?> newProductSpecifyFor(@RequestBody ProductSpecifyForCreationDto productSpecifyForCreationDto){
        EntityModel<ProductSpecifyForDto> productSpecifyForEntityModel = productSpecifyForModelAssembler.toModel(productSpecifyForService.addProductSpecifyFor(productSpecifyForCreationDto));
        return ResponseEntity.created(productSpecifyForEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productSpecifyForEntityModel);
    }

    @PutMapping("productSpecifyFors/{id}")
    ResponseEntity<?> replaceProductSpecifyFor(@RequestBody ProductSpecifyForCreationDto productSpecifyForCreationDto, @PathVariable Integer id){
        ProductSpecifyForDto updateProductSpecifyFor = productSpecifyForService.updateProductSpecifyFor(productSpecifyForCreationDto, id);
        EntityModel<ProductSpecifyForDto> productSpecifyForEntityModel = productSpecifyForModelAssembler.toModel(updateProductSpecifyFor);
        return ResponseEntity.created(productSpecifyForEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productSpecifyForEntityModel);
    }

    @DeleteMapping("productSpecifyFors/{id}")
    ResponseEntity<?> deleteProductSpecifyFor(@PathVariable Integer id){
        productSpecifyForService.deleteProductSpecifyFor(id);
        return ResponseEntity.noContent().build();
    }
}
