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
@CrossOrigin
public class ProductDosageFormController {
    private final ProductDosageFormService productDosageFormService;
    private final ProductDosageFormModelAssembler productDosageFormModelAssembler;
    private final ProductDosageFormMapperDto productDosageFormMapperDto;


    @Autowired
    public ProductDosageFormController(ProductDosageFormService productDosageFormService, ProductDosageFormModelAssembler productDosageFormModelAssembler, ProductDosageFormMapperDto productDosageFormMapperDto) {
        this.productDosageFormService = productDosageFormService;
        this.productDosageFormModelAssembler = productDosageFormModelAssembler;
        this.productDosageFormMapperDto = productDosageFormMapperDto;
    }

    @GetMapping("/productDosageForms/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        ProductDosageFormDto productDosageFormDto = productDosageFormService.getProductById(id);
        return productDosageFormModelAssembler.toModel(productDosageFormDto);
    }
    @GetMapping("/productDosageForms")
    CollectionModel<EntityModel<ProductDosageFormDto>> all(){
        List<EntityModel<ProductDosageFormDto>> productDosageForms = productDosageFormService.getAllProducts().stream().map(productDosageFormModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(productDosageForms, linkTo(methodOn(ProductDosageFormController.class).all()).withSelfRel());
    }

    @PostMapping("/productDosageForms")
    ResponseEntity<?> newProductDosageForm(@RequestBody ProductDosageFormCreationDto productDosageFormCreationDto){
        EntityModel<ProductDosageFormDto> productDosageFormEntityModel = productDosageFormModelAssembler.toModel(productDosageFormService.addProductDosageForm(productDosageFormCreationDto));
        return ResponseEntity.created(productDosageFormEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productDosageFormEntityModel);
    }

    @PutMapping("productDosageForms/{id}")
    ResponseEntity<?> replaceProductDosageForm(@RequestBody ProductDosageFormCreationDto productDosageFormCreationDto, @PathVariable Integer id){
        ProductDosageFormDto updateProductDosageForm = productDosageFormService.updateProductDosageForm(productDosageFormCreationDto, id);
        EntityModel<ProductDosageFormDto> productDosageFormEntityModel = productDosageFormModelAssembler.toModel(updateProductDosageForm);
        return ResponseEntity.created(productDosageFormEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productDosageFormEntityModel);
    }

    @DeleteMapping("productDosageForms/{id}")
    ResponseEntity<?> deleteProductDosageForm(@PathVariable Integer id){
        productDosageFormService.deleteProductDosageForm(id);
        return ResponseEntity.noContent().build();
    }
}
