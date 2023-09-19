package com.schoolproject.Drugstore.product.ingredient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;


// use for method get, return response kieu productDto cho client thay
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductIngredientDto {
    private Integer id;
    private String name;
    private String description;
    private Collection<String> products;
}
