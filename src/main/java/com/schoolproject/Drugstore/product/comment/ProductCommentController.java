package com.schoolproject.Drugstore.product.comment;


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
ProductCommentController {
    private final ProductCommentService productCommentService;
    private final ProductCommentModelAssembler productCommentModelAssembler;
    private final ProductCommentMapperDto productCommentMapperDto;


    @Autowired
    public ProductCommentController(ProductCommentService productCommentService, ProductCommentModelAssembler productCommentModelAssembler, ProductCommentMapperDto productCommentMapperDto) {
        this.productCommentService = productCommentService;
        this.productCommentModelAssembler = productCommentModelAssembler;
        this.productCommentMapperDto = productCommentMapperDto;
    }

    @GetMapping("/productComments/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        ProductCommentDto productCommentDto = productCommentService.getProductCommentById(id);
        return productCommentModelAssembler.toModel(productCommentDto);
    }
    @GetMapping("/productComments")
    CollectionModel<EntityModel<ProductCommentDto>> all(){
        List<EntityModel<ProductCommentDto>> productComments = productCommentService.getAllProductComments().stream().map(productCommentModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(productComments, linkTo(methodOn(ProductCommentController.class).all()).withSelfRel());
    }

    @PostMapping("/productComments")
    ResponseEntity<?> newProductComment(@RequestBody ProductCommentCreationDto productCommentCreationDto){
        EntityModel<ProductCommentDto> productCommentEntityModel = productCommentModelAssembler.toModel(productCommentService.addProductComment(productCommentCreationDto));
        return ResponseEntity.created(productCommentEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productCommentEntityModel);
    }

    @PutMapping("productComments/{id}")
    ResponseEntity<?> replaceProductComment(@RequestBody ProductCommentCreationDto productCommentCreationDto, @PathVariable Integer id){
        ProductCommentDto updateProduct = productCommentService.updateProductComment(productCommentCreationDto, id);
        EntityModel<ProductCommentDto> productCommentEntityModel = productCommentModelAssembler.toModel(updateProduct);
        return ResponseEntity.created(productCommentEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productCommentEntityModel);
    }

    @DeleteMapping("productComments/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable Integer id){
        productCommentService.deleteProductComment(id);
        return ResponseEntity.noContent().build();
    }
}
