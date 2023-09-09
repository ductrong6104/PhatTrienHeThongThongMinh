package com.schoolproject.Drugstore.product.brand;



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
public class BrandController {
    private final BrandService brandService;
    private final BrandModelAssembler brandModelAssembler;


    @Autowired
    public BrandController(BrandService brandService, BrandModelAssembler brandModelAssembler) {
        this.brandService = brandService;
        this.brandModelAssembler = brandModelAssembler;
    }

    @GetMapping("/brands/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        Brand brand = brandService.getBrandById(id);
        return brandModelAssembler.toModel(brand);
    }
    @GetMapping("/brands")
    CollectionModel<EntityModel<Brand>> all(){
        List<EntityModel<Brand>> brands = brandService.getAllBrands().stream().map(brandModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(brands, linkTo(methodOn(BrandController.class).all()).withSelfRel());
    }

    @PostMapping("/brands")
    ResponseEntity<?> newBrand(@RequestBody Brand brand){
        EntityModel<Brand> brandEntityModel = brandModelAssembler.toModel(brandService.addBrand(brand));

        return ResponseEntity.created(brandEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(brandEntityModel);
    }

    @PutMapping("brands/{id}")
    ResponseEntity<?> replaceBrand(@RequestBody Brand brand, @PathVariable Integer id){
        Brand updateBrand = brandService.updateBrand(brand, id);
        EntityModel<Brand> brandEntityModel = brandModelAssembler.toModel(updateBrand);
        return ResponseEntity.created(brandEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(brandEntityModel);
    }

    @DeleteMapping("brands/{id}")
    ResponseEntity<?> deleteBrand(@PathVariable Integer id){
        brandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }
}
