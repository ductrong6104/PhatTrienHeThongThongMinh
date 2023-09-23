package com.schoolproject.Drugstore.product.use;

import com.schoolproject.Drugstore.product.use.ProductUseForCreationDto;
import com.schoolproject.Drugstore.product.use.ProductUseForDto;
import com.schoolproject.Drugstore.product.use.ProductUseForMapperDto;
import com.schoolproject.Drugstore.product.use.ProductUseForServiceImpl;
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
public class ProductUseForController {
    private final ProductUseForService productUseForService;
    private final ProductUseForModelAssembler productUseForModelAssembler;
    private final ProductUseForMapperDto productUseForMapperDto;


    @Autowired
    public ProductUseForController(ProductUseForService productUseForService, ProductUseForModelAssembler productUseForModelAssembler, ProductUseForMapperDto productUseForMapperDto) {
        this.productUseForService = productUseForService;
        this.productUseForModelAssembler = productUseForModelAssembler;
        this.productUseForMapperDto = productUseForMapperDto;
    }

    @GetMapping("/productUseFors/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        ProductUseForDto productUseForDto = productUseForService.getProductUseForById(id);
        return productUseForModelAssembler.toModel(productUseForDto);
    }
    @GetMapping("/productUseFors")
    CollectionModel<EntityModel<ProductUseForDto>> all(){
        List<EntityModel<ProductUseForDto>> productUseFors = productUseForService.getAllProductUseFors().stream().map(productUseForModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(productUseFors, linkTo(methodOn(ProductUseForController.class).all()).withSelfRel());
    }

    @PostMapping("/productUseFors")
    ResponseEntity<?> newProduct(@RequestBody ProductUseForCreationDto productUseForCreationDto){
        EntityModel<ProductUseForDto> productUseForEntityModel = productUseForModelAssembler.toModel(productUseForService.addProductUseFor(productUseForCreationDto));
        return ResponseEntity.created(productUseForEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productUseForEntityModel);
    }

    @PutMapping("productUseFors/{id}")
    ResponseEntity<?> replaceProduct(@RequestBody ProductUseForCreationDto productUseForCreationDto, @PathVariable Integer id){
        ProductUseForDto updateProduct = productUseForService.updateProductUseFor(productUseForCreationDto, id);
        EntityModel<ProductUseForDto> productUseForEntityModel = productUseForModelAssembler.toModel(updateProduct);
        return ResponseEntity.created(productUseForEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productUseForEntityModel);
    }

    @DeleteMapping("productUseFors/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable Integer id){
        productUseForService.deleteProductUseFor(id);
        return ResponseEntity.noContent().build();
    }
}
