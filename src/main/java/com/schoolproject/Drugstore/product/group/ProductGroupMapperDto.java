package com.schoolproject.Drugstore.product.group;

import com.schoolproject.Drugstore.product.category.ProductCategory;
import com.schoolproject.Drugstore.product.type.ProductType;
import com.schoolproject.Drugstore.product.type.ProductTypeService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class ProductGroupMapperDto {
    private final ProductTypeService productTypeService;

    public ProductGroupMapperDto(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    public ProductGroupDto toDTO(ProductGroup productGroup){
        Collection<String> productCategories = productGroup.getProductCategories().stream().map(ProductCategory::getName).toList();
        return new ProductGroupDto(productGroup.getId(), productGroup.getName(), productGroup.getProductType().getName(),productCategories);
    }

    public ProductGroup toProductGroup(ProductGroupCreationDto productGroupCreationDto){
        return new ProductGroup(productGroupCreationDto.getName(), new ProductType(), new ArrayList<>());
    }

}
