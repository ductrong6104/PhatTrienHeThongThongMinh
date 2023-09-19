package com.schoolproject.Drugstore.product.brand;


import com.schoolproject.Drugstore.nation.Nation;

import com.schoolproject.Drugstore.product.group.ProductGroup;
import com.schoolproject.Drugstore.product.group.ProductGroupService;
import com.schoolproject.Drugstore.product.product.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class BrandMapperDto {

    public BrandDto toDTO(Brand brand){
        return new BrandDto(brand.getId(), brand.getName(), brand.getDescription(), brand.getAvatar(), brand.getNation().getName(), brand.getProducts().stream().map(Product::getName).toList());
    }

    public Brand toBrand(BrandCreationDto brandCreationDto){
        return new Brand(brandCreationDto.getName(),brandCreationDto.getDescription(), brandCreationDto.getAvatar(), new Nation(), new ArrayList<>());
    }

}
