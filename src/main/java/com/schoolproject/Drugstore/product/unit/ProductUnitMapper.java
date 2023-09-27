package com.schoolproject.Drugstore.product.unit;

import org.springframework.stereotype.Component;

import com.schoolproject.Drugstore.exception.customeException.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductUnitMapper {

    public ProductUnit toEntity(ProductUnitDto productUnitDto) {
        if (productUnitDto == null) {
            throw new DataNotFoundException();
        }
        ProductUnit productUnit = ProductUnit.builder()
                .id(productUnitDto.getId())
                .name(productUnitDto.getName())
                .build();
        return productUnit;
    }

    public ProductUnitDto toDto(ProductUnit productUnit) {
        if (productUnit == null) {
            throw new DataNotFoundException();
        }
        ProductUnitDto productUnitDto = ProductUnitDto.builder()
                .id(productUnit.getId())
                .name(productUnit.getName())
                .build();

        return productUnitDto;
    }

}
