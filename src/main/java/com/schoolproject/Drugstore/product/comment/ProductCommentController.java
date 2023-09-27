package com.schoolproject.Drugstore.product.comment;


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
@RequestMapping("/productComments")
@CrossOrigin
public class ProductCommentController implements ProductCommentOperations{
    private final ProductCommentService productCommentService;
    private final ProductCommentModelAssembler productCommentModelAssembler;
    private final ProductCommentMapperDto productCommentMapperDto;


    @Autowired
    public ProductCommentController(ProductCommentService productCommentService, ProductCommentModelAssembler productCommentModelAssembler, ProductCommentMapperDto productCommentMapperDto) {
        this.productCommentService = productCommentService;
        this.productCommentModelAssembler = productCommentModelAssembler;
        this.productCommentMapperDto = productCommentMapperDto;
    }

    @GetMapping("/{id}")
    public EntityModel<?> one(@PathVariable Integer id){
        ProductCommentDto productCommentDto = productCommentService.getProductCommentById(id);
        return productCommentModelAssembler.toModel(productCommentDto);
    }
    @GetMapping("")
    // Collection
    public CollectionModel<EntityModel<ProductCommentDto>> all(){
        // type ProductCommentDto
        List<EntityModel<ProductCommentDto>> productComments = productCommentService.getAllProductComments().stream().map(productCommentModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(productComments, linkTo(methodOn(ProductCommentController.class).all()).withSelfRel());
    }

    @PostMapping("")
    public ResponseEntity<?> newProductComment(@RequestBody ProductCommentCreationDto productCommentCreationDto){
        EntityModel<ProductCommentDto> productCommentEntityModel = productCommentModelAssembler.toModel(productCommentService.addProductComment(productCommentCreationDto));
        // ma 200
        return ResponseEntity.created(productCommentEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productCommentEntityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> replaceProductComment(@RequestBody ProductCommentCreationDto productCommentCreationDto, @PathVariable Integer id){
        ProductCommentDto updateProduct = productCommentService.updateProductComment(productCommentCreationDto, id);
        EntityModel<ProductCommentDto> productCommentEntityModel = productCommentModelAssembler.toModel(updateProduct);
        return ResponseEntity.created(productCommentEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productCommentEntityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id){
        productCommentService.deleteProductComment(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public CollectionModel<EntityModel<ProductCommentDto>> filterCommentByProduct(Integer productId) {
        List<EntityModel<ProductCommentDto>> productComments = productCommentService.filterCommentByProduct(productId).stream().map(productCommentModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(productComments, linkTo(methodOn(ProductCommentController.class).all()).withSelfRel());
    }
}
