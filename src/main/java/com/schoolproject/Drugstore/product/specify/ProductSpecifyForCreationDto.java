package com.schoolproject.Drugstore.product.specify;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// use for method post, get requestbody kieu productCreateDto
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSpecifyForCreationDto {
    private String name;
}
