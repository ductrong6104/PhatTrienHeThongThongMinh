package com.schoolproject.Drugstore.product.use;

import org.springframework.stereotype.Component;

import com.schoolproject.Drugstore.exception.customeException.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductUseForMapper {

    public ProductUseFor toEntity(ProductUseForDto productUseForDto) {
        if (productUseForDto == null) {
            throw new DataNotFoundException();
        }
        ProductUseFor productUseFor = ProductUseFor.builder()
                .id(productUseForDto.getId())
                .name(productUseForDto.getName())
                .build();
        return productUseFor;
    }

    public ProductUseForDto toDto(ProductUseFor productUseFor) {
        if (productUseFor == null) {
            throw new DataNotFoundException();
        }
        ProductUseForDto productUseForDto = ProductUseForDto.builder()
                .id(productUseFor.getId())
                .name(productUseFor.getName())
                .build();

        return productUseForDto;
    }

}
