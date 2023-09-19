package com.schoolproject.Drugstore.product.use;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;


// use for method get, return response kieu productDto cho client thay
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUseForDto {
    private Integer id;
    private String name;
    private Collection<String> products;


}
