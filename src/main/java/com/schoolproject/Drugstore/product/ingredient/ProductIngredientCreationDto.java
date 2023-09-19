package com.schoolproject.Drugstore.product.ingredient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// use for method post, get requestbody kieu productCreateDto
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductIngredientCreationDto {
    private String name;
    private String description;
}
