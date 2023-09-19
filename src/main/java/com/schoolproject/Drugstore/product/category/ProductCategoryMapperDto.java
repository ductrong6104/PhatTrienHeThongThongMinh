package com.schoolproject.Drugstore.product.category;


import com.schoolproject.Drugstore.product.group.ProductGroup;

import com.schoolproject.Drugstore.product.group.ProductGroupService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProductCategoryMapperDto {
    private final ProductGroupService productGroupService;

    public ProductCategoryMapperDto(ProductGroupService productGroupService) {
        this.productGroupService = productGroupService;
    }

    public ProductCategoryDto toDTO(ProductCategory productCategory){
        return new ProductCategoryDto(productCategory.getId(), productCategory.getName(), productCategory.getProductGroup().getName());
    }

    public ProductCategory toProductCategory(ProductCategoryCreationDto productCategoryCreationDto){
        return new ProductCategory(productCategoryCreationDto.getName(), new ProductGroup(), new ArrayList<>());
    }

}
