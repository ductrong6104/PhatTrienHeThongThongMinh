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
public class ProductCommentController {
    private final ProductCommentService productCommentService;
    private final ProductCommentModelAssembler productCommentModelAssembler;


    @Autowired
    public ProductCommentController(ProductCommentService productCommentService, ProductCommentModelAssembler productCommentModelAssembler) {
        this.productCommentService = productCommentService;
        this.productCommentModelAssembler = productCommentModelAssembler;
    }

    @GetMapping("/productComments/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        ProductComment productComment = productCommentService.getProductCommentById(id);
        return productCommentModelAssembler.toModel(productComment);
    }
    @GetMapping("/productComments")
    CollectionModel<EntityModel<ProductComment>> all(){
        List<EntityModel<ProductComment>> productComments = productCommentService.getAllProductComments().stream().map(productCommentModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(productComments, linkTo(methodOn(ProductCommentController.class).all()).withSelfRel());
    }

    @PostMapping("/productComments")
    ResponseEntity<?> newProductComment(@RequestBody ProductComment productComment){
        EntityModel<ProductComment> productCommentEntityModel = productCommentModelAssembler.toModel(productCommentService.addProductComment(productComment));
        return ResponseEntity.created(productCommentEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productCommentEntityModel);
    }

    @PutMapping("productComments/{id}")
    ResponseEntity<?> replaceProductComment(@RequestBody ProductComment productComment, @PathVariable Integer id){
        ProductComment updateProductComment = productCommentService.updateProductComment(productComment, id);
        EntityModel<ProductComment> productCommentEntityModel = productCommentModelAssembler.toModel(updateProductComment);
        return ResponseEntity.created(productCommentEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(productCommentEntityModel);
    }

    @DeleteMapping("productComments/{id}")
    ResponseEntity<?> deleteProductComment(@PathVariable Integer id){
        productCommentService.deleteProductComment(id);
        return ResponseEntity.noContent().build();
    }
}
