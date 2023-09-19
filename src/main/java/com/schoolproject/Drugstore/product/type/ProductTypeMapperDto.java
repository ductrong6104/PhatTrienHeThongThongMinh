package com.schoolproject.Drugstore.product.type;

import com.schoolproject.Drugstore.product.category.ProductCategory;
import com.schoolproject.Drugstore.product.group.ProductGroup;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class ProductTypeMapperDto {


    public ProductTypeDto toDTO(ProductType productType){
        Collection<String> productGroups = productType.getProductGroups().stream().map(ProductGroup::getName).toList();
        return new ProductTypeDto(productType.getId(), productType.getName(),productGroups);
    }

    public ProductType toProductType(ProductTypeCreationDto productTypeCreationDto){
        return new ProductType(productTypeCreationDto.getName(), new ArrayList<>());
    }

}
