package com.schoolproject.Drugstore.product.specify;

import org.springframework.stereotype.Component;

import com.schoolproject.Drugstore.exception.customeException.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductSpecifyForMapper {

    public ProductSpecifyFor toEntity(ProductSpecifyForDto productSpecifyForDto) {
        if (productSpecifyForDto == null) {
            throw new DataNotFoundException();
        }
        ProductSpecifyFor productType = ProductSpecifyFor.builder()
                .id(productSpecifyForDto.getId())
                .name(productSpecifyForDto.getName())
                .build();
        return productType;
    }

    public ProductSpecifyForDto toDto(ProductSpecifyFor productType) {
        if (productType == null) {
            throw new DataNotFoundException();
        }
        ProductSpecifyForDto productSpecifyForDto = ProductSpecifyForDto.builder()
                .id(productType.getId())
                .name(productType.getName())
                .build();

        return productSpecifyForDto;
    }

}
