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
@CrossOrigin
public class BrandController {
    private final BrandService brandService;
    private final BrandModelAssembler brandModelAssembler;
    private final BrandMapperDto brandMapperDto;


    @Autowired
    public BrandController(BrandService brandService, BrandModelAssembler brandModelAssembler, BrandMapperDto brandMapperDto) {
        this.brandService = brandService;
        this.brandModelAssembler = brandModelAssembler;
        this.brandMapperDto = brandMapperDto;
    }

    @GetMapping("/brands/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        BrandDto brandDto = brandService.getProductById(id);
        return brandModelAssembler.toModel(brandDto);
    }
    @GetMapping("/brands")
    CollectionModel<EntityModel<BrandDto>> all(){
        List<EntityModel<BrandDto>> brands = brandService.getAllProducts().stream().map(brandModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(brands, linkTo(methodOn(BrandController.class).all()).withSelfRel());
    }

    @PostMapping("/brands")
    ResponseEntity<?> newBrand(@RequestBody BrandCreationDto brandCreationDto){
        EntityModel<BrandDto> brandEntityModel = brandModelAssembler.toModel(brandService.addBrand(brandCreationDto));
        return ResponseEntity.created(brandEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(brandEntityModel);
    }

    @PutMapping("brands/{id}")
    ResponseEntity<?> replaceBrand(@RequestBody BrandCreationDto brandCreationDto, @PathVariable Integer id){
        BrandDto updateBrand = brandService.updateBrand(brandCreationDto, id);
        EntityModel<BrandDto> brandEntityModel = brandModelAssembler.toModel(updateBrand);
        return ResponseEntity.created(brandEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(brandEntityModel);
    }

    @DeleteMapping("brands/{id}")
    ResponseEntity<?> deleteBrand(@PathVariable Integer id){
        brandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }
}
