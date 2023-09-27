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

@RequestMapping("/productRates")
public interface ProductRateOperations {


    @GetMapping("/{id}")
    EntityModel<?> one(@PathVariable Integer id);
    @GetMapping("")
    CollectionModel<EntityModel<ProductRateDto>> all();

    @PostMapping("")
    ResponseEntity<?> newProductRate(@RequestBody ProductRateCreationDto productRateCreationDto);
    @PutMapping("/{id}")
    ResponseEntity<?> replaceProductRate(@RequestBody ProductRateCreationDto productRateCreationDto, @PathVariable Integer id);
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable Integer id);
    @GetMapping("/loc-danh-gia-theo-thuoc")
    CollectionModel<EntityModel<ProductRateDto>> filterRateByProduct(@RequestParam("f") Integer productId);
}