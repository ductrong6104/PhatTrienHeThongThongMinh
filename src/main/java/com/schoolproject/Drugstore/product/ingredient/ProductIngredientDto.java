package com.schoolproject.Drugstore.product.ingredient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductIngredientDto {

    private Integer id;
    private String name;
    private String description;

}
