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

@RequestMapping("/productComments")
public interface ProductCommentOperations {
    @GetMapping("/{id}")
    EntityModel<?> one(@PathVariable Integer id);
    @GetMapping("")// Collection
    CollectionModel<EntityModel<ProductCommentDto>> all();

    @PostMapping("")
    ResponseEntity<?> newProductComment(@RequestBody ProductCommentCreationDto productCommentCreationDto);

    @PutMapping("/{id}")
    ResponseEntity<?> replaceProductComment(@RequestBody ProductCommentCreationDto productCommentCreationDto, @PathVariable Integer id);
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable Integer id);
    @GetMapping("/loc-binh-luan-theo-thuoc")// Collection
    CollectionModel<EntityModel<ProductCommentDto>> filterCommentByProduct(@RequestParam("f") Integer productId);
}
