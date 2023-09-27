package com.schoolproject.Drugstore.product.ingredient;

import org.springframework.stereotype.Component;

import com.schoolproject.Drugstore.exception.customeException.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductIngredientMapper {

    public ProductIngredient toEntity(ProductIngredientDto productIngredientDto) {
        if (productIngredientDto == null) {
            throw new DataNotFoundException();
        }
        ProductIngredient productIngredient = ProductIngredient.builder()
                .id(productIngredientDto.getId())
                .name(productIngredientDto.getName())
                .description(productIngredientDto.getDescription())
                .build();
        return productIngredient;
    }

    public ProductIngredientDto toDto(ProductIngredient productIngredient) {
        if (productIngredient == null) {
            throw new DataNotFoundException();
        }
        ProductIngredientDto productIngredientDto = ProductIngredientDto.builder()
                .id(productIngredient.getId())
                .name(productIngredient.getName())
                .description(productIngredient.getDescription())
                .build();

        return productIngredientDto;
    }

}
