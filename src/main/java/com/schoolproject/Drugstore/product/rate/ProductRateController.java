package com.schoolproject.Drugstore.product.rate;


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
public class ProductRateController {
    private final ProductRateService productRateService;
    private final ProductRateModelAssembler productRateModelAssembler;
    private final ProductRateMapperDto productRateMapperDto;


    @Autowired
    public ProductRateController(ProductRateService productRateService, ProductRateModelAssembler productRateModelAssembler, ProductRateMapperDto productRateMapperDto) {
        this.productRateService = productRateService;
        this.productRateModelAssembler = productRateModelAssembler;
        this.productRateMapperDto = productRateMapperDto;
    }

    @GetMapping("/productRates/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        ProductRateDto productRateDto = productRateService.getProductRateById(id);
        return productRateModelAssembler.toModel(productRateDto);
    }
    @GetMapping("/productRates")
    CollectionModel<EntityModel<ProductRateDto>> all(){
        List<EntityModel<ProductRateDto>> productRates = productRateService.getAllProductRates().stream().map(productRateModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(productRates, linkTo(methodOn(ProductRateController.class).all()).withSelfRel());
    }

    @PostMapping("/productRates")
    ResponseEntity<?> newProductRate(@RequestBody ProductRateCreationDto productRateCreationDto){
        EntityModel<ProductRateDto> productRateEntityModel = productRateModelAssembler.toModel(productRateService.addProductRate(productRateCreationDto));
        return ResponseEntity.created(productRateEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productRateEntityModel);
    }

    @PutMapping("productRates/{id}")
    ResponseEntity<?> replaceProductRate(@RequestBody ProductRateCreationDto productRateCreationDto, @PathVariable Integer id){
        ProductRateDto updateProduct = productRateService.updateProductRate(productRateCreationDto, id);
        EntityModel<ProductRateDto> productRateEntityModel = productRateModelAssembler.toModel(updateProduct);
        return ResponseEntity.created(productRateEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productRateEntityModel);
    }

    @DeleteMapping("productRates/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable Integer id){
        productRateService.deleteProductRate(id);
        return ResponseEntity.noContent().build();
    }
}
