package com.schoolproject.Drugstore.product.ingredient;

import com.schoolproject.Drugstore.product.product.Product;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProductIngredientMapperDto {
    public ProductIngredientDto toDTO(ProductIngredient productIngredient){
        return new ProductIngredientDto(productIngredient.getId(), productIngredient.getName(), productIngredient.getDescription(),productIngredient.getProducts().stream().map(Product::getName).toList());
    }

    public ProductIngredient toProductIngredient(ProductIngredientCreationDto productIngredientCreationDto){
        return new ProductIngredient(productIngredientCreationDto.getName(), productIngredientCreationDto.getDescription(), new ArrayList<>());
    }

}
