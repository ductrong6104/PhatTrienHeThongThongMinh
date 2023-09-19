package com.schoolproject.Drugstore.product.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// use for method post, get requestbody kieu productCreateDto
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryCreationDto {
    private String name;
    private Integer groupId;
}
