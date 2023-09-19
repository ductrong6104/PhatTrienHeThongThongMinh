package com.schoolproject.Drugstore.product.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// use for method get, return response kieu productDto cho client thay
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryDto {
    private Integer id;
    private String name;
    private String groupName;

}
