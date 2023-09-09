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
public class ProductRateController {
    private final ProductRateService productRateService;
    private final ProductRateModelAssembler productRateModelAssembler;


    @Autowired
    public ProductRateController(ProductRateService productRateService, ProductRateModelAssembler productRateModelAssembler) {
        this.productRateService = productRateService;
        this.productRateModelAssembler = productRateModelAssembler;
    }

    @GetMapping("/productRates/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        ProductRate productRate = productRateService.getProductRateById(id);
        return productRateModelAssembler.toModel(productRate);
    }
    @GetMapping("/productRates")
    CollectionModel<EntityModel<ProductRate>> all(){
        List<EntityModel<ProductRate>> productRates = productRateService.getAllProductRates().stream().map(productRateModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(productRates, linkTo(methodOn(ProductRateController.class).all()).withSelfRel());
    }

    @PostMapping("/productRates")
    ResponseEntity<?> newProductRate(@RequestBody ProductRate productRate){
        EntityModel<ProductRate> productRateEntityModel = productRateModelAssembler.toModel(productRateService.addProductRate(productRate));

        return ResponseEntity.created(productRateEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productRateEntityModel);
    }

    @PutMapping("productRates/{id}")
    ResponseEntity<?> replaceProductRate(@RequestBody ProductRate productRate, @PathVariable Integer id){
        ProductRate updateProductRate = productRateService.updateProductRate(productRate, id);
        EntityModel<ProductRate> productRateEntityModel = productRateModelAssembler.toModel(updateProductRate);
        return ResponseEntity.created(productRateEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productRateEntityModel);
    }

    @DeleteMapping("productRates/{id}")
    ResponseEntity<?> deleteProductRate(@PathVariable Integer id){
        productRateService.deleteProductRate(id);
        return ResponseEntity.noContent().build();
    }
}
