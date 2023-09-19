package com.schoolproject.Drugstore.product.specify;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;


// use for method get, return response kieu productDto cho client thay
@Data
@AllArgsConstructor
public class ProductSpecifyForDto {
    private Integer id;
    private String name;
    private Collection<String> products;

}
