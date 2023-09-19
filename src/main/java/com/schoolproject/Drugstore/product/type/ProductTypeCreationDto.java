package com.schoolproject.Drugstore.product.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// use for method post, get requestbody kieu productCreateDto
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductTypeCreationDto {
    private String name;
}
