package com.schoolproject.Drugstore.product.category;

import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

import com.schoolproject.Drugstore.exception.customeException.DataNotFoundException;
import com.schoolproject.Drugstore.product.group.ProductGroup;
import com.schoolproject.Drugstore.product.group.ProductGroupRepository;

@Component
@RequiredArgsConstructor
public class ProductCategoryMapper {

    private final ProductGroupRepository productGroupRepository;

    public ProductCategory toEntity(ProductCategoryDto productCategoryDto) {
        if (productCategoryDto == null) {
            throw new DataNotFoundException();
        }

        ProductGroup productGroup = productGroupRepository
                .findById(productCategoryDto.getGroupId())
                .get();

        ProductCategory productCategory = ProductCategory.builder()
                .id(productCategoryDto.getId())
                .name(productCategoryDto.getName())
                .productGroup(productGroup)
                .build();

        return productCategory;
    }

    public ProductCategoryDto toDto(ProductCategory productCategory) {
        if (productCategory == null) {
            throw new DataNotFoundException();
        }
        ProductCategoryDto productGroupDto = ProductCategoryDto.builder()
                .id(productCategory.getId())
                .name(productCategory.getName())
                .groupId(productCategory.getProductGroup().getId())
                .build();

        return productGroupDto;
    }

}
