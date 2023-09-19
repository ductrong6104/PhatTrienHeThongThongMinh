package com.schoolproject.Drugstore.product.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;


// use for method get, return response kieu productDto cho client thay
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductGroupDto {
    private Integer id;
    private String productName;
    private String typeName;
    private Collection<String> productCategories;


}
