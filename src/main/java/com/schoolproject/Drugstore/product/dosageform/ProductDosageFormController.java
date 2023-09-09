package com.schoolproject.Drugstore.product.dosageform;



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
public class ProductDosageFormController {
    private final ProductDosageFormService productDosageFormService;
    private final ProductDosageFormModelAssembler productDosageFormModelAssembler;


    @Autowired
    public ProductDosageFormController(ProductDosageFormService productDosageFormService, ProductDosageFormModelAssembler productDosageFormModelAssembler) {
        this.productDosageFormService = productDosageFormService;
        this.productDosageFormModelAssembler = productDosageFormModelAssembler;
    }

    @GetMapping("/productDosageForms/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        ProductDosageForm productDosageForm = productDosageFormService.getProductDosageFormById(id);
        return productDosageFormModelAssembler.toModel(productDosageForm);
    }
    @GetMapping("/productDosageForms")
    CollectionModel<EntityModel<ProductDosageForm>> all(){
        List<EntityModel<ProductDosageForm>> productDosageForms = productDosageFormService.getAllProductDosageForms().stream().map(productDosageFormModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(productDosageForms, linkTo(methodOn(ProductDosageFormController.class).all()).withSelfRel());
    }

    @PostMapping("/productDosageForms")
    ResponseEntity<?> newProductDosageForm(@RequestBody ProductDosageForm productDosageForm){
        EntityModel<ProductDosageForm> productDosageFormEntityModel = productDosageFormModelAssembler.toModel(productDosageFormService.addProductDosageForm(productDosageForm));

        return ResponseEntity.created(productDosageFormEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productDosageFormEntityModel);
    }

    @PutMapping("productDosageForms/{id}")
    ResponseEntity<?> replaceProductDosageForm(@RequestBody ProductDosageForm productDosageForm, @PathVariable Integer id){
        ProductDosageForm updateProductDosageForm = productDosageFormService.updateProductDosageForm(productDosageForm, id);
        EntityModel<ProductDosageForm> productDosageFormEntityModel = productDosageFormModelAssembler.toModel(updateProductDosageForm);
        return ResponseEntity.created(productDosageFormEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productDosageFormEntityModel);
    }

    @DeleteMapping("productDosageForms/{id}")
    ResponseEntity<?> deleteProductDosageForm(@PathVariable Integer id){
        productDosageFormService.deleteProductDosageForm(id);
        return ResponseEntity.noContent().build();
    }
}
