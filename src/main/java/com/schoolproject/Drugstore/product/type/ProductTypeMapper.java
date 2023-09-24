package com.schoolproject.Drugstore.product.type;

import org.springframework.stereotype.Component;

import com.schoolproject.Drugstore.exception.customeException.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductTypeMapper {

    public ProductType toEntity(ProductTypeDto productTypeDto){
        if(productTypeDto == null){
            throw new DataNotFoundException();
        }
        ProductType productType = ProductType.builder()
                .id(productTypeDto.getId())
                .name(productTypeDto.getName())
                .build();
        return productType;
    }

    public ProductTypeDto toDto(ProductType productType){
        if(productType == null){
            throw new DataNotFoundException();
        }
        ProductTypeDto productTypeDto = ProductTypeDto.builder()
            .id(productType.getId())
            .name(productType.getName())
            .build();
        
        return productTypeDto;
    }


}
