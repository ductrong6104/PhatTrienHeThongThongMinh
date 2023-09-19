package com.schoolproject.Drugstore.product.type;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;


// use for method get, return response kieu productDto cho client thay
@Data
@AllArgsConstructor
public class ProductTypeDto {
    private Integer id;
    private String productTypeName;
    private Collection<String> productGroups;

}
